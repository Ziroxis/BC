package com.yuanno.block_clover.events.ability;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityUseEvent;
import com.yuanno.block_clover.data.devil.DevilCapability;
import com.yuanno.block_clover.data.devil.IDevil;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.events.levelEvents.ExperienceUpEvent;
import com.yuanno.block_clover.networking.ManaSync;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncDevilPacket;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class AbilityUseEvents {

    @SubscribeEvent
    public static void onUsePre(AbilityUseEvent.Pre event)
    {
        PlayerEntity player = event.getPlayer();
        if (player.level.isClientSide)
            return;
        Ability ability = event.getAbility();
        IEntityStats propsEntity = EntityStatsCapability.get(player);
        IDevil propsDevil = DevilCapability.get(player);
        if (!ability.getIsDevil())
        {
            if (!ability.getEvolved())
                propsEntity.alterMana(-ability.getmanaCost());
            else
                propsEntity.alterMana(-ability.getEvolvedManaCost());
        }
        else if (ability.getIsDevil())
        {
            propsDevil.alterDevilMana(-ability.getmanaCost());
        }
        PacketHandler.sendTo(new ManaSync(propsEntity.getMana()), player);
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), propsEntity), player);
        PacketHandler.sendTo(new SSyncDevilPacket(player.getId(), propsDevil), player);

    }

    @SubscribeEvent
    public static void onUsePost(AbilityUseEvent.Post event)
    {
        PlayerEntity player = event.getPlayer();
        if (player.level.isClientSide)
            return;
        Ability ability = event.getAbility();
        IEntityStats entityStats = EntityStatsCapability.get(player);

        // experience of player logic
        if (entityStats.getLevel() < ability.getExperienceGainLevelCap())
        {
            float experienceToGive = ability.getExperiencePoint() * entityStats.getMultiplier();
            entityStats.alterExperience((int) experienceToGive);

            ExperienceUpEvent eventExperience = new ExperienceUpEvent(player, ability.getExperiencePoint());
            MinecraftForge.EVENT_BUS.post(eventExperience);
        }

        // experience of the spell logic
        if (entityStats.getExperienceSpell(ability.getName()) != null && (int) entityStats.getExperienceSpell(ability.getName()) >= ability.getEvolutionCost() && !ability.isEvolved())
            ability.evolved(true);
        if (entityStats.hasExperienceSpell(ability.getName())) {
            int experience = entityStats.getExperienceSpell(ability.getName());
            entityStats.setExperienceSpells(ability.getName(), experience + 1);
        }
        else
            entityStats.setExperienceSpells(ability.getName(), 1);

        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
    }
}

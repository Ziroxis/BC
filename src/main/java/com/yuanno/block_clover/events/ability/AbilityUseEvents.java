package com.yuanno.block_clover.events.ability;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.sorts.ChargeableAbility;
import com.yuanno.block_clover.data.devil.DevilCapability;
import com.yuanno.block_clover.data.devil.IDevil;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.events.experience.ExperienceUpEvent;
import com.yuanno.block_clover.networking.ManaSync;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncDevilPacket;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Here everything is handled so don't have to ctrl+c and ctrl+v or always have to extend from a class for later use
 * All the menial things that have to be done are here:
 * altering (devil) mana, experience, experience of spell, evolving spells
 */
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
            propsEntity.alterMana(-ability.getmanaCost());
        else if (ability.getIsDevil())
            propsDevil.alterDevilMana(-ability.getmanaCost());
        PacketHandler.sendTo(new ManaSync(propsEntity.getMana()), player);
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), propsEntity), player);
        PacketHandler.sendTo(new SSyncDevilPacket(player.getId(), propsDevil), player);
    }

    @SubscribeEvent
    public static void onUsePer(AbilityUseEvent.Per event)
    {
        PlayerEntity player = event.getPlayer();
        if (player.level.isClientSide)
            return;
        if (!(player.tickCount % 20 == 0))
            return;

        // handles the mana stuff
        Ability ability = event.getAbility();
        IEntityStats propsEntity = EntityStatsCapability.get(player);
        IDevil propsDevil = DevilCapability.get(player);
        if (!ability.getIsDevil())
            propsEntity.alterMana(-ability.getmanaCost());
        else if (ability.getIsDevil())
            propsDevil.alterDevilMana(-ability.getmanaCost());
        PacketHandler.sendTo(new ManaSync(propsEntity.getMana()), player);
        PacketHandler.sendTo(new SSyncDevilPacket(player.getId(), propsDevil), player);

        IEntityStats entityStats = EntityStatsCapability.get(player);

        // handles player experience stuff
        if (entityStats.getLevel() < ability.getExperienceGainLevelCap())
        {
            float experienceToGive = ability.getExperiencePoint() * entityStats.getMultiplier();
            entityStats.alterExperience((int) experienceToGive);

            ExperienceUpEvent eventExperience = new ExperienceUpEvent(player, ability.getExperiencePoint());
            MinecraftForge.EVENT_BUS.post(eventExperience);
        }

        // handles the evolution of the spell, 1 point every second maybe changed later?
        if (ability.getEvolvedAbility() != null && entityStats.hasExperienceSpell(ability.getName())) {
            int experience = entityStats.getExperienceSpell(ability.getName());
            entityStats.setExperienceSpells(ability.getName(), experience + 1);
        }
        else if (ability.getEvolvedAbility() != null)
            entityStats.setExperienceSpells(ability.getName(), 1);
        if (entityStats.getExperienceSpell(ability.getName()) != null && (int) entityStats.getExperienceSpell(ability.getName()) >= ability.getEvolutionCost()) {
            AbilityEvolutionEvent abilityEvolutionEvent = new AbilityEvolutionEvent(player, ability);
            MinecraftForge.EVENT_BUS.post(abilityEvolutionEvent);
        }

        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), propsEntity), player);
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
        if (ability.getEvolvedAbility() != null && entityStats.hasExperienceSpell(ability.getName())) {
            int experience = entityStats.getExperienceSpell(ability.getName());
            entityStats.setExperienceSpells(ability.getName(), experience + 1);
        }
        else if (ability.getEvolvedAbility() != null)
            entityStats.setExperienceSpells(ability.getName(), 1);
        if (entityStats.getExperienceSpell(ability.getName()) != null && (int) entityStats.getExperienceSpell(ability.getName()) >= ability.getEvolutionCost()) {
            AbilityEvolutionEvent abilityEvolutionEvent = new AbilityEvolutionEvent(player, ability);
            MinecraftForge.EVENT_BUS.post(abilityEvolutionEvent);
        }

        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
    }
}

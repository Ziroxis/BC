package com.yuanno.block_clover.events.ability;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncAbilityDataPacket;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class AbilityEvolutionEvents {

    @SubscribeEvent
    public static void onSpellEvolution(AbilityEvolutionEvent event)
    {
        PlayerEntity player = event.getPlayer();
        if (player.level.isClientSide)
            return;
        if (event.getAbility().getEvolvedAbility() == null)
            return;
        AbilityCore baseSpell = event.getAbility().getCore();
        AbilityCore evolvedSpell = baseSpell.createAbility().getEvolvedAbility();

        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.replaceAbility(baseSpell, evolvedSpell);
        if (evolvedSpell.createAbility().getEvolvedAbility() != null)
        {
            IEntityStats entityStats = EntityStatsCapability.get(player);
            entityStats.setExperienceSpells(evolvedSpell.getName(), 0);
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
        }
        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
    }
}

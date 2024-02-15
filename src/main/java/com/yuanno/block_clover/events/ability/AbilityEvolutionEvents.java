package com.yuanno.block_clover.events.ability;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.AbilityEvolutionEvent;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncAbilityDataPacket;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import com.yuanno.block_clover.spells.water.EvolvedWaterBallAbility;
import com.yuanno.block_clover.spells.water.WaterBallAbility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class AbilityEvolutionEvents {
    private static final Map<AbilityCore, AbilityCore> evolutionMap = new HashMap<>();

    static {
        evolutionMap.put(WaterBallAbility.INSTANCE, EvolvedWaterBallAbility.INSTANCE);
    }
    @SubscribeEvent
    public static void onSpellEvolution(AbilityEvolutionEvent event)
    {
        PlayerEntity player = event.getPlayer();
        if (player.level.isClientSide)
            return;

        AbilityCore baseSpell = event.getAbility().getCore();
        AbilityCore evolvedSpell = evolutionMap.get(baseSpell);

        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilityData.replaceAbility(baseSpell, evolvedSpell);

        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
    }
}

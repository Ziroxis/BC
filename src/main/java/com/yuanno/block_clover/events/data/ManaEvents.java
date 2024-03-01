package com.yuanno.block_clover.events.data;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.data.devil.DevilCapability;
import com.yuanno.block_clover.data.devil.IDevil;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncDevilPacket;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ManaEvents {

    @SubscribeEvent
    public static void regenerateMana(TickEvent.PlayerTickEvent event)
    {
        PlayerEntity player = event.player;
        if (player.level.isClientSide)
            return;
        IEntityStats propsEntity = EntityStatsCapability.get(player);
        IDevil devil = DevilCapability.get(player);
        float regen = propsEntity.getManaRegeneration();
        if (!(player.tickCount % 20 == 0))
            return;

        if (propsEntity.getMana() < propsEntity.getMaxMana())
            propsEntity.alterMana(regen);
        if (propsEntity.getAttribute().equals(ModValues.TIME) || propsEntity.getSecondAttribute().equals(ModValues.TIME) && propsEntity.getTime() < 1000)
            propsEntity.alterTime(1);
        if (devil.getDevilMana() < devil.getMaxDevilMana())
            devil.alterDevilMana(1);
        PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), propsEntity), player);
        PacketHandler.sendTo(new SSyncDevilPacket(player.getId(), devil), player);
    }
}

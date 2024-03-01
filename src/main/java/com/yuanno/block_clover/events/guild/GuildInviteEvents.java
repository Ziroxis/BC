package com.yuanno.block_clover.events.guild;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.data.world.ExtendedWorldData;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SOpenGuildScreenInvitationpacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GuildInviteEvents {

    @SubscribeEvent
    public static void rightClickEvent(PlayerInteractEvent.EntityInteract event)
    {

        World world = event.getWorld();
        if (world.isClientSide)
            return;
        Entity entityTarget = event.getTarget();
        ExtendedWorldData extendedWorldDataCaptain = ExtendedWorldData.get(event.getPlayer().level);
        boolean isInGuildCaptain = extendedWorldDataCaptain.getGuildWithMember(event.getPlayer().getUUID()) != null;
        if (!(entityTarget instanceof PlayerEntity)) // target is player
            return;

        if (!isInGuildCaptain)
            return;

        if (!extendedWorldDataCaptain.getGuildWithMember(event.getPlayer().getUUID()).getCaptain().getUUID().equals(event.getPlayer().getUUID())) // if he's not the captain return
            return;
        // server side; right click entity; is in guild; is captain
        PlayerEntity playerTargeted = (PlayerEntity) event.getTarget();
        ExtendedWorldData extendedWorldDataTarget = ExtendedWorldData.get(playerTargeted.level);
        boolean isInGuildTarget = extendedWorldDataTarget.getGuildWithMember(playerTargeted.getUUID()) != null;
        if (isInGuildTarget)
            return;

        PacketHandler.sendTo(new SOpenGuildScreenInvitationpacket(playerTargeted.getId(), event.getPlayer().getId()), playerTargeted);

    }
}

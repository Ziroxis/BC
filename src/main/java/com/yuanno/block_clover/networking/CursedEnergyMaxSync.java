package com.yuanno.block_clover.networking;

import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CursedEnergyMaxSync {
    private float maxCursedEnergy;
    private boolean toClient;

    public CursedEnergyMaxSync(float maxCursedEnergy, boolean toClient) {
        this.maxCursedEnergy = maxCursedEnergy;
        this.toClient = toClient;
    }

    public static void encode(CursedEnergyMaxSync msg, PacketBuffer buf) {
        buf.writeFloat(msg.maxCursedEnergy);
        buf.writeBoolean(msg.toClient);
    }

    public static CursedEnergyMaxSync decode(PacketBuffer buf) {
        float data = buf.readFloat();
        return new CursedEnergyMaxSync(data, buf.readBoolean());
    }

    public static void handle(CursedEnergyMaxSync msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (msg.toClient) {
                Minecraft mc = Minecraft.getInstance();
                IEntityStats playercap = mc.player.getCapability(EntityStatsCapability.INSTANCE).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                playercap.setMaxMana( msg.maxCursedEnergy);
            }
            else {
                PlayerEntity player= ctx.get().getSender();
                IEntityStats playercap = player.getCapability(EntityStatsCapability.INSTANCE).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                playercap.setMaxMana( msg.maxCursedEnergy);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}

package com.yuanno.block_clover.networking;

import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ManaSync {
    private float mana;

    public ManaSync(float cursedEnergy)
    {
        this.mana = mana;
    }

    public static void encode(ManaSync msg, PacketBuffer buf)
    {
        buf.writeFloat(msg.mana);
    }

    public static ManaSync decode(PacketBuffer buf)
    {
        float data = buf.readFloat();
        return new ManaSync(data);
    }

    public static void handle(ManaSync msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();
            IEntityStats playercap = mc.player.getCapability(EntityStatsCapability.INSTANCE).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
            playercap.setMana((float) msg.mana);
        });
        ctx.get().setPacketHandled(true);
    }
}

package com.yuanno.block_clover.networking.client;

import com.yuanno.block_clover.init.ModStructures;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CTeleportMagicTowerPacket {

    public CTeleportMagicTowerPacket()
    {

    }

    public void encode(PacketBuffer buffer)
    {
    }


    public static CTeleportMagicTowerPacket decode(PacketBuffer buffer)
    {
        CTeleportMagicTowerPacket msg = new CTeleportMagicTowerPacket();
        return msg;
    }

    public static void handle(CTeleportMagicTowerPacket message, final Supplier<NetworkEvent.Context> ctx)
    {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
        {
            ctx.get().enqueueWork(() ->
            {
                PlayerEntity player = ctx.get().getSender();
                World world = player.level;
                if (player.level.isClientSide)
                    return;
                if (!(world instanceof ServerWorld))
                    return;
                ServerWorld serverWorld = (ServerWorld) world;
                BlockPos pos = player.blockPosition();
                BlockPos tpPos = serverWorld.findNearestMapFeature(ModStructures.MAGICTOWER.get(), pos, 100, false);

                ChunkPos chunkPos = new ChunkPos(tpPos);
                serverWorld.getChunk(chunkPos.x, chunkPos.z);
                int y = world.getHeight(Heightmap.Type.WORLD_SURFACE, tpPos.getX(), tpPos.getZ());

                player.teleportTo(tpPos.getX(), y, tpPos.getZ());

            });
        }
        ctx.get().setPacketHandled(true);
    }
}

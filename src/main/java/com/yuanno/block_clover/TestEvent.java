package com.yuanno.block_clover;

import com.yuanno.block_clover.blocks.containers.DevilAltarContainer;
import com.yuanno.block_clover.blocks.tileentities.DevilAltarTileEntity;
import com.yuanno.block_clover.client.gui.screen.block.DevilAltarScreen;
import com.yuanno.block_clover.data.challenges.ChallengesDataCapability;
import com.yuanno.block_clover.data.challenges.IChallengesData;
import com.yuanno.block_clover.init.ModChallenges;
import com.yuanno.block_clover.init.ModConfiguredFeatures;
import com.yuanno.block_clover.init.ModStructures;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.COpenDevilSummoningScreenPacket;
import com.yuanno.block_clover.networking.server.SOpenDevilSummoningScreenpacket;
import com.yuanno.block_clover.networking.server.SOpenTeleportTowerScreenPacket;
import com.yuanno.block_clover.networking.server.SSyncChallengeDataPacket;
import com.yuanno.block_clover.world.structure.structures.MagicTowerStructure;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerChunkProvider;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkHooks;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class TestEvent {

    @SubscribeEvent
    public static void chatEventServer(ServerChatEvent event)
    {
        if (event.getMessage().equals("summoningritual"))
        {
            PlayerEntity player = event.getPlayer();
            IChallengesData challengesData = ChallengesDataCapability.get(player);
            PacketHandler.sendTo(new SSyncChallengeDataPacket(player.getId(), challengesData), player);
            PacketHandler.sendTo(new SOpenDevilSummoningScreenpacket(), player);
        }
        if (event.getMessage().equals("grimoire")) {
            PlayerEntity player = event.getPlayer();
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
        }
        if (event.getMessage().equals("test"))
        {
            PlayerEntity player = event.getPlayer();
            PacketHandler.sendTo(new SOpenTeleportTowerScreenPacket(), player);
        }

    }


}
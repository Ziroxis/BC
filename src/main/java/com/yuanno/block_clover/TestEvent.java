package com.yuanno.block_clover;

import com.yuanno.block_clover.blocks.containers.DevilAltarContainer;
import com.yuanno.block_clover.blocks.tileentities.DevilAltarTileEntity;
import com.yuanno.block_clover.client.gui.screen.block.DevilAltarScreen;
import com.yuanno.block_clover.data.challenges.ChallengesDataCapability;
import com.yuanno.block_clover.data.challenges.IChallengesData;
import com.yuanno.block_clover.init.ModChallenges;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.client.COpenDevilSummoningScreenPacket;
import com.yuanno.block_clover.networking.server.SOpenDevilSummoningScreenpacket;
import com.yuanno.block_clover.networking.server.SSyncChallengeDataPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
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
            challengesData.addChallenge(ModChallenges.WALGNER_DEVIL.get());
            PacketHandler.sendTo(new SSyncChallengeDataPacket(player.getId(), challengesData), player);
            PacketHandler.sendTo(new SOpenDevilSummoningScreenpacket(), player);
        }
        if (event.getMessage().equals("block")) {
            PlayerEntity player = event.getPlayer();

        }

    }


}
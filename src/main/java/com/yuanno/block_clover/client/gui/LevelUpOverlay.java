package com.yuanno.block_clover.client.gui;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.events.levelEvents.ExperienceEvents;
import com.yuanno.block_clover.events.levelEvents.ExperienceUpEvent;
import com.yuanno.block_clover.events.levelEvents.LevelUpEvent;
import io.netty.util.Timer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.system.CallbackI;

import javax.swing.*;
import java.sql.Time;
import java.time.LocalDateTime;

import static net.minecraft.client.gui.AbstractGui.drawString;
@OnlyIn(Dist.CLIENT)
public class LevelUpOverlay extends AbstractGui {
    int previousLevel = 1;
    int tickCount;
    int count = 0;
    int doubleCount;
    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent.Post event)
    {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        assert player != null;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        String levelUp = "You leveled up!";
        if (previousLevel < entityStats.getLevel())
        {


            drawString(event.getMatrixStack(), Minecraft.getInstance().font, TextFormatting.BOLD + levelUp, 310, 30, 20);
            int tickCountPlus = player.tickCount;
            if ((tickCountPlus - tickCount) > 50)
            {
                previousLevel = entityStats.getLevel();
            }
            /*
            for (int i = 0; i<12000; i++)
            {
                drawString(event.getMatrixStack(), Minecraft.getInstance().font, TextFormatting.BOLD + levelUp, 310, 30, 20);
                if (i == 11999)
                    previousLevel = entityStats.getLevel();
            }

             */
        }
        else
        {
            tickCount = player.tickCount;

        }
    }
}

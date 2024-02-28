package com.yuanno.block_clover.client.gui.overlay;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.data.quest.IQuestData;
import com.yuanno.block_clover.data.quest.QuestDataCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Arrays;

@OnlyIn(Dist.CLIENT)
public class QuestOverlay extends AbstractGui {
    @SubscribeEvent
    public void renderQuestOverlay(RenderGameOverlayEvent.Post event) {

        ClientPlayerEntity player = Minecraft.getInstance().player;
        IQuestData questData = QuestDataCapability.get(player);
        MatrixStack matrixStack = event.getMatrixStack();
        System.out.println(questData.getInProgressQuests());
        boolean allNull = true;
        for (Quest quest : questData.getInProgressQuests()) {
            if (quest != null) {
                allNull = false;
                break;
            }
        }

        if (allNull) {
            return;
        }
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT)
        {
            for (int i = 0; i < questData.getInProgressQuests().length; i++) {
                drawString(matrixStack, Minecraft.getInstance().font, TextFormatting.BOLD + questData.getInProgressQuests()[i].getCore().getName(), 290, 10 + (i * 40), -1);
                matrixStack.pushPose();
                matrixStack.scale(0.7f, 0.7f, 0.7f);
                for (int ia = 0; ia < questData.getInProgressQuests()[i].getObjectives().size(); ia++) {
                    drawString(matrixStack, Minecraft.getInstance().font, TextFormatting.RESET + questData.getInProgressQuests()[i].getObjectives().get(ia).getTitle(), (int) (310 / 0.7), (int) ((30 + (i * 15)) / 0.7), -1);
                }
                matrixStack.popPose();
            }
        }
    }

}

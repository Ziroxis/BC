package com.yuanno.block_clover.client.overlay.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.client.overlay.model.ExclamationModel;
import com.yuanno.block_clover.data.quest.IQuestData;
import com.yuanno.block_clover.data.quest.QuestDataCapability;
import com.yuanno.block_clover.entities.NPCentity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class ExclamationRenderer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID + ":textures/entities/layers/exclamation.png");

    private ExclamationModel model = new ExclamationModel();

    public ExclamationRenderer(IEntityRenderer<T, M> entityRenderer)
    {
        super(entityRenderer);
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        /*
        PlayerEntity player = Minecraft.getInstance().player;
        //TODO fix this shit
        if (!(entity instanceof NPCentity))
            return;
        boolean flag = false;
        NPCentity npCentity = (NPCentity) entity;

        IQuestData questData = QuestDataCapability.get(player);
        ArrayList<String> questCompleteStrings = new ArrayList<String>();
        for (int i = 0; i < questData.getInProgressQuests().length; i++)
        {
            if (questData.getInProgressQuest(i) != null && questData.getInProgressQuest(i).isComplete() && !questCompleteStrings.contains(questData.getInProgressQuest(i).getId()))
                questCompleteStrings.add(questData.getInProgressQuest(i).getId());
        }
        ArrayList<String> questsString = new ArrayList<String>();
        for (int i = 0; i < npCentity.quests.size(); i++)
        {
            questsString.add(npCentity.quests.get(i).getId());
        }
        for (String s : questsString) {
            flag = questCompleteStrings.contains(s);
        }
        if (!flag)
        {
            matrixStack.pushPose();
            this.getParentModel().copyPropertiesTo(this.model);
            this.model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            IVertexBuilder ivertexbuilder = ItemRenderer.getFoilBuffer(buffer, this.model.renderType(TEXTURE), false, false);
            this.model.renderToBuffer(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStack.popPose();
        }

         */
    }




}

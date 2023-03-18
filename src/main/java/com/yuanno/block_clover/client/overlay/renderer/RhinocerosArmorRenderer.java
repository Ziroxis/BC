package com.yuanno.block_clover.client.overlay.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.client.overlay.model.ManaSkinModel;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.spells.beast.RhinocerosArmorAbility;
import com.yuanno.block_clover.spells.misc.ManaSkinAbility;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;

public class RhinocerosArmorRenderer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {

    private ResourceLocation TEXTURE = new ResourceLocation(Main.MODID + ":textures/entities/layers/manaskin/rhinocerosarmor.png");

    private ManaSkinModel model = new ManaSkinModel();

    public RhinocerosArmorRenderer(IEntityRenderer<T, M> entityRenderer)
    {
        super(entityRenderer);
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entityLivingBase, float limbSwing, float limbSwingAmount,
                       float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
    {
        IEntityStats entityStats = EntityStatsCapability.get(entityLivingBase);
        IAbilityData abilityData = AbilityDataCapability.get(entityLivingBase);
        RhinocerosArmorAbility manaSkinAbility = (RhinocerosArmorAbility) abilityData.getEquippedAbility(ManaSkinAbility.INSTANCE);
        if (manaSkinAbility != null && manaSkinAbility.isContinuous())
        {
            matrixStack.pushPose();
            this.getParentModel().copyPropertiesTo(this.model);
            this.model.setupAnim(entityLivingBase, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            IVertexBuilder ivertexbuilder = ItemRenderer.getFoilBuffer(buffer, this.model.renderType(TEXTURE), false, false);
            this.model.renderToBuffer(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStack.popPose();

        }
    }
}

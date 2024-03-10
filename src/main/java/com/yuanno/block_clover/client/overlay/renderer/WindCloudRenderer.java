package com.yuanno.block_clover.client.overlay.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.client.overlay.model.EarthManipulationModel;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.spells.earth.EarthManipulationAbility;
import com.yuanno.block_clover.spells.wind.WindCloudAbility;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;

public class WindCloudRenderer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID + ":textures/entities/projectiles/wind/windblade.png");

    private EarthManipulationModel model = new EarthManipulationModel();

    public WindCloudRenderer(IEntityRenderer<T, M> entityRenderer)
    {
        super(entityRenderer);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn,
                       float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
    {
        IAbilityData abilityData = AbilityDataCapability.get(entitylivingbaseIn);

        for (Ability ability : abilityData.getEquippedAbilities(AbilityCategories.AbilityCategory.ALL))
        {
            if (ability == null)
                continue;

            try
            {
                if (ability instanceof WindCloudAbility && ((WindCloudAbility) ability).isContinuous() && !entitylivingbaseIn.isOnGround())
                {
                    matrixStackIn.pushPose();
                    this.getParentModel().copyPropertiesTo(this.model);
                    this.model.setupAnim(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                    IVertexBuilder ivertexbuilder = ItemRenderer.getFoilBuffer(bufferIn, this.model.renderType(TEXTURE), false, false);
                    this.model.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
                    matrixStackIn.popPose();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}

package com.yuanno.block_clover.client.overlay.renderer;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.client.overlay.model.ThunderGodGearModel;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.spells.beast.BearClawAbility;
import com.yuanno.block_clover.spells.lightning.ThunderGodBootsAbility;
import com.yuanno.block_clover.spells.lightning.ThunderGodGlovesAbility;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;

public class ThunderGodGearRenderer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID + ":textures/entities/layers/spells/thundergodgear.png");

    private ThunderGodGearModel model = new ThunderGodGearModel();

    public ThunderGodGearRenderer(IEntityRenderer<T, M> entityRenderer)
    {
        super(entityRenderer);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn,
                       float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
    {
        // bandits can also use this
        /*
        if (!(entitylivingbaseIn instanceof PlayerEntity))
            return;
         */

        IAbilityData abilityData = AbilityDataCapability.get(entitylivingbaseIn);

        for (Ability ability : abilityData.getEquippedAbilities(AbilityCategories.AbilityCategory.ALL))
        {
            if (ability == null)
                continue;

            try
            {
                if (ability instanceof ThunderGodBootsAbility && ((ThunderGodBootsAbility) ability).isContinuous())
                    model.showBoots = true;
                else if (ability instanceof ThunderGodBootsAbility && ability.isOnCooldown())
                    model.showBoots = false;
                if (ability instanceof ThunderGodGlovesAbility && ((ThunderGodGlovesAbility) ability).isContinuous())
                    model.showGloves = true;
                else if(ability instanceof ThunderGodGlovesAbility && ability.isOnCooldown())
                    model.showGloves = false;

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        matrixStackIn.pushPose();
        this.getParentModel().copyPropertiesTo(this.model);
        this.model.setupAnim(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        IVertexBuilder ivertexbuilder = ItemRenderer.getFoilBuffer(bufferIn, this.model.renderType(TEXTURE), false, false);
        this.model.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.popPose();

    }

}

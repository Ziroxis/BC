package com.yuanno.block_clover.client.overlay.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.client.overlay.model.BearClawModel;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.effects.FreezeEffect;
import com.yuanno.block_clover.init.ModEffects;
import com.yuanno.block_clover.init.ModRenderTypes;
import com.yuanno.block_clover.spells.beast.BearClawAbility;
import com.yuanno.block_clover.spells.misc.ManaSenseAbility;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OutlineLayerBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.util.Optional;

public class FrozenRenderer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {

    public FrozenRenderer(IEntityRenderer entityRenderer)
    {
        super(entityRenderer);
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int packedLightIn, T entity,
                       float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
    {

        Optional<EffectInstance> inst = entity.getActiveEffects().stream().filter(e -> e.getEffect() instanceof FreezeEffect
                && entity.hasEffect(e.getEffect())).findFirst();

        if (inst.isPresent())
        {
            matrixStack.pushPose();

            String color = "#0000FF";


            OutlineLayerBuffer outline = Minecraft.getInstance().renderBuffers().outlineBufferSource();
            Color rgbColor = Beapi.hexToRGB(color);
            float red = rgbColor.getRed() / 255.0f;
            float green = rgbColor.getGreen() / 255.0f;
            float blue = rgbColor.getBlue() / 255.0f;
            outline.setColor((int)(red * 255), (int)(green * 255), (int)(blue * 255), 200);
            IVertexBuilder vertex = outline.getBuffer(ModRenderTypes.getAuraRenderType(this.getTextureLocation(entity)));

            this.getParentModel().renderToBuffer(matrixStack, vertex, packedLightIn, OverlayTexture.NO_OVERLAY, red, green, blue, 0.6f);

            matrixStack.popPose();
        }
    }

}

package com.yuanno.block_clover.effects;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.init.ModAttributes;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.time.ChronoStasisParticleEffect;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeMod;

import java.util.UUID;

public class WindUpEffect extends SpecialEffect {
    /*
    public static final AttributeModifier GRAVITY = new AttributeModifier(UUID.fromString("f4a8d3d2-de56-11ee-bd3d-0242ac120002"),
            "GravityStuff", -0.03, AttributeModifier.Operation.ADDITION);

     */
    public WindUpEffect()
    {
        super(EffectType.BENEFICIAL, Beapi.hexToRGB("#000000").getRGB());
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "2727e176-e9e8-4523-92f8-22619308d9ee", 0.2, AttributeModifier.Operation.ADDITION)
                .addAttributeModifier(ModAttributes.STEP_HEIGHT.get(), "e8cd65cb-2768-4fd8-aa54-bdcda029aaff", 1, AttributeModifier.Operation.ADDITION)
                .addAttributeModifier(ForgeMod.ENTITY_GRAVITY.get(), "f4a8d3d2-de56-11ee-bd3d-0242ac120002", -0.03, AttributeModifier.Operation.ADDITION)
                .addAttributeModifier(Attributes.ATTACK_SPEED, "f8b2474d-4cdb-42b0-a868-327443a2a505", 0.5, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier)
    {
        if (livingEntity.level.isClientSide)
            return;
        /*
        if (!livingEntity.isOnGround())
        {
            if (!livingEntity.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).hasModifier(GRAVITY))
                livingEntity.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).addTransientModifier(GRAVITY);
        }
        else {
            if (livingEntity.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).hasModifier(GRAVITY))
                livingEntity.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).removeModifier(GRAVITY);
        }

         */
    }
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier)
    {
        return true;
    }
    @Override
    public boolean shouldRender(EffectInstance effect)
    {
        return false;
    }

    @Override
    public boolean shouldRenderHUD(EffectInstance effect)
    {
        return false;
    }

    @Override
    public float[] getOverlayColor()
    {
        return new float[] { 0.0f, 0.0f, 0.0f, 1f };
    }

    @Override
    public boolean hasBodyOverlayColor() {
        return false;
    }

    @Override
    public Block getBlockOverlay() {
        return null;
    }

    @Override
    public boolean isBlockingMovement() {
        return true;
    }

    @Override
    public ResourceLocation getResourceLocation(int duration) {
        return null;
    }


}

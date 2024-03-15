package com.yuanno.block_clover.effects;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ModEffect;
import com.yuanno.block_clover.init.ModAttributes;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.fire.LeoPalmaParticleEffect;
import com.yuanno.block_clover.particles.wind.WhirlwindParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraftforge.common.ForgeMod;

public class WhirlwindEffect extends ModEffect {
    private static final ParticleEffect PARTICLES = new WhirlwindParticleEffect();
    public WhirlwindEffect()
    {
        super(EffectType.HARMFUL, Beapi.hexToRGB("#000000").getRGB());
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "44c4f6ea-e30f-11ee-bd3d-0242ac120002", -256, AttributeModifier.Operation.ADDITION)
                .addAttributeModifier(ForgeMod.SWIM_SPEED.get(), "4a7e6576-e30f-11ee-bd3d-0242ac120002", -256, AttributeModifier.Operation.ADDITION)
                .addAttributeModifier(ModAttributes.JUMP_HEIGHT.get(), "4d6dbb6a-e30f-11ee-bd3d-0242ac120002", -256, AttributeModifier.Operation.ADDITION)
                .addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, "503e3d38-e30f-11ee-bd3d-0242ac120002", 100, AttributeModifier.Operation.ADDITION)
                .addAttributeModifier(Attributes.ARMOR_TOUGHNESS, "52dde6a6-e30f-11ee-bd3d-0242ac120002", -200, AttributeModifier.Operation.ADDITION);

    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier)
    {
        if (!livingEntity.level.isClientSide)
        {
            PARTICLES.spawn(livingEntity.level, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 0, 0, 0);
        }
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
    public boolean isBlockingRotations() {
        return true;
    }


    @Override
    public boolean isRemoveable() {
        return false;
    }

    @Override
    public boolean shouldUpdateClient() {
        return true;
    }


}

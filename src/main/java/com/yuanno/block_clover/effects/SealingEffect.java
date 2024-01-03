package com.yuanno.block_clover.effects;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.init.ModAttributes;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.fire.LeoPalmaParticleEffect;
import com.yuanno.block_clover.particles.sealing.SealingParticleEffect;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeMod;


public class SealingEffect extends SpecialEffect {
    private static final ParticleEffect PARTICLES = new SealingParticleEffect();

    public SealingEffect()
    {
        super(EffectType.HARMFUL, Beapi.hexToRGB("#000000").getRGB());
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "2727e176-e9e8-4523-92f8-22619308d9ee", -256, AttributeModifier.Operation.ADDITION)
                .addAttributeModifier(ForgeMod.SWIM_SPEED.get(), "323ffb58-0b57-434e-bdfc-354670e22d5f", -256, AttributeModifier.Operation.ADDITION)
                .addAttributeModifier(ModAttributes.JUMP_HEIGHT.get(), "e8cd65cb-2768-4fd8-aa54-bdcda029aaff", -256, AttributeModifier.Operation.ADDITION)
                .addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, "f8b2474d-4cdb-42b0-a868-327443a2a505", 100, AttributeModifier.Operation.ADDITION);
    }


    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier)
    {
        if (livingEntity instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) livingEntity;
            IAbilityData abilityData = AbilityDataCapability.get(player);
            for (Ability ability : abilityData.getEquippedAbilities(AbilityCategories.AbilityCategory.ALL))
            {
                if (ability == null)
                    continue;
                if (!ability.isDisabled())
                    ability.startDisable(20);
            }
        }
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

    @Override
    public boolean shouldRenderHUD(EffectInstance effect)
    {
        return false;
    }
}

package com.yuanno.block_clover.effects;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ModEffect;
import com.yuanno.block_clover.init.ModAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraftforge.common.ForgeMod;

public class BubbledEffect extends ModEffect {

    public BubbledEffect()
    {
        super(EffectType.HARMFUL, Beapi.hexToRGB("#000000").getRGB());
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "2727e176-e9e8-4523-92f8-22619308d9ee", -4, AttributeModifier.Operation.ADDITION)
                .addAttributeModifier(ModAttributes.JUMP_HEIGHT.get(), "e8cd65cb-2768-4fd8-aa54-bdcda029aaff", -4, AttributeModifier.Operation.ADDITION)
                .addAttributeModifier(Attributes.ATTACK_SPEED, "910ef02a-34b2-11ee-be56-0242ac120002", -4, AttributeModifier.Operation.ADDITION);

    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier)
    {
        if (!livingEntity.level.isClientSide)
        {
            if (livingEntity.tickCount % 20 == 0)
            {
                int airSupply = livingEntity.getAirSupply() - 1;
                livingEntity.setAirSupply(airSupply);
            }
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

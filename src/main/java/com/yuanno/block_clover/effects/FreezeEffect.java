package com.yuanno.block_clover.effects;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ModEffect;
import com.yuanno.block_clover.init.ModAttributes;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeMod;

public class FreezeEffect extends ModEffect {

    public FreezeEffect()
    {
        super(EffectType.HARMFUL, Beapi.hexToRGB("#000000").getRGB());
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "2727e176-e9e8-4523-92f8-22619308d9ee", -256, AttributeModifier.Operation.ADDITION)
                .addAttributeModifier(ForgeMod.SWIM_SPEED.get(), "323ffb58-0b57-434e-bdfc-354670e22d5f", -256, AttributeModifier.Operation.ADDITION)
                .addAttributeModifier(ModAttributes.JUMP_HEIGHT.get(), "e8cd65cb-2768-4fd8-aa54-bdcda029aaff", -256, AttributeModifier.Operation.ADDITION)
                .addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, "f8b2474d-4cdb-42b0-a868-327443a2a505", 100, AttributeModifier.Operation.ADDITION)
                .addAttributeModifier(Attributes.ATTACK_SPEED, "910ef02a-34b2-11ee-be56-0242ac120002", -256, AttributeModifier.Operation.ADDITION)
                .addAttributeModifier(Attributes.ARMOR_TOUGHNESS, "096ae850-ca45-11ec-9d64-0242ac120002", -200, AttributeModifier.Operation.ADDITION);

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

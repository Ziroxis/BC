package com.yuanno.block_clover.items.artifacts;

import com.yuanno.block_clover.api.curios.SlotContext;
import com.yuanno.block_clover.api.curios.type.capability.ICurioItem;
import com.yuanno.block_clover.items.ArtifactItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class BraceletOfPushArtifactItem extends ArtifactItem implements ICurioItem {

    public BraceletOfPushArtifactItem()
    {
        this.artifactInformation = "This bracelet pushes anyone that you touch with";
    }

    private static final AttributeModifier KNOCKBACK = new AttributeModifier(UUID.fromString("999d353c-1af3-11ed-861d-0242ac120002"),
            "Lightning Attack", 2, AttributeModifier.Operation.ADDITION);
    @Override
    public void onEquip(SlotContext slotContext, ItemStack previousStack, ItemStack currentStack)
    {
        LivingEntity livingEntity = slotContext.getWearer();
        if (livingEntity instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) livingEntity;
            if (!player.getAttribute(Attributes.ATTACK_KNOCKBACK).hasModifier(KNOCKBACK))
            {
                player.getAttribute(Attributes.ATTACK_KNOCKBACK).addTransientModifier(KNOCKBACK);
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack oldStack)
    {
        LivingEntity livingEntity = slotContext.getWearer();
        if (livingEntity instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) livingEntity;
            if (player.getAttribute(Attributes.ATTACK_KNOCKBACK).hasModifier(KNOCKBACK))
            {
                player.getAttribute(Attributes.ATTACK_KNOCKBACK).removeModifier(KNOCKBACK);
            }
        }
    }


}

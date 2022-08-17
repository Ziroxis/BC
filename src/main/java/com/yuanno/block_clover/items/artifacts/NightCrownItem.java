package com.yuanno.block_clover.items.artifacts;

import com.yuanno.block_clover.api.curios.CuriosApi;
import com.yuanno.block_clover.api.curios.SlotTypePreset;
import com.yuanno.block_clover.api.curios.type.capability.ICurioItem;
import com.yuanno.block_clover.items.ArtifactItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class NightCrownItem extends ArtifactItem implements ICurioItem {

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {

        PlayerEntity player = (PlayerEntity) livingEntity;
        if (!livingEntity.getEntity().level.isClientSide && livingEntity.tickCount % 20 == 0)
        {
            livingEntity.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 40, 1, true, true));
            stack.hurtAndBreak(1, player,
                    damager -> CuriosApi.getCuriosHelper().onBrokenCurio(SlotTypePreset.RING.getIdentifier(), index, damager));
        }
    }
}

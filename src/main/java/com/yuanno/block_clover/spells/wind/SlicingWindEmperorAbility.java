package com.yuanno.block_clover.spells.wind;

import com.yuanno.block_clover.api.ability.AbilityCategories;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.block_clover.api.ability.sorts.ContinuousAbility;
import com.yuanno.block_clover.init.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Util;
import net.minecraft.util.text.TranslationTextComponent;

public class SlicingWindEmperorAbility extends ContinuousAbility implements IParallelContinuousAbility {
    public static final AbilityCore INSTANCE = new AbilityCore.Builder("Slicing Wind Emperor", AbilityCategories.AbilityCategory.ATTRIBUTE, SlicingWindEmperorAbility.class)
            .setDescription("Uses a stick to create a wind sword")
            .build();

    public SlicingWindEmperorAbility()
    {
        super(INSTANCE);
        this.setExperiencePoint(5);
        this.setExperienceGainLevelCap(25);
        this.setMaxCooldown(0);
        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.duringContinuityEvent = this::duringContinuity;
        this.onEndContinuityEvent = this::onEndContinuity;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        if (!player.getMainHandItem().getItem().equals(Items.STICK))
        {
            player.sendMessage(new TranslationTextComponent("Need to hold a stick!"), Util.NIL_UUID);
            return false;
        }
        else
        {
            player.inventory.setItem(player.inventory.selected, new ItemStack(ModItems.WIND_SWORD.get()));
        }
        return true;
    }

    private void duringContinuity(PlayerEntity player, int timer)
    {
        if (!player.getMainHandItem().getItem().equals(ModItems.WIND_SWORD.get()))
        {
            player.sendMessage(new TranslationTextComponent("Need to hold your sword!"), Util.NIL_UUID);
            this.endContinuity(player);
        }
    }

    private boolean onEndContinuity(PlayerEntity player)
    {
        for (int i = 0; i < 35; i++)
        {
            if (player.inventory.getItem(i).getItem().equals(ModItems.WIND_SWORD.get()))
                player.inventory.setItem(i, new ItemStack(Items.STICK));
        }
        return true;
    }
}

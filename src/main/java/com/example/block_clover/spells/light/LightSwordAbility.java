package com.example.block_clover.spells.light;

import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.example.block_clover.api.ability.sorts.ItemAbility;
import com.example.block_clover.init.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class LightSwordAbility extends ItemAbility implements IParallelContinuousAbility {

    public static final LightSwordAbility INSTANCE = new LightSwordAbility();

    public LightSwordAbility()
    {
        super("Light Sword", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Creates a sharp blade made out of solid light");
        this.setExperiencePoint(3);
        this.setMaxCooldown(0);
        this.setmanaCost(4);
        //this.setPassiveExperience(3);
        //this.setPassiveManaCost(3);
    }


    @Override
    public ItemStack getItemStack(PlayerEntity player)
    {
        return new ItemStack(ModItems.LIGHT_SWORD.get());
    }

    @Override
    public boolean canBeActive(PlayerEntity player) {
        return true;
    }
}

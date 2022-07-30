package com.example.block_clover.spells.light;

import com.example.block_clover.api.ability.AbilityCategories;
import com.example.block_clover.api.ability.interfaces.IParallelContinuousAbility;
import com.example.block_clover.api.ability.sorts.ItemAbility;
import com.example.block_clover.init.ModItems;
import com.example.block_clover.particles.ParticleEffect;
import com.example.block_clover.particles.light.LightSwordParticleEffect;
import com.example.block_clover.particles.lightning.BootsParticleEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class LightSwordAbility extends ItemAbility implements IParallelContinuousAbility {
    private static final ParticleEffect PARTICLES = new LightSwordParticleEffect();
    public static final LightSwordAbility INSTANCE = new LightSwordAbility();

    public LightSwordAbility()
    {
        super("Light Sword", AbilityCategories.AbilityCategory.ATTRIBUTE);
        this.setDescription("Creates a sharp blade made out of solid light");
        this.setExperiencePoint(3);
        this.setMaxCooldown(0);
        this.setmanaCost(4);
        this.duringContinuityEvent = this::duringContinuityEvent;
        //this.setPassiveExperience(3);
        //this.setPassiveManaCost(3);
    }


    @Override
    public ItemStack getItemStack(PlayerEntity player)
    {
        return new ItemStack(ModItems.LIGHT_SWORD.get());
    }


    private void duringContinuityEvent(PlayerEntity player, int timer)
    {
        if (player.getMainHandItem().getItem().equals(ModItems.LIGHT_SWORD.get()))
            PARTICLES.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0);

    }

    @Override
    public boolean canBeActive(PlayerEntity player) {
        return true;
    }
}

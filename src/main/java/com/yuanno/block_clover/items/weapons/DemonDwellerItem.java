package com.yuanno.block_clover.items.weapons;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import com.yuanno.block_clover.init.ModItemGroup;
import com.yuanno.block_clover.init.ModTiers;
import com.yuanno.block_clover.items.MagicWeapon;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DemonDwellerItem extends MagicWeapon {

    public DemonDwellerItem() {
        super(ModTiers.WEAPON, 7, 1f, new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_WEAPONS).stacksTo(1));
    }
    private static final int COOLDOWN_TICKS = 60; // 20 ticks is equivalent to 1 second
    private static final int MAX_STORED_PROJECTILES = 1; // Only store 1 projectile at a time
    private List<ProjectileEntity> storedProjectiles = new ArrayList<>();
    public boolean isBeingUsed = false;
    public AbilityProjectileEntity projectile;
    public int currentCooldownTicks;
    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        return ActionResult.consume(itemstack);
    }
    @Override
    public UseAction getUseAnimation(ItemStack p_77661_1_) {
        return UseAction.BLOCK;
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        // Decrement cooldown ticks every tick
        if (currentCooldownTicks > 0) {
            currentCooldownTicks--;
        }
    }
}
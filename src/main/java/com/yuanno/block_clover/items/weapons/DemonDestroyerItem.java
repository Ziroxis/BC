package com.yuanno.block_clover.items.weapons;

import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.init.ModItemGroup;
import com.yuanno.block_clover.init.ModTiers;
import com.yuanno.block_clover.items.MagicWeapon;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class DemonDestroyerItem extends MagicWeapon {

    // bulky sword that cures you and others
    public DemonDestroyerItem() {
        super(ModTiers.WEAPON, 7, 1f, new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_WEAPONS).stacksTo(1));
    }

    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker)
    {
        if (target instanceof PlayerEntity)
        {
            PlayerEntity targetPlayer = (PlayerEntity) target;
            targetPlayer.removeAllEffects();
        }
        return super.hurtEnemy(itemStack, target, attacker);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClientSide) {
            player.removeAllEffects();
        }
        return super.use(world, player, hand);
    }
}

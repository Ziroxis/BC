package com.yuanno.block_clover.items.weapons;

import com.yuanno.block_clover.init.ModItemGroup;
import com.yuanno.block_clover.init.ModTiers;
import com.yuanno.block_clover.items.MagicWeapon;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DemonDwellerItem extends MagicWeapon {

    public DemonDwellerItem() {
        super(ModTiers.WEAPON, 7, 1f, new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_WEAPONS).stacksTo(1));
    }
    private static final int COOLDOWN_TICKS = 20; // 20 ticks is equivalent to 1 second
    private int cooldown = 0;
    private static final int MAX_STORED_PROJECTILES = 1; // Only store 1 projectile at a time
    private List<ProjectileEntity> storedProjectiles = new ArrayList<>();

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (!world.isClientSide()) {
            if (player.isUsingItem() && player.getUsedItemHand() == hand && player.getUseItem() != null && player.getUseItem().getItem() instanceof SwordItem) {
                // Look for all projectile entities within a 2 block radius of the player
                List<Entity> entities = world.getEntitiesOfClass(Entity.class, new AxisAlignedBB(player.getX() - 2, player.getY() - 2, player.getZ() - 2, player.getX() + 2, player.getY() + 2, player.getZ() + 2));
                for (Entity entity : entities) {
                    if (entity instanceof ProjectileEntity && entity.isAlive() && !entity.removed && entity.getBoundingBox().intersects(player.getBoundingBox())) {
                        // Remove the projectile
                        entity.remove();
                        break;
                    }
                }
            }
        }

        return ActionResult.pass(itemStack);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClientSide() && cooldown > 0) {
            cooldown--;
        }
    }
}
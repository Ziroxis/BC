package com.yuanno.block_clover.spells.sword;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.items.weapons.DemonDwellerItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = "block_clover", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DemonDwellerEvent {
    //TODO sucking up part works just the rethrowing parts needs some work
    private boolean isBlocking = false;
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (!(event.getEntityLiving() instanceof PlayerEntity))
            return;
        if (event.getSource().getEntity() == null)
            return;
        PlayerEntity player = (PlayerEntity) event.getEntityLiving();
        if (event.getSource().getDirectEntity() instanceof ProjectileEntity) {
            if (player.getMainHandItem().getItem().asItem() instanceof DemonDwellerItem) {
                DemonDwellerItem demonDwellerItem = (DemonDwellerItem) player.getMainHandItem().getItem();
                System.out.println(demonDwellerItem.currentCooldownTicks);
                if (demonDwellerItem.isBeingUsed)
                // sucks up arrow if cooldown = 0 and the item is being used
                {
                    demonDwellerItem.projectile = (ProjectileEntity) event.getSource().getDirectEntity();
                    event.getSource().getDirectEntity().remove();
                    event.setCanceled(true);
                    demonDwellerItem.currentCooldownTicks = 60;
                }

            }
            else if (player.getOffhandItem().getItem().asItem() instanceof DemonDwellerItem)

            {
                DemonDwellerItem demonDwellerItem = (DemonDwellerItem) player.getOffhandItem().getItem();
                if (demonDwellerItem.isBeingUsed)
                // sucks up arrow if cooldown = 0 and the item is being used
                {
                    demonDwellerItem.projectile = (ProjectileEntity) event.getSource().getDirectEntity();
                    event.getSource().getDirectEntity().remove();
                    event.setCanceled(true);
                    demonDwellerItem.currentCooldownTicks = 60;
                }

            }
        }
    }

    @SubscribeEvent
    public static void onPlayerRightClick(PlayerInteractEvent.RightClickItem event) {
        PlayerEntity player = event.getPlayer();
        World world = player.level;
        ItemStack mainHandItem = player.getMainHandItem();
        ItemStack offHandItem = player.getOffhandItem();
        ItemStack itemUsed = event.getItemStack();
        double range = 10.0; // The range in blocks for deleting projectiles
        if (mainHandItem.getItem() instanceof DemonDwellerItem && mainHandItem == itemUsed) {
            DemonDwellerItem demonDwellerItem = (DemonDwellerItem) player.getMainHandItem().getItem();
            System.out.println(demonDwellerItem.projectile);
            if (demonDwellerItem.projectile != null && demonDwellerItem.currentCooldownTicks == 0)
            // shoots projectile that you sucked up if cooldown is ready
            {
                ProjectileEntity projectile = demonDwellerItem.projectile;
                player.level.addFreshEntity(projectile);
                demonDwellerItem.projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 2f, 1);
                demonDwellerItem.projectile = null;
                demonDwellerItem.currentCooldownTicks = 60;
                return;
            }
            boolean isFinished = event.isCanceled() || event.getItemStack().isEmpty() || event.getResult() == Event.Result.DENY;
            if (isFinished) {
                demonDwellerItem.isBeingUsed = false;
                return;
            }
            if (demonDwellerItem.currentCooldownTicks == 0)
                demonDwellerItem.isBeingUsed = true;
        }
        else if (offHandItem.getItem() instanceof DemonDwellerItem && offHandItem == itemUsed)
        {
            DemonDwellerItem demonDwellerItem = (DemonDwellerItem) player.getOffhandItem().getItem();
            if (demonDwellerItem.projectile != null && demonDwellerItem.currentCooldownTicks == 0)
            // when projectile sucked up and cooldown ready
            {
                ProjectileEntity projectile = demonDwellerItem.projectile;
                player.level.addFreshEntity(projectile);
                demonDwellerItem.projectile.shootFromRotation(player, player.xRot, player.yRot, 0, 2f, 1);
                demonDwellerItem.projectile = null;
                demonDwellerItem.currentCooldownTicks = 60;
                return;
            }
            boolean isFinished = event.isCanceled() || event.getItemStack().isEmpty() || event.getResult() == Event.Result.DENY;
            if (isFinished) {
                demonDwellerItem.isBeingUsed = false;
                return;
            }
            demonDwellerItem.isBeingUsed = true;
        }
    }






}

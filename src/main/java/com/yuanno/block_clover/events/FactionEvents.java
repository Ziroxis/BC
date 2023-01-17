package com.yuanno.block_clover.events;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.FactionHelper;
import com.yuanno.block_clover.api.SetOnFireEvent;
import com.yuanno.block_clover.events.projectiles.ProjectileHitEvent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class FactionEvents {

    @SubscribeEvent
    public static void onEntityAttack(LivingHurtEvent event)
    {
        if (event.getSource().getEntity() instanceof PlayerEntity)
        {
            PlayerEntity attacker = (PlayerEntity) event.getSource().getEntity();
            LivingEntity target = event.getEntityLiving();

            boolean sameGroup = FactionHelper.getSameGroupPredicate(attacker).test(target);
            if (sameGroup)
            {
                event.setCanceled(true);
                return;
            }
        }
    }

    @SubscribeEvent
    public static void onProjectileHit(ProjectileHitEvent event)
    {
        if (event.getProjectile().getOwner() instanceof PlayerEntity && event.getHit().getType() == RayTraceResult.Type.ENTITY)
        {
            PlayerEntity attacker = (PlayerEntity) event.getProjectile().getOwner();
            EntityRayTraceResult hitResult = (EntityRayTraceResult) event.getHit();

            if (hitResult.getEntity() instanceof LivingEntity)
            {
                LivingEntity target = (LivingEntity) hitResult.getEntity();

                boolean sameGroup = FactionHelper.getSameGroupPredicate(attacker).test(target);
                if (sameGroup)
                {
                    event.setCanceled(true);
                    return;
                }
            }
        }
    }

    @SubscribeEvent
    public static void onSetFire(SetOnFireEvent event)
    {
        if (event.getAttacker() instanceof PlayerEntity)
        {
            PlayerEntity attacker = (PlayerEntity) event.getAttacker();
            LivingEntity target = event.getEntityLiving();

            boolean sameGroup = FactionHelper.getSameGroupPredicate(attacker).test(target);
            if (sameGroup)
            {
                event.setCanceled(true);
                return;
            }
        }
    }

    @SubscribeEvent
    public static void onTargetSet(LivingSetAttackTargetEvent event)
    {
        if(event.getTarget() instanceof PlayerEntity)
        {
            boolean sameGroup = FactionHelper.getSameGroupPredicate(event.getTarget()).test(event.getEntityLiving());
            if(sameGroup)
            {
                if(event.getEntityLiving() instanceof MobEntity)
                    ((MobEntity)event.getEntityLiving()).setTarget(null);
                event.getEntityLiving().setLastHurtByPlayer(null);
                event.getEntityLiving().setLastHurtByMob(null);
            }
        }
    }


}

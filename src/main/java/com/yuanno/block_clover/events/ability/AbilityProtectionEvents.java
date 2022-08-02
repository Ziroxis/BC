package com.yuanno.block_clover.events.ability;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.ability.AbilityHelper;
import com.yuanno.block_clover.data.world.ExtendedWorldData;
import com.yuanno.block_clover.events.projectiles.ProjectileHitEvent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class AbilityProtectionEvents
{
	@SubscribeEvent
	public static void onExplosionDetonate(ExplosionEvent.Detonate event)
	{
		if(!event.getWorld().isClientSide)
		{
			ExtendedWorldData worldData = ExtendedWorldData.get(event.getWorld());
			
			Vector3d pos = event.getExplosion().getPosition();
			if(worldData.isInsideRestrictedArea((int)pos.x, (int)pos.y, (int)pos.z))
			{
				event.getAffectedBlocks().clear();
				event.getAffectedEntities().clear();
			}
		}
	}
	
	@SubscribeEvent
	public static void onAbilityHit(ProjectileHitEvent event)
	{
		if (event.getHit().getType() == RayTraceResult.Type.ENTITY)
		{
			EntityRayTraceResult entityHit = (EntityRayTraceResult) event.getHit();

			if (entityHit.getEntity() instanceof LivingEntity)
			{
				LivingEntity hitEntity = (LivingEntity) entityHit.getEntity();
				
				if(AbilityHelper.checkForRestriction(hitEntity.level, (int) hitEntity.getX(), (int) hitEntity.getY(), (int) hitEntity.getZ()))
				{
					event.setCanceled(true);
				}
			}
		}
	}
}

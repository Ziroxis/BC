package com.yuanno.block_clover.mixins;

import com.yuanno.block_clover.api.IVanishEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
	
	// Blocks the running particles from spawning, if we want true invisibility we can't just have random particles spawning around
	@Inject(method = "canSpawnSprintParticle", at = @At("HEAD"), cancellable = true)
	public void canSpawnSprintParticle(CallbackInfoReturnable<Boolean> ci) {
		Entity entity = (Entity) (Object) this;
		if (entity instanceof LivingEntity) {
			for (EffectInstance inst : ((LivingEntity) entity).getActiveEffects()) {
				if (inst.getEffect() instanceof IVanishEffect
						&& ((IVanishEffect) inst.getEffect()).isVanished((LivingEntity) entity, inst.getDuration(), inst.getAmplifier())
						&& ((IVanishEffect) inst.getEffect()).disableParticles()) {
					ci.setReturnValue(false);
					return;
				}
			}
		}
	}

	// If its stupid and it works it aint stupid :)
	@Inject(method = "isInvisible", at = @At("HEAD"), cancellable = true)
	public void isInvisible(CallbackInfoReturnable<Boolean> ci) {
		Entity entity = (Entity) (Object) this;
		if (entity instanceof LivingEntity) {
			for (EffectInstance inst : ((LivingEntity) entity).getActiveEffects()) {
				if (inst.getEffect() instanceof IVanishEffect
						&& ((IVanishEffect) inst.getEffect()).isVanished((LivingEntity) entity, inst.getDuration(), inst.getAmplifier())) {
					ci.setReturnValue(true);
					return;
				}
			}
		}
	}
}
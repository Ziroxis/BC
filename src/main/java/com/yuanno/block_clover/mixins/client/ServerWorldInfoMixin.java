package com.yuanno.block_clover.mixins.client;

import com.mojang.serialization.Lifecycle;
import net.minecraft.world.storage.ServerWorldInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerWorldInfo.class)
public class ServerWorldInfoMixin
{
	@Inject(method = "worldGenSettingsLifecycle", at = @At("HEAD"), cancellable = true)
	public void forceStable(CallbackInfoReturnable<Lifecycle> callback)
	{
		callback.setReturnValue(Lifecycle.stable());
	}
}

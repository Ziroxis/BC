package com.yuanno.block_clover.mixins.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DeathScreen.class)
public class DeathScreenMixin extends Screen
{
	@Shadow
	@Final
	public boolean hardcore;
	
	public DeathScreenMixin(ITextComponent title)
	{
		super(title);
	}

	@Inject(
		method = "init", 
		at = @At("HEAD")
	)
	public void init(CallbackInfo callback)
	{
		/*
		PlayerEntity player = Minecraft.getInstance().player;
		IEntityStats props = EntityStatsCapability.get(player);
		String race = props.getRace();
		if(race.equals(ModValues.HUMAN) || race.equals(ModValues.SPIRIT))
		{
			this.hardcore = false;
		}

		 */
	}
}

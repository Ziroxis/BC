package com.yuanno.block_clover.mixins.client;

import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.client.renderer.model.ModelRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

/** Only reason this exists is as a quick dirty fix, basically by default, minecraft doesn't check if a model renderer has no cubes nor does it check for any of its children's cubes as a fail safe, 
 * resulting in a crash if an arrow stuck on the body decides to pick a renderer with an empty cube list, this is not a permanent fix and the models will need manual readjustments later.
 * This checks if the renderer itself has an empty cube list it'll first check and return a random cube of a child, should fix 99% of the cases and in case an empty renderer has no children and
 * has an empty cube list, rip it'll crash :)
 */

@Mixin(ModelRenderer.class)
public class ModelRendererMixin
{
	@Shadow
	@Final
	private ObjectList<ModelRenderer.ModelBox> cubes;
	
	@Shadow
	@Final
	private ObjectList<ModelRenderer> children;
	
	@Inject(
		method = "getRandomCube",
		at = @At("HEAD"),
		cancellable = true
	)
	public void getRandomCube(Random random, CallbackInfoReturnable<ModelRenderer.ModelBox> callback)
	{
		if(this.cubes.isEmpty())
		{
			if(!this.children.isEmpty())
			{
				for(ModelRenderer renderer : this.children)
				{
					ModelRenderer.ModelBox box = renderer.getRandomCube(random);
					if(box != null)
					{
						callback.setReturnValue(box);
						return;
					}
				}
			}
		}
	}
}

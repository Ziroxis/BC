package com.example.block_clover.particles;

import com.example.block_clover.init.ModRenderTypes;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

public class CustomParticleRenderType implements IParticleRenderType
{
	private ResourceLocation texture;

	public CustomParticleRenderType(ResourceLocation texture)
	{
		this.texture = texture;
	}

	@Override
	public void begin(BufferBuilder buffer, TextureManager textureManager)
	{
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.depthMask(false);
		textureManager.bind(this.texture);
		buffer.begin(7, ModRenderTypes.PARTICLE_POSITION_TEX_COLOR_LMAP);
	}

	@Override
	public void end(Tessellator tess)
	{
		tess.end();
	}
}

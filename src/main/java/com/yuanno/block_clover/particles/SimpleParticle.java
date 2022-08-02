package com.yuanno.block_clover.particles;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.TexturedParticle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.settings.PointOfView;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SimpleParticle extends TexturedParticle
{
	private boolean hasMotionDecay = true;
	private boolean hasScaleDecay = true;
	private Vector3f rotationVector;
	private float rotationSpeed;
	private IParticleRenderType type;

	public SimpleParticle(IParticleRenderType type, ClientWorld world, double x, double y, double z, double xd, double yd, double zd)
	{
		super(world, x, y, z, 0.0D, 0.0D, 0.0D);
		this.lifetime = 30 + this.random.nextInt(10);
		this.age = 0;
		this.quadSize = 0.2F;
		this.gravity = 0F;
		this.setColor(1.0F, 1.0F, 1.0F);
		this.hasPhysics = false;

		this.type = type;

		this.xd = xd;
		this.yd = yd;
		this.zd = zd;
	}

	@Override
	public void tick()
	{
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		if (this.gravity != 0)
			this.yd = -0.04D * this.gravity;

		this.move(this.xd, this.yd, this.zd);
		if (this.hasMotionDecay)
		{
			this.xd *= 0.99D;
			this.yd *= 0.99D;
			this.zd *= 0.99D;
		}

		if(this.hasScaleDecay)
		{
			if (this.age >= this.lifetime / 2)
			{
				if (this.quadSize > 0)
					this.quadSize /= 1.1F;
			}
			
			if (this.age + 5 >= this.lifetime)
			{
				if (this.alpha > 0)
					this.alpha /= 1.15;
			}
		}

		if(this.rotationSpeed != 0)
			this.roll -= this.rotationSpeed;
		
		if (this.age++ >= this.lifetime || this.onGround)
			this.remove();
	}

	@Override
	public void render(IVertexBuilder buffer, ActiveRenderInfo renderInfo, float partialTicks)
	{
		Vector3d Vector3d = renderInfo.getPosition();
		float f = (float) (MathHelper.lerp(partialTicks, this.xo, this.x) - Vector3d.x());
		float f1 = (float) (MathHelper.lerp(partialTicks, this.yo, this.y) - Vector3d.y());
		float f2 = (float) (MathHelper.lerp(partialTicks, this.zo, this.z) - Vector3d.z());
		Quaternion quaternion;
		if (this.roll == 0.0F)
		{
			quaternion = renderInfo.rotation();
		}
		else
		{
			quaternion = new Quaternion(renderInfo.rotation());
			quaternion.mul(this.rotationVector.rotation(this.roll));
		}

		Vector3f vector3f1 = new Vector3f(-1.0F, -1.0F, 0.0F);
		vector3f1.transform(quaternion);
		Vector3f[] avector3f = new Vector3f[] { new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F) };
		float scale = this.getQuadSize(partialTicks);

		for (int i = 0; i < 4; ++i)
		{
			Vector3f vector3f = avector3f[i];
			vector3f.transform(quaternion);
			vector3f.mul(scale);
			vector3f.add(f, f1, f2);
		}

		float minU = this.getU0();
		float maxU = this.getU1();
		float minV = this.getV0();
		float maxV = this.getV1();
		int brightness = this.getLightColor(partialTicks);
		buffer.vertex(avector3f[0].x(), avector3f[0].y(), avector3f[0].z()).uv(maxU, maxV).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(brightness).endVertex();
		buffer.vertex(avector3f[1].x(), avector3f[1].y(), avector3f[1].z()).uv(maxU, minV).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(brightness).endVertex();
		buffer.vertex(avector3f[2].x(), avector3f[2].y(), avector3f[2].z()).uv(minU, minV).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(brightness).endVertex();
		buffer.vertex(avector3f[3].x(), avector3f[3].y(), avector3f[3].z()).uv(minU, maxV).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(brightness).endVertex();
	}

	public SimpleParticle setAlphaF(float f)
	{
		this.setAlpha(f);
		return this;
	}

	public SimpleParticle setParticleSize(float f)
	{
		this.quadSize = f;
		return this;
	}
	
	public SimpleParticle setParticleRotation(float f)
	{
		this.rotationSpeed = f;
		return this;
	}

	public SimpleParticle setParticleGravity(float f)
	{
		this.gravity = f;
		return this;
	}

	public SimpleParticle setParticleAge(int i)
	{
		this.lifetime = i + this.random.nextInt(10);
		return this;
	}
	
	public SimpleParticle setHasMotionDecay(boolean flag)
	{
		this.hasMotionDecay = flag;
		return this;
	}

	public SimpleParticle setRotation(Vector3f vec)
	{
		this.rotationVector = vec;
		return this;
	}
	
	public SimpleParticle setHasScaleDecay(boolean flag)
	{
		this.hasScaleDecay = flag;
		return this;
	}
	
	public BlockPos getPos()
	{
		return new BlockPos(this.x, this.y, this.z);
	}
	
	@Override
	public IParticleRenderType getRenderType()
	{
		return this.type;
	}

	@Override
	protected float getU1()
	{
		return 1;
	}

	@Override
	protected float getV1()
	{
		return 1;
	}

	@Override
	protected float getU0()
	{
		return 0;
	}

	@Override
	protected float getV0()
	{
		return 0;
	}

	public static class Factory implements IParticleFactory<GenericParticleData>
	{
		private IParticleRenderType type;

		public Factory(ResourceLocation res)
		{
			this.type = new CustomParticleRenderType(res);
		}

		@Override
		public Particle createParticle(GenericParticleData data, ClientWorld world, double x, double y, double z, double velX, double velY, double velZ)
		{
			SimpleParticle particle = new SimpleParticle(this.type, world, x, y, z, data.getDeltaMovementX(), data.getDeltaMovementY(), data.getDeltaMovementZ());
			particle.setColor(data.getRed(), data.getGreen(), data.getBlue());
			particle.setRotation(new Vector3f(data.getRotX(), data.getRotY(), data.getRotZ()));
			particle.setAlpha(data.getAlpha());
			particle.setParticleSize(data.getSize() / 10);
			particle.setParticleRotation(data.getRotationSpeed());
			particle.setParticleAge(data.getLife());
			particle.setHasMotionDecay(data.hasMotionDecay());
			particle.setHasScaleDecay(data.hasScaleDecay());

			if(Minecraft.getInstance().player != null)
			{
				if (data.getEntityID() == Minecraft.getInstance().player.getId())
				{
					if (Minecraft.getInstance().options.getCameraType() == PointOfView.FIRST_PERSON && data.getHideTooClose())
						particle.setAlphaF(1.0f);
				}
				else if (data.getHideFromOthers())
					particle.setAlphaF(0);
			}

			return particle;
		}
	}
}

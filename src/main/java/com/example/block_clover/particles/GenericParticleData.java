package com.example.block_clover.particles;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.math.vector.Vector3f;

public class GenericParticleData extends ParticleType<GenericParticleData> implements IParticleData
{
	public static final IDeserializer<GenericParticleData> DESERIALIZER = new IDeserializer<GenericParticleData>()
	{
		@Override
		public GenericParticleData fromCommand(ParticleType<GenericParticleData> particleType, StringReader stringReader) throws CommandSyntaxException
		{
			stringReader.expect(' ');
			float red = stringReader.readFloat();
			float green = stringReader.readFloat();
			float blue = stringReader.readFloat();
			float alpha = stringReader.readFloat();
			float size = stringReader.readFloat();
			float rot = stringReader.readFloat();
			int life = stringReader.readInt();
			int ID = stringReader.readInt();

			boolean hasMotionDecay = stringReader.readBoolean();
			boolean hasScaleDecay = stringReader.readBoolean();
			boolean shouldHideTooClose = stringReader.readBoolean();
			boolean shouldHideFromOthers = stringReader.readBoolean();
			
			double motionX = stringReader.readDouble();
			double motionY = stringReader.readDouble();
			double motionZ = stringReader.readDouble();

			float rotX = stringReader.readFloat();
			float rotY = stringReader.readFloat();
			float rotZ = stringReader.readFloat();
			
			GenericParticleData data = new GenericParticleData(particleType);
			data.setColor(red, green, blue, alpha);
			data.setMotion(motionX, motionY, motionZ);
			data.setRotation(rotX, rotY, rotZ);
			data.setSize(size);
			data.setRotationSpeed(rot);
			data.setLife(life);
			data.setEntityID(ID);
			
			data.setHasMotionDecay(hasMotionDecay);
			data.setHasScaleDecay(hasScaleDecay);
			
			if (shouldHideTooClose)
				data.hideTooClose();
			
			if(shouldHideFromOthers)
				data.hideFromOthers();

			return data;
		}

		@Override
		public GenericParticleData fromNetwork(ParticleType<GenericParticleData> particleType, PacketBuffer packetBuffer)
		{
			float red = packetBuffer.readFloat();
			float green = packetBuffer.readFloat();
			float blue = packetBuffer.readFloat();
			float alpha = packetBuffer.readFloat();
			float size = packetBuffer.readFloat();
			float rot = packetBuffer.readFloat();
			int life = packetBuffer.readInt();
			int ID = packetBuffer.readInt();
			boolean hasMotionDecay = packetBuffer.readBoolean();
			boolean hasScaleDecay = packetBuffer.readBoolean();
			boolean shouldHideTooClose = packetBuffer.readBoolean();
			boolean shouldHideFromOthers = packetBuffer.readBoolean();

			double motionX = packetBuffer.readDouble();
			double motionY = packetBuffer.readDouble();
			double motionZ = packetBuffer.readDouble();

			float rotX = packetBuffer.readFloat();
			float rotY = packetBuffer.readFloat();
			float rotZ = packetBuffer.readFloat();
			
			GenericParticleData data = new GenericParticleData(particleType);
			data.setColor(red, green, blue, alpha);
			data.setMotion(motionX, motionY, motionZ);
			data.setRotation(rotX, rotY, rotZ);
			data.setSize(size);
			data.setRotationSpeed(rot);
			data.setLife(life);
			data.setEntityID(ID);
			
			data.setHasMotionDecay(hasMotionDecay);
			data.setHasScaleDecay(hasScaleDecay);
			
			if (shouldHideTooClose)
				data.hideTooClose();
			
			if(shouldHideFromOthers)
				data.hideFromOthers();
			
			return data;
		}
	};

	private final Codec<GenericParticleData> codec = Codec.unit(this);

	private float red = 1, green = 1, blue = 1;
	private double motionX, motionY, motionZ;
	private float rotX, rotY, rotZ;
	private float alpha = 1.0F;
	private float size = 1;
	private float rotSpeed = 0f;
	private int life = 10;
	private int entityID = 0;
	private boolean hasMotionDecay = true;
	private boolean hasScaleDecay = true;
	private boolean shouldHideTooClose = false;
	private boolean shouldHideFromOthers = false;

	private ParticleType type;

	public GenericParticleData()
	{
		super(true, DESERIALIZER);
		this.type = this;
	}
	
	public GenericParticleData(ParticleType type)
	{
		super(true, DESERIALIZER);
		this.type = type;
	}

	@Override
	public ParticleType getType()
	{
		return this.type;
	}

	@Override
	public void writeToNetwork(PacketBuffer buffer)
	{
		buffer.writeFloat(this.red);
		buffer.writeFloat(this.green);
		buffer.writeFloat(this.blue);
		buffer.writeFloat(this.alpha);
		buffer.writeFloat(this.size);
		buffer.writeFloat(this.rotSpeed);
		buffer.writeInt(this.life);
		buffer.writeInt(this.entityID);
		
		buffer.writeBoolean(this.hasMotionDecay);
		buffer.writeBoolean(this.hasScaleDecay);
		buffer.writeBoolean(this.shouldHideTooClose);
		buffer.writeBoolean(this.shouldHideFromOthers);

		buffer.writeDouble(this.motionX);
		buffer.writeDouble(this.motionY);
		buffer.writeDouble(this.motionZ);
		
		buffer.writeFloat(this.rotX);
		buffer.writeFloat(this.rotY);
		buffer.writeFloat(this.rotZ);
	}

	public GenericParticleData setMotion(double motionX, double motionY, double motionZ)
	{
		this.motionX = motionX;
		this.motionY = motionY;
		this.motionZ = motionZ;
		return this;
	}
	
	public GenericParticleData setRotation(float rotX, float rotY, float rotZ)
	{
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.rotSpeed = 0.3f;
		return this;
	}
	
	public GenericParticleData setRotation(Vector3f vec)
	{
		return this.setRotation(vec.x(), vec.y(), vec.z());
	}

	public GenericParticleData setColor(float red, float green, float blue)
	{
		return this.setColor(red, green, blue, 1.0F);
	}

	public GenericParticleData setColor(float red, float green, float blue, float alpha)
	{
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
		return this;
	}
	
	public GenericParticleData setRotationSpeed(float rot)
	{
		this.rotSpeed = rot;
		return this;
	}

	public GenericParticleData setSize(float size)
	{
		this.size = size;
		return this;
	}

	public GenericParticleData setLife(int life)
	{
		this.life = life;
		return this;
	}
	
	public GenericParticleData setEntityID(int id)
	{
		this.entityID = id;
		return this;
	}

	public GenericParticleData setHasMotionDecay(boolean flag)
	{
		this.hasMotionDecay = flag;
		return this;
	}
	
	public GenericParticleData setHasScaleDecay(boolean flag)
	{
		this.hasScaleDecay = flag;
		return this;
	}
	
	public GenericParticleData hideFromOthers()
	{
		this.shouldHideFromOthers = true;
		return this;
	}
	
	public GenericParticleData hideTooClose()
	{
		this.shouldHideTooClose = true;
		return this;
	}
	
	public boolean getHideTooClose()
	{
		return this.shouldHideTooClose;
	}
	
	
	public boolean getHideFromOthers()
	{
		return this.shouldHideFromOthers;
	}
	
	@Override
	public String writeToString()
	{
		return this.getType().getRegistryName().toString();
	}

	public float getRed()
	{
		return this.red;
	}
	
	public float getEntityID()
	{
		return this.entityID;
	}
	
	public float getGreen()
	{
		return this.green;
	}

	public float getBlue()
	{
		return this.blue;
	}

	public float getAlpha()
	{
		return this.alpha;
	}

	public float getSize()
	{
		return this.size;
	}
	
	public float getRotationSpeed()
	{
		return this.rotSpeed;
	}

	public int getLife()
	{
		return this.life;
	}

	public double getDeltaMovementX()
	{
		return this.motionX;
	}

	public double getDeltaMovementY()
	{
		return this.motionY;
	}

	public double getDeltaMovementZ()
	{
		return this.motionZ;
	}
	
	public float getRotX()
	{
		return this.rotX;
	}

	public float getRotY()
	{
		return this.rotY;
	}

	public float getRotZ()
	{
		return this.rotZ;
	}

	public boolean hasMotionDecay()
	{
		return this.hasMotionDecay;
	}
	
	public boolean hasScaleDecay()
	{
		return this.hasScaleDecay;
	}

	@Override
	public Codec<GenericParticleData> codec()
	{
		return this.codec;
	}
}

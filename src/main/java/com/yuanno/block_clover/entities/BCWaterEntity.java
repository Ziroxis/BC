package com.yuanno.block_clover.entities;

import com.yuanno.block_clover.data.entity.EntityStatsCapability;
import com.yuanno.block_clover.data.entity.IEntityStats;
import com.yuanno.block_clover.events.levelEvents.ExperienceUpEvent;
import com.yuanno.block_clover.networking.PacketHandler;
import com.yuanno.block_clover.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.DolphinLookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;

import javax.annotation.Nullable;
import java.awt.font.TextHitInfo;

public abstract class BCWaterEntity extends WaterMobEntity {

    protected int xpDrop = 0;
    protected BCWaterEntity(EntityType<? extends BCWaterEntity> p_i48575_1_, World p_i48575_2_) {
        super(p_i48575_1_, p_i48575_2_);
        //this.moveControl = new BCWaterEntity.MoveHelperController(this);
        this.lookControl = new DolphinLookController(this, 10);

    }
    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
        this.setAirSupply(this.getMaxAirSupply());
        this.xRot = 0.0F;
        return super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
    }


    public int getMaxAirSupply() {
        return 4800;
    }

    protected int increaseAirSupply(int p_207300_1_) {
        return this.getMaxAirSupply();
    }

    @OnlyIn(Dist.CLIENT)
    private void addParticlesAroundSelf(IParticleData p_208401_1_) {
        for(int lvt_2_1_ = 0; lvt_2_1_ < 7; ++lvt_2_1_) {
            double lvt_3_1_ = this.random.nextGaussian() * 0.01;
            double lvt_5_1_ = this.random.nextGaussian() * 0.01;
            double lvt_7_1_ = this.random.nextGaussian() * 0.01;
            this.level.addParticle(p_208401_1_, this.getRandomX(1.0), this.getRandomY() + 0.2, this.getRandomZ(1.0), lvt_3_1_, lvt_5_1_, lvt_7_1_);
        }

    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }
    @Override
    public void tick() {
        super.tick();
        this.setAirSupply(this.getMaxAirSupply());
        if (this.isInWater()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            this.setAirSupply(this.getMaxAirSupply());
        }
    }


        @Override
        public void die(DamageSource cause) {
            super.die(cause);

            if (cause.getEntity() instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) cause.getEntity();
                IEntityStats props = EntityStatsCapability.get(player);

                props.alterExperience(this.getExperience());
                ExperienceUpEvent eventExperience = new ExperienceUpEvent(player, props.getExperience());
                if (MinecraftForge.EVENT_BUS.post(eventExperience))
                    return;
                PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), props), player);
            }
        }


        public int getExperience() {
            return xpDrop;
        }

}

package com.yuanno.block_clover.entities.misc;

import com.yuanno.block_clover.entities.BCentity;
import com.yuanno.block_clover.entities.goals.attribute.fire.FireBallGoal;
import com.yuanno.block_clover.entities.goals.attribute.fire.SpiralFlameGoal;
import com.yuanno.block_clover.entities.goals.attribute.fire.WildBurstingFlamesGoal;
import com.yuanno.block_clover.init.ModAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class VolcanoMonsterEntity extends BCentity
{

    public VolcanoMonsterEntity(EntityType type, World world)
    {
        super(type, world);
        this.xpDrop = 500;
    }
    @Override
    protected void registerGoals()
    {
        super.registerGoals();

        if (canUseMagic)
        {
            this.goalSelector.addGoal(6, new SpiralFlameGoal(this));
            this.goalSelector.addGoal(5, new WildBurstingFlamesGoal(this));
            this.goalSelector.addGoal(5, new FireBallGoal(this));
        }
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1, true));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 4));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));

    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes()
    {
        return MobEntity.createMobAttributes()
                .add(Attributes.ATTACK_DAMAGE, 7)
                .add(Attributes.ARMOR, 10)
                .add(Attributes.MAX_HEALTH, 50)
                .add(Attributes.FOLLOW_RANGE, 20)
                .add(Attributes.MOVEMENT_SPEED, 0.05)
                .add(ModAttributes.FALL_RESISTANCE.get(), 50);

    }

    @Override
    public boolean removeWhenFarAway(double d)
    {
        return false;
    }

    @Override
    public float getBrightness() {
        return 2.0F;
    }

    @Override
    public boolean isSensitiveToWater() {
        return true;
    }



    @Override
    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag)
    {
        spawnData = super.finalizeSpawn(world, difficulty, reason, spawnData, dataTag);
        return spawnData;

    }


}

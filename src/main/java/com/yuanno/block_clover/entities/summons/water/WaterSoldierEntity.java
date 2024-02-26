package com.yuanno.block_clover.entities.summons.water;

import com.yuanno.block_clover.entities.summons.CloneEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class WaterSoldierEntity extends CloneEntity {

    public WaterSoldierEntity(EntityType type, World world)
    {
        super(type, world);
    }

    public WaterSoldierEntity(World world)
    {
        super(WaterSummons.WATER_SOLDIER.get(), world);
    }
    @Override
    protected void registerGoals()
    {
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1, true));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 4));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(1, new SwimGoal(this));


        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
    }

    @Override
    public boolean hurt(DamageSource damageSource, float damageValue)
    {
        if(damageSource.getEntity() != null && damageSource.getEntity() instanceof PlayerEntity && damageSource.getEntity() == this.getOwner())
            return false;

        return super.hurt(damageSource, damageValue);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes()
    {
        return WaterSoldierEntity.createMobAttributes()
                .add(Attributes.FOLLOW_RANGE, 35)
                .add(Attributes.MOVEMENT_SPEED, 0.25F)
                .add(Attributes.MAX_HEALTH, 5.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 10.0D)
                .add(Attributes.ARMOR, 2.0D);
    }


}

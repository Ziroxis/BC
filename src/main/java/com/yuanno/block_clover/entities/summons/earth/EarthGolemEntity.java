package com.yuanno.block_clover.entities.summons.earth;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.entities.api.BCsummon;
import com.yuanno.block_clover.entities.beastial.CloverSharkEntity;
import com.yuanno.block_clover.entities.beastial.MonkeyEntity;
import com.yuanno.block_clover.entities.misc.VolcanoMonsterEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.*;

public class EarthGolemEntity extends BCsummon {

    private static final DataParameter<String> TEXTURE = EntityDataManager.defineId(EarthGolemEntity.class, DataSerializers.STRING);
    private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.defineId(EarthGolemEntity.class, DataSerializers.OPTIONAL_UUID);
    public boolean isAggressive = true;
    public List<LivingEntity> forcedTargets = new ArrayList<LivingEntity>();





    public EarthGolemEntity(EntityType type, World world) {
        super(type, world);
    }

    public EarthGolemEntity(World world)
    {
        super(EarthSummons.EARTH_GOLEM.get(), world);
    }






    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1, true));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));


        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes()
    {
        return MobEntity.createMobAttributes()
                .add(Attributes.ARMOR, 7)
                .add(Attributes.MAX_HEALTH, 30)
                .add(Attributes.FOLLOW_RANGE, 25)
                .add(Attributes.MOVEMENT_SPEED, 0.35)
                .add(Attributes.ATTACK_DAMAGE, 7)
                .add(Attributes.ATTACK_SPEED, 2);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(OWNER, null);
        this.getEntityData().define(TEXTURE, "");
    }

    @Override
    public boolean hurt(DamageSource damageSource, float damageValue)
    {
        if(damageSource.getEntity() != null && damageSource.getEntity() instanceof PlayerEntity && damageSource.getEntity() == this.getOwner())
            return false;

        return super.hurt(damageSource, damageValue);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound)
    {
        super.addAdditionalSaveData(compound);
        if (this.entityData.get(OWNER) != null)
            compound.putString("OwnerUUID", this.entityData.get(OWNER).toString());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound)
    {
        super.readAdditionalSaveData(compound);
        this.entityData.set(OWNER, Optional.of(UUID.fromString(compound.getString("OwnerUUID"))));
    }

    public void setOwner(LivingEntity owner)
    {
        this.entityData.set(OWNER, Optional.of(owner.getUUID()));
    }

    public PlayerEntity getOwner()
    {
        return this.getEntityData().get(OWNER).isPresent() ? this.level.getPlayerByUUID(this.getEntityData().get(OWNER).get()) : null;
    }

    @Override
    public void tick()
    {
        if(!this.level.isClientSide)
        {
            if(this.getOwner() == null)
            {
                this.remove();
                return;
            }

            if(this.distanceTo(this.getOwner()) > 10)
                this.getNavigation().moveTo(this.getOwner(), 1.5);

            if(this.distanceTo(this.getOwner()) > 128)
                this.setPos(this.getOwner().getX(), this.getOwner().getY(), this.getOwner().getZ());

            List<LivingEntity> earthMinionAttackList = this.isAggressive ? Beapi.getEntitiesNear(this.blockPosition(), this.level, 20, PlayerEntity.class, MonsterEntity.class, VolcanoMonsterEntity.class, CloverSharkEntity.class, MonkeyEntity.class) : !this.forcedTargets.isEmpty() ? this.forcedTargets : new ArrayList<LivingEntity>();
            LivingEntity target = null;


            if(!earthMinionAttackList.isEmpty() && (this.getTarget() == null || !this.getTarget().isAlive()))
            {
                if(earthMinionAttackList.contains(this.getOwner()))
                    earthMinionAttackList.remove(this.getOwner());


                target = earthMinionAttackList.stream().findFirst().orElse(null);
            }

            if(target != null)
                this.setTarget(target);

            if(!this.forcedTargets.isEmpty())
            {
                Iterator it = this.forcedTargets.iterator();
                while(it.hasNext())
                {
                    LivingEntity forcedTarget = (LivingEntity) it.next();
                    if(forcedTarget == null || !forcedTarget.isAlive())
                        it.remove();
                }
            }
        }

        super.tick();
    }

}

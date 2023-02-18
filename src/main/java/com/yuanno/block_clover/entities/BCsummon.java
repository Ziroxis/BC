package com.yuanno.block_clover.entities;

import com.yuanno.block_clover.entities.summons.earth.EarthMinionEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.UUID;

public class BCsummon extends CreatureEntity {
    protected BCsummon(EntityType<? extends CreatureEntity> p_i48575_1_, World p_i48575_2_) {
        super(p_i48575_1_, p_i48575_2_);
    }
    private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.defineId(BCsummon.class, DataSerializers.OPTIONAL_UUID);

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
}

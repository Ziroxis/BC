package com.yuanno.block_clover.blocks.tileentities;

import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.init.ModBlocks;
import com.yuanno.block_clover.init.ModTileEntities;
import com.yuanno.block_clover.spells.antimagic.AntiMagicManaZoneAbility;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class AntiMagicBlockTileEntity extends TileEntity implements ITickableTileEntity {

    private Optional<UUID> ownerUUID = Optional.empty();
    public AntiMagicBlockTileEntity()
    {
        super(ModTileEntities.ANTIMAGIC_TILEENTITY.get());
    }
        //test
    @Override
    public void tick()
    {
        if (!this.level.isClientSide)
        {
            LivingEntity owner = this.getOwner();
            if(owner != null)
            {
                AntiMagicManaZoneAbility ability = (AntiMagicManaZoneAbility) AbilityDataCapability.get(owner).getEquippedAbility(AntiMagicManaZoneAbility.INSTANCE);

                if(ability != null)
                {
                    BlockPos pos = new BlockPos(owner.getX(), owner.getY(), owner.getZ());
                    double distance = pos.distSqr(this.worldPosition);
                    if(MathHelper.sqrt(distance) > ability.getROOMSize() + 2 || !ability.isContinuous())
                        ability.endContinuity((PlayerEntity) owner);
                }
                else
                    this.clearRoom();
            }
            else
                this.clearRoom();
        }
    }
    public void clearRoom()
    {
        for (int i = -AntiMagicManaZoneAbility.MAX_ZONE_SIZE; i < AntiMagicManaZoneAbility.MAX_ZONE_SIZE; i++)
            for (int k = -AntiMagicManaZoneAbility.MAX_ZONE_SIZE; k < AntiMagicManaZoneAbility.MAX_ZONE_SIZE; k++)
                for (int j = -AntiMagicManaZoneAbility.MAX_ZONE_SIZE; j < AntiMagicManaZoneAbility.MAX_ZONE_SIZE; j++)
                    if (this.level.getBlockState(new BlockPos(this.getBlockPos().getX() + i, this.getBlockPos().getY() + k, this.getBlockPos().getZ() + j)).getBlock() == ModBlocks.ANTIMAGIC.get())
                        this.level.setBlockAndUpdate(new BlockPos(this.getBlockPos().getX() + i, this.getBlockPos().getY() + k, this.getBlockPos().getZ() + j), Blocks.AIR.defaultBlockState());
        this.level.setBlockAndUpdate(new BlockPos(this.getBlockPos().getX(), this.getBlockPos().getY(), this.getBlockPos().getZ()), Blocks.AIR.defaultBlockState());
    }
    @Nullable
    public LivingEntity getOwner()
    {
        if(this.level instanceof ServerWorld && this.ownerUUID.isPresent())
        {
            Entity owner = ((ServerWorld)this.level).getEntity(this.ownerUUID.get());
            if(owner instanceof LivingEntity)
                return (LivingEntity) owner;
            else
                return null;
        }
        else
            return null;
    }

    public void setOwner(LivingEntity owner)
    {
        this.ownerUUID = Optional.of(owner.getUUID());
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt)
    {
        super.save(nbt);

        if(this.ownerUUID.isPresent())
            nbt.putUUID("UUID", this.ownerUUID.get());

        return nbt;
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt)
    {
        super.load(state, nbt);

        this.ownerUUID = Optional.of(nbt.getUUID("UUID"));
    }

    @Override
    public CompoundNBT getUpdateTag()
    {
        return this.save(new CompoundNBT());
    }

    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket()
    {
        CompoundNBT nbttagcompound = new CompoundNBT();
        this.save(nbttagcompound);
        return new SUpdateTileEntityPacket(this.worldPosition, 9, this.getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt)
    {
        this.load(null, pkt.getTag());
    }
}

package com.yuanno.block_clover.entities.summons.water;

import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import com.yuanno.block_clover.entities.summons.darkness.DarknesSummons;
import com.yuanno.block_clover.particles.ParticleEffect;
import com.yuanno.block_clover.particles.darkness.BlackHoleParticleEffect;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.List;
import java.util.UUID;

public class WaterShieldEntity extends Entity {

    private LivingEntity owner;
    private UUID ownerUUID;
    private float manaIn;
    private final double speedLimit = 1;
    private final double pullSpeed = 0.7;
    //private static final ParticleEffect PARTICLES = new BlackHoleParticleEffect();

    public WaterShieldEntity(EntityType type, World world) {
        super(type, world);
    }

    public WaterShieldEntity(World world, LivingEntity owner)
    {
        super(WaterSummons.WATER_SHIELD.get(), world);
        this.ownerUUID = owner.getUUID();
    }

    public void setOwner(UUID uuid)
    {
        this.ownerUUID = uuid;
        this.owner = (LivingEntity) ((ServerWorld) this.level).getEntity(this.ownerUUID);
    }

    @Override
    public boolean isColliding(BlockPos pPos, BlockState pState) {
        return super.isColliding(pPos, pState);
    }

    @Override
    public void tick()
    {
        super.tick();

        if(this.level.isClientSide)
            return;

        if (this.ownerUUID == null)
        {
            this.remove();
            return;
        }

        if(this.owner == null)
        {
            this.owner = (LivingEntity) ((ServerWorld) this.level).getEntity(this.ownerUUID);
            return;
        }
        //PARTICLES.spawn(this.level, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
        this.yRot = (this.owner.getYHeadRot() - 90.0F) * -1;
        /*
        if(Math.round(this.getY()) > Math.round(owner.getY() + 3))
            this.move(MoverType.SELF, new Vector3d(0, -0.1, 0));
        else if (Math.round(this.getY()) < Math.round(owner.getY() + 3))
            this.move(MoverType.SELF, new Vector3d(0, 0.1, 0));

         */

        Vector3d lookingAngle = this.owner.getLookAngle();

        //this.owner.yHeadRot
        if(this.owner.getHealth() <= 0)
            this.remove();

        double posX = 0;
        double posZ = 0;
        double posY = 0;
        posX = this.getX() - this.owner.getX() - 2 * lookingAngle.x;
        posY = lookingAngle.y;
        posZ = this.getZ() - this.owner.getZ() - 2 * lookingAngle.z;

        if(posX > this.speedLimit)
            posX = this.speedLimit;
        else if(posX < 0 && posX < -this.speedLimit)
            posX = -this.speedLimit;

        if(posY > this.speedLimit)
            posY = this.speedLimit;
        else if(posY < 0 && posY < -this.speedLimit)
            posY = -this.speedLimit;

        if(posZ > this.speedLimit)
            posZ = this.speedLimit;
        else if(posZ < 0 && posZ < -this.speedLimit)
            posZ = -this.speedLimit;

        this.move(MoverType.SELF, new Vector3d(-posX, -posY, -posZ));

        List<ProjectileEntity> spellProjectiles = Beapi.getNearbyEntities(this.getOnPos(), this.level, 3, 2.5, 1, null, ProjectileEntity.class);

        spellProjectiles.forEach(spellProjectile -> {

            if(spellProjectile.getOwner() == this.owner)
                return;

            spellProjectile.kill();

        });

    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void addAdditionalSaveData(CompoundNBT compound)
    {
        compound.putString("OwnerUUID", this.ownerUUID.toString());
    }

    @Override
    protected void readAdditionalSaveData(CompoundNBT compound)
    {
        if (compound.contains("OwnerUUID", 8))
            this.setOwner(UUID.fromString(compound.getString("OwnerUUID")));
    }

    @Override
    public IPacket<?> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public void setRot(float yDirection, float xDirection) {
        this.yRot = yDirection % 360.0F;
        this.xRot = xDirection % 360.0F;

    }
}

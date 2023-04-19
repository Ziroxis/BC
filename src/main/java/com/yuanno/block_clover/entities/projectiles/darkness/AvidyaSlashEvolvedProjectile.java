package com.yuanno.block_clover.entities.projectiles.darkness;

import com.yuanno.block_clover.api.ability.AbilityHelper;
import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import com.yuanno.block_clover.api.ability.sorts.ExplosionAbility;
import com.yuanno.block_clover.particles.CommonExplosionParticleEffect;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AvidyaSlashEvolvedProjectile extends AbilityProjectileEntity {

    public AvidyaSlashEvolvedProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public AvidyaSlashEvolvedProjectile(World world, LivingEntity player)
    {
        super(DarknessProjectiles.AVIDYA_SLASH_EVOLVED.get(), world, player);
        this.setDamage(14);
        this.setMaxLife(128);
        this.setPhysical(false);
        this.onBlockImpactEvent = this::onHitBlock;
        this.onEntityImpactEvent = this::onEntityImpactEvent;
        this.onTickEvent = this::onTick;

    }

    private void onEntityImpactEvent(LivingEntity entity)
    {
        /*
        entity.setSecondsOnFire(7);
        ExplosionAbility explosion = AbilityHelper.newExplosion(this.getThrower(), this.level, entity.getX(), entity.getY(), entity.getZ(), 3);
        explosion.setStaticDamage(5);
        explosion.setExplosionSound(true);
        explosion.setDamageOwner(false);
        explosion.setDestroyBlocks(true);
        explosion.setFireAfterExplosion(false);
        explosion.setSmokeParticles(new CommonExplosionParticleEffect(3));
        explosion.setDamageEntities(true)
        explosion.doExplosion();
        */
    }

    private void onHitBlock(BlockPos hit)
    {
        World world = this.level;
        world.setBlock(hit, Blocks.AIR.defaultBlockState(), 2);
    }

    private void onTick()
    {
        World world = this.level;

        // gets the blocks around this entity and turns them into air
        double x0 = this.getX();
        double y0 = this.getY();
        double z0 = this.getZ();

        int radius = 1;

// Get the Y coordinate and calculate new values
        double y2 = y0 + 2;
        double y3 = y0 + 3;
        double y2Minus = y0 - 2;
        double y3Minus = y0 - 3;

// Set the blocks to air
        world.setBlock(new BlockPos(x0, y2, z0), Blocks.AIR.defaultBlockState(), 2);
        world.setBlock(new BlockPos(x0, y3, z0), Blocks.AIR.defaultBlockState(), 2);
        world.setBlock(new BlockPos(x0, y2Minus, z0), Blocks.AIR.defaultBlockState(), 2);
        world.setBlock(new BlockPos(x0, y3Minus, z0), Blocks.AIR.defaultBlockState(), 2);
        for (double y = y0 - radius; y <= y0 + radius; y++)
        {
            for (double x = x0 - radius; x <= x0 + radius; x++)
            {
                for (double z = z0 - radius; z <= z0 + radius; z++)
                {
                    BlockPos pos = new BlockPos(x, y, z);
                    world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                }
            }
        }
    }
}

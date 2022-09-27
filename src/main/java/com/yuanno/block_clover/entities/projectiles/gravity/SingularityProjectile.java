package com.yuanno.block_clover.entities.projectiles.gravity;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;


public class SingularityProjectile extends AbilityProjectileEntity {

    public SingularityProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public SingularityProjectile(World world, LivingEntity player)
    {
        super(GravityProjectiles.SINGULARITY.get(), world, player);
        this.setDamage(16);
        this.setMaxLife(128);
        this.setPhysical(false);
        this.setPassThroughBlocks();
        this.onTickEvent = this::onTick;
    }

    private void onTick()
    {
        World world = this.level;

        // gets the blocks around this entity and turns them into air
        double x0 = this.getX();
        double y0 = this.getY();
        double z0 = this.getZ();

        int radius = 3;

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

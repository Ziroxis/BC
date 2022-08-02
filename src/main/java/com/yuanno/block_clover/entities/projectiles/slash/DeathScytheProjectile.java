package com.yuanno.block_clover.entities.projectiles.slash;

import com.yuanno.block_clover.api.ability.AbilityProjectileEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class DeathScytheProjectile extends AbilityProjectileEntity {

    public DeathScytheProjectile(EntityType type, World world)
    {
        super(type, world);
    }

    public DeathScytheProjectile(World world, LivingEntity player)
    {
        super(SlashProjectiles.DEATH_SCYTHE.get(), world, player);
        this.setDamage(8);
        this.setMaxLife(128);
        this.setPhysical(false);
    }

    @Override
    protected void onHitBlock(BlockRayTraceResult result)
    {
        BlockPos pos = result.getBlockPos();
        World world = this.level;
        world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
    }
}

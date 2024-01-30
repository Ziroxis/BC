package com.yuanno.block_clover.api;

import com.yuanno.block_clover.BlockProtectionRule;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class BeModApi {

    public static List<BlockPos> createRandomizedPlatform(World world, double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, BlockProtectionRule rule)
    {
        List<BlockPos> blockPositions = new ArrayList<BlockPos>();
        for (int x = -sizeX; x <= sizeX; x++)
        {
            for (int z = -sizeZ; z <= sizeZ; z++)
            {
                BlockPos pos = new BlockPos(posX + x, posY, posZ + z);
                if (Math.random() <= 0.05)
                    blockPositions.add(pos);
            }
        }
        return blockPositions;
    }
    public static List<BlockPos> createPlatform(World world, double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, BlockProtectionRule rule)
    {
        List<BlockPos> blockPositions = new ArrayList<BlockPos>();
        for (int x = -sizeX; x <= sizeX; x++)
        {
            for (int z = -sizeZ; z <= sizeZ; z++)
            {
                BlockPos pos = new BlockPos(posX + x, posY, posZ + z);
                blockPositions.add(pos);
            }
        }
        return blockPositions;
    }
    public static List<BlockPos> createEmptyCube(World world, double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, Block blockToPlace, BlockProtectionRule rule)
    {
        return createEmptyCube(world, posX, posY, posZ, sizeX, sizeY, sizeZ, 2, blockToPlace, rule);
    }

    public static List<BlockPos> createEmptyCube(World world, double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, int flags, Block blockToPlace, BlockProtectionRule rule)
    {
        List<BlockPos> blockPositions = new ArrayList<BlockPos>();
        for (int x = -sizeX; x <= sizeX; x++)
        {
            for (int y = -sizeY; y <= sizeY; y++)
            {
                for (int z = -sizeZ; z <= sizeZ; z++)
                {
                    if (x == -sizeX || x == sizeX || y == -sizeY || y == sizeY || z == -sizeZ || z == sizeZ)
                    {
                        BlockPos pos = new BlockPos(posX + x, posY + y, posZ + z);
                        if (true)
                            blockPositions.add(pos);
                    }
                }
            }
        }
        return blockPositions;
    }

    public static <T extends Entity> EntityType.Builder createEntityType(EntityType.IFactory<T> factory)
    {
        return createEntityType(factory, EntityClassification.MISC);
    }
    public static <T extends Entity> EntityType.Builder createEntityType(EntityType.IFactory<T> factory, EntityClassification classification)
    {
        EntityType.Builder<T> builder = EntityType.Builder.<T>of(factory, classification);

        builder.setTrackingRange(128).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).sized(0.6F, 1.8F);

        return builder;
    }
}

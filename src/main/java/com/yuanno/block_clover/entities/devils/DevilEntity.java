package com.yuanno.block_clover.entities.devils;

import com.yuanno.block_clover.entities.BCentity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class DevilEntity extends BCentity {
    protected DevilEntity(EntityType type, World world) {
        super(type, world);
        this.canUseMagic = true;

    }
}

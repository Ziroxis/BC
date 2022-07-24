package com.example.block_clover.data.entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class EntityStatsCapability {

    @CapabilityInject(IEntityStats.class)
    public static final Capability<IEntityStats> INSTANCE = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IEntityStats.class, new Capability.IStorage<IEntityStats>()
        {
            @Override
            public INBT writeNBT(Capability<IEntityStats> capability, IEntityStats instance, Direction side)
            {
                CompoundNBT props = new CompoundNBT();
                props.putInt("level", instance.getLevel());
                props.putInt("maxLevel", instance.getMaxLevel());
                props.putInt("experience", instance.getExperience());
                props.putInt("maxExperience", instance.getMaxExperience());
                props.putFloat("mana", instance.getMana());
                props.putFloat("maxMana", instance.getMaxMana());
                props.putString("attribute", instance.getAttribute());
                props.putString("race", instance.getRace());

                return props;
            }

            @Override
            public void readNBT(Capability<IEntityStats> capability, IEntityStats instance, Direction side, INBT nbt)
            {
                CompoundNBT props = (CompoundNBT) nbt;

                instance.setLevel(props.getInt("level"));
                instance.setMaxLevel(props.getInt("maxLevel"));
                instance.setExperience(props.getInt("experience"));
                instance.setMaxExperience(props.getInt("maxExperience"));
                instance.setMana(props.getFloat("mana"));
                instance.setMaxMana(props.getFloat("maxMana"));
                instance.setAttribute(props.getString("attribute"));
                instance.setRace(props.getString("race"));

            }
        }, () -> new EntityStatsBase());

    }
    public static IEntityStats get(final LivingEntity entity)
    {
        return entity.getCapability(INSTANCE, null).orElse(new EntityStatsBase());
    }
}

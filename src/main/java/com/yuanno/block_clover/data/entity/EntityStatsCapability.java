package com.yuanno.block_clover.data.entity;

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
                props.putBoolean("isInCombatMode", instance.isInCombatMode());
                props.putInt("level", instance.getLevel());
                props.putInt("maxLevel", instance.getMaxLevel());
                props.putInt("experience", instance.getExperience());
                props.putInt("maxExperience", instance.getMaxExperience());
                props.putFloat("mana", instance.getMana());
                props.putFloat("maxMana", instance.getMaxMana());
                props.putFloat("manaRegeneration", instance.getManaRegeneration());
                props.putString("attribute", instance.getAttribute());
                props.putString("secondAttribute", instance.getSecondAttribute());
                props.putString("race", instance.getRace());
                props.putBoolean("hasGrimoire", instance.hasGrimoire());
                props.putInt("state", instance.getState());
                props.putString("title", instance.getTitle());
                props.putString("rank", instance.getRank());

                return props;
            }

            @Override
            public void readNBT(Capability<IEntityStats> capability, IEntityStats instance, Direction side, INBT nbt)
            {
                CompoundNBT props = (CompoundNBT) nbt;

                instance.setCombatMode(props.getBoolean("isInCombatMode"));
                instance.setLevel(props.getInt("level"));
                instance.setMaxLevel(props.getInt("maxLevel"));
                instance.setExperience(props.getInt("experience"));
                instance.setMaxExperience(props.getInt("maxExperience"));
                instance.setMana(props.getFloat("mana"));
                instance.setMaxMana(props.getFloat("maxMana"));
                instance.setManaRegeneration(props.getFloat("manaRegeneration"));
                instance.setAttribute(props.getString("attribute"));
                instance.setSecondAttribute(props.getString("secondAttribute"));
                instance.setRace(props.getString("race"));
                instance.setGrimoire(props.getBoolean("hasGrimoire"));
                instance.setState(props.getInt("state"));
                instance.setTitle(props.getString("title"));
                instance.setRank(props.getString("rank"));

            }
        }, () -> new EntityStatsBase());

    }
    public static IEntityStats get(final LivingEntity entity)
    {
        return entity.getCapability(INSTANCE, null).orElse(new EntityStatsBase());
    }
}

package com.yuanno.block_clover.data.entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import java.util.HashMap;

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
                props.putInt("time", instance.getTime());
                props.putFloat("multiplier", instance.getMultiplier());
		        props.putBoolean("staffBoost", instance.getStaffBoost());
            
                props.putBoolean("hatBoost", instance.getHatBoost());
                props.putBoolean("canLink", instance.getLinkAbility());
                props.putInt("cookingLevel", instance.getCookingLevel());
                props.putInt("maxCookingLevel", instance.getMaxCookingLevel());
                props.putInt("cookingExperience", instance.getCookingExperience());
                props.putInt("maxCookingExperience", instance.getMaxCookingExperience());

                props.putBoolean("innateDevil", instance.getInnateDevil());
                props.putString("devil", instance.getDevil());

                // Save the HashMap
                CompoundNBT hashMapNBT = new CompoundNBT();
                HashMap<String, Integer> hashMap = instance.getExperienceSpells();
                for (String key : hashMap.keySet()) {
                    hashMapNBT.putInt(key, hashMap.get(key));
                }
                props.put("experience_spells", hashMapNBT);
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
                instance.setTime(props.getInt("time"));
                instance.setMultiplier(props.getFloat("multiplier"));
		        instance.setStaffBoost(props.getBoolean("staffBoost"));
                instance.setStaffBoost(props.getBoolean("hatBoost"));
                instance.setLinkAbility(props.getBoolean("canLink"));
                instance.setCookingLevel(props.getInt("cookingLevel"));
                instance.setMaxCookingLevel(props.getInt("maxCookingLevel"));
                instance.setCookingExperience(props.getInt("cookingExperience"));
                instance.setMaxCookingLevel(props.getInt("maxCookingExperience"));

                instance.setInnateDevil(props.getBoolean("innateDevil"));
                instance.setDevil(props.getString("devil"));

                CompoundNBT hashMapNBT = props.getCompound("experience_spells");
                HashMap<String, Integer> hashMap = new HashMap<>();
                for (String key : hashMapNBT.getAllKeys()) {
                    hashMap.put(key, hashMapNBT.getInt(key));
                }
                instance.setExperienceSpells(hashMap);
            }
        }, () -> new EntityStatsBase());

    }
    public static IEntityStats get(final LivingEntity entity)
    {
        return entity.getCapability(INSTANCE, null).orElse(new EntityStatsBase());
    }
}

package com.yuanno.block_clover.helper;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.data.ability.AbilityDataCapability;
import com.yuanno.block_clover.data.ability.IAbilityData;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.spells.water.*;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.HashMap;

public class GainSpellHelper {

    private static final AbilityCore[][] waterAbilities = {
            {WaterSubstituteSpell.INSTANCE, CurrentOfTheFortuneRiverAbility.INSTANCE, HolyFistOfLoveAbility.INSTANCE},
            {WaterShieldAbility.INSTANCE, WaterBlessingAbility.INSTANCE, WaterJavelinAbility.INSTANCE},
            {SeaDragonsCradleAbility.INSTANCE, SeaDragonsNestAbility.INSTANCE, SeaSerpentsBelowAbility.INSTANCE},
            {ValkyrieArmorAbility.INSTANCE, QualleOperationSpell.INSTANCE, TaintedCulvertWyrmAbility.INSTANCE},
            {WaterSurgeAbility.INSTANCE, WaterSoldierAbility.INSTANCE, SeaDragonsRoarAbility.INSTANCE},
            {SeaSerpentsBelowAbility.INSTANCE, PointBlankDragonAbility.INSTANCE, WaterPressureShotAbility.INSTANCE},
    };

    private static HashMap<Integer, AbilityCore[]> spellPerLevelWater = new HashMap<>();
    static {
        spellPerLevelWater.put(5, waterAbilities[0]);
        spellPerLevelWater.put(10, waterAbilities[1]);
        spellPerLevelWater.put(15, waterAbilities[2]);
        spellPerLevelWater.put(20, waterAbilities[3]);
        spellPerLevelWater.put(25, waterAbilities[4]);
        spellPerLevelWater.put(30, waterAbilities[5]);

    }

    public static HashMap<String, HashMap<Integer, AbilityCore[]>> spellsMap = new HashMap<>();
    static
    {
        spellsMap.put(ModValues.WATER, spellPerLevelWater);
    }

    // Cache for ArrayList<Ability>
    private static HashMap<AbilityCore[], ArrayList<Ability>> abilityListCache = new HashMap<>();

    public static ArrayList<Ability> returnAbilitiesPerLevelAttribute(String attribute, int level)
    {
        level += 1;
        AbilityCore[] abilityCores = spellsMap.get(attribute).get(level);
        if (abilityCores == null)
        {
            return new ArrayList<>();
        }
        // Check if the ArrayList<Ability> is already in the cache
        ArrayList<Ability> abilities = abilityListCache.get(abilityCores);
        if (abilities == null) {
            // If not, create it and put it in the cache
            abilities = new ArrayList<>();
            for (AbilityCore abilityCore : abilityCores) {
                abilities.add(abilityCore.createAbility());
            }
            abilityListCache.put(abilityCores, abilities);
        }

        return abilities;
    }

    public static ArrayList<Ability> returnAbilitiesTotal(String attribute, int level)
    {
        level += 1;
        ArrayList<Ability> abilities = new ArrayList<>();
        for (int i = 0; i < level; i++ )
        {
            abilities.addAll(returnAbilitiesPerLevelAttribute(attribute, i));
        }
        return abilities;
    }

    public static ArrayList<Ability> returnAbilitiesInPool(String attribute, int level, PlayerEntity player)
    {
        level += 1;
        ArrayList<Ability> abilities = returnAbilitiesTotal(attribute, level);
        IAbilityData abilityData = AbilityDataCapability.get(player);
        abilities.removeIf(ability -> abilityData.getUnlockedAbility(ability) != null);
        return abilities;
    }
}

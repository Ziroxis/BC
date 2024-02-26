package com.yuanno.block_clover.helper;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.spells.water.*;

import java.util.ArrayList;
import java.util.HashMap;

public class GainSpellHelper {

    private static final AbilityCore[][] waterAbilities = {
            {WaterShieldAbility.INSTANCE, CurrentOfTheFortuneRiverAbility.INSTANCE, WaterBlessingAbility.INSTANCE},
            {SeaDragonsCradleAbility.INSTANCE, HolyFistOfLoveAbility.INSTANCE, WaterJavelinAbility.INSTANCE}
            // Add more arrays for water10, water15, etc.
    };

    private static HashMap<Integer, AbilityCore[]> spellPerLevelWater = new HashMap<>();
    static {
        spellPerLevelWater.put(5, waterAbilities[0]);
        spellPerLevelWater.put(10, waterAbilities[1]);
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

}

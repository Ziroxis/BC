package com.yuanno.block_clover.helper;

import com.yuanno.block_clover.api.ability.Ability;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.init.ModValues;
import com.yuanno.block_clover.spells.water.CurrentOfTheFortuneRiverAbility;
import com.yuanno.block_clover.spells.water.WaterBlessingAbility;
import com.yuanno.block_clover.spells.water.WaterShieldAbility;

import java.util.ArrayList;
import java.util.HashMap;

public class GainSpellHelper {
    
    private static final AbilityCore[] water5 = {
            WaterShieldAbility.INSTANCE, CurrentOfTheFortuneRiverAbility.INSTANCE, WaterBlessingAbility.INSTANCE     
    };
    
    private static HashMap<Integer, AbilityCore[]> spellPerLevelWater = new HashMap<>();
    static {
        spellPerLevelWater.put(5, water5);
    }
    
    public static HashMap<String, HashMap<Integer, AbilityCore[]>> spellsMap = new HashMap<>();
    static
    {
        spellsMap.put(ModValues.WATER, spellPerLevelWater);
    }
    
    public static ArrayList<Ability> returnAbilitiesPerLevelAttribute(String attribute, int level)
    {
        ArrayList<Ability> abilities = new ArrayList<>();
        
        AbilityCore[] abilityCores = spellsMap.get(attribute).get(level);
        for (AbilityCore abilityCore : abilityCores) {
            abilities.add(abilityCore.createAbility());
        }
        return abilities;
    }

}

package com.yuanno.block_clover.init;


import com.yuanno.block_clover.api.Quest.Quest;
import com.yuanno.block_clover.api.ability.AbilityCore;
import com.yuanno.block_clover.spells.beast.BearClawAbility;
import com.yuanno.block_clover.spells.copy.CopyAbility;
import com.yuanno.block_clover.spells.darkness.DarkCloakedBladeAbility;
import com.yuanno.block_clover.spells.earth.EarthChunkAbility;
import com.yuanno.block_clover.spells.fire.FireBallAbility;
import com.yuanno.block_clover.spells.light.LightBladeAbility;
import com.yuanno.block_clover.spells.lightning.ThunderGodBootsAbility;
import com.yuanno.block_clover.spells.mercury.MercuryBulletAbility;
import com.yuanno.block_clover.spells.sealing.SealingProjectileAbility;
import com.yuanno.block_clover.spells.slash.SlashBladesAbility;
import com.yuanno.block_clover.spells.sword.AirDashAbility;
import com.yuanno.block_clover.spells.time.TimeStealAbility;
import com.yuanno.block_clover.spells.water.WaterBallAbility;
import com.yuanno.block_clover.spells.wind.WindBladeAbility;
import org.lwjgl.system.CallbackI;

import java.util.*;

public class ModValues {
    public static final int MAX_DIFFICULTY_STARS = 15;

    public static final String NONE = "None";
    public static final int MAX_IN_PROGRESS_QUESTS = 3;
    //Attributes
    public static final String FIRE = "Fire";
    public static final String WATER = "Water";
    public static final String LIGHTNING = "Lightning";
    public static final String DARKNESS = "Darkness";
    public static final String EARTH = "Earth";
    public static final String LIGHT = "Light";
    public static final String SLASH = "Slash";
    public static final String WIND = "Wind";
    public static final String SEALING = "Sealing";
    public static final String ANTIMAGIC = "Anti_Magic";
    public static final String TIME = "Time";
    public static final String BEAST = "Beast";
    public static final String MERCURY = "Mercury";
    public static final String SWORD = "Sword";
    public static final String COPY = "Copy";
    public static List<String> availableAttributes = new ArrayList<>(Arrays.asList(
            ModValues.WIND, ModValues.FIRE, ModValues.LIGHT, ModValues.LIGHTNING, ModValues.DARKNESS,
            ModValues.EARTH, ModValues.SLASH, ModValues.SEALING, ModValues.TIME,
            ModValues.WATER, ModValues.MERCURY, ModValues.BEAST, ModValues.COPY, ModValues.SWORD
    ));
    public static final String[] attributes_no_antimagic = {
            ModValues.FIRE, ModValues.LIGHTNING, ModValues.DARKNESS, ModValues.EARTH, ModValues.LIGHT, ModValues.SLASH, ModValues.WIND, ModValues.SEALING, ModValues.WATER, ModValues.TIME
    };

    public static final String NAHAMAN = "Nahaman";
    public static final String WALGNER = "Walgner";
    public static final String LILITH = "Lilith";
    public static final String[] devils = {
            ModValues.NAHAMAN, ModValues.WALGNER, ModValues.LILITH
    };
    //Races
    public static final String HUMAN = "Human";
    public static final String ELF = "Elf";
    public static final String HYBRID = "Hybrid";
    public static final String DWARF = "Dwarf";
    public static final String WITCH = "Witch";
    public static final String[] races = {
      ModValues.HUMAN, ModValues.ELF, ModValues.HYBRID, ModValues.WITCH
    };

    // Define a static block to initialize the abilityMap
    private static Map<String, AbilityCore> abilityMap = new HashMap<>();
    static {
        abilityMap.put(ModValues.WIND, WindBladeAbility.INSTANCE);
        abilityMap.put(ModValues.FIRE, FireBallAbility.INSTANCE);
        abilityMap.put(ModValues.LIGHT, LightBladeAbility.INSTANCE);
        abilityMap.put(ModValues.LIGHTNING, ThunderGodBootsAbility.INSTANCE);
        abilityMap.put(ModValues.DARKNESS, DarkCloakedBladeAbility.INSTANCE);
        abilityMap.put(ModValues.EARTH, EarthChunkAbility.INSTANCE);
        abilityMap.put(ModValues.SLASH, SlashBladesAbility.INSTANCE);
        abilityMap.put(ModValues.SEALING, SealingProjectileAbility.INSTANCE);
        abilityMap.put(ModValues.TIME, TimeStealAbility.INSTANCE);
        abilityMap.put(ModValues.WATER, WaterBallAbility.INSTANCE);
        abilityMap.put(ModValues.MERCURY, MercuryBulletAbility.INSTANCE);
        abilityMap.put(ModValues.BEAST, BearClawAbility.INSTANCE);
        abilityMap.put(ModValues.COPY, CopyAbility.INSTANCE);
        abilityMap.put(ModValues.SWORD, AirDashAbility.INSTANCE);
        abilityMap.put(ModValues.ANTIMAGIC, null);

        // Optionally, you can assign the abilityMap to a static field for later access
        // StaticAbilityMapHolder.setAbilityMap(abilityMap);
    }

    // You can also define a static method to get the initialized abilityMap
    public static Map<String, AbilityCore> getAbilityMap() {
        return abilityMap;
    }

    public static final String RANK_QUEST_SSS = "SSS";
    public static final String RANK_QUEST_SS = "SS";
    public static final String RANK_QUEST_S = "S";
    public static final String RANK_QUEST_A = "A";
    public static final String RANK_QUEST_B = "B";
    public static final String RANK_QUEST_C = "C";
    public static final String RANK_QUEST_D = "D";

    //RANKS
    public static final String EMPEROR = "Magic Emperor";
    public static final String GRAND = "Grand Magic Knight";
    public static final String SENIOR = "Senior Magic Knight";
    public static final String INTERMEDIATE = "Intermediate Magic Knight";
    public static final String JUNIOR = "Junior Magic Knight";

    //TITLES
    public static final String BEOSTI = "Almighty Captain of Yuanno";
    public static final String SKYLING = "Engulfing sky";
    public static final String CONZILLA = "Collected storm";
    public static final String MADDOX = "Mad Goblin";
    public static final String KAUSU = "Sage";
    public static final String REDWOLF = "THE King";
}

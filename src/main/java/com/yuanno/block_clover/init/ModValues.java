package com.yuanno.block_clover.init;


import org.lwjgl.system.CallbackI;

public class ModValues {

    public static final String NONE = "None";

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
    public static final String[] attributes = {
            ModValues.FIRE, ModValues.LIGHTNING, ModValues.DARKNESS, ModValues.EARTH, ModValues.LIGHT, ModValues.SLASH, ModValues.WIND, ModValues.SEALING, ModValues.WATER, ModValues.TIME, ModValues.ANTIMAGIC
    };
    public static final String[] attributes_no_antimagic = {
            ModValues.FIRE, ModValues.LIGHTNING, ModValues.DARKNESS, ModValues.EARTH, ModValues.LIGHT, ModValues.SLASH, ModValues.WIND, ModValues.SEALING, ModValues.WATER, ModValues.TIME
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

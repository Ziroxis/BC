package com.yuanno.block_clover.init;


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
    public static final String ANTIMAGIC = "Anti-magic";
    public static final String[] attributes = {
            ModValues.FIRE, ModValues.LIGHTNING, ModValues.DARKNESS, ModValues.EARTH, ModValues.LIGHT, ModValues.SLASH, ModValues.WIND, ModValues.ANTIMAGIC
    };
    public static final String[] attributes_no_antimagic = {
            ModValues.FIRE, ModValues.LIGHTNING, ModValues.DARKNESS, ModValues.EARTH, ModValues.LIGHT, ModValues.SLASH, ModValues.WIND
    };

    //Races
    public static final String HUMAN = "Human";
    public static final String ELF = "Elf";
    public static final String HYBRID = "Hybrid";
    public static final String DWARF = "Dwarf";
    public static final String[] races = {
      ModValues.HUMAN, ModValues.ELF, ModValues.HYBRID
    };
}

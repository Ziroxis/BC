package com.yuanno.block_clover.data.entity;


import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.api.ability.Ability;

import java.util.HashMap;
import java.util.Map;

public class EntityStatsBase implements IEntityStats{


    private boolean inCombatMode = false;

    private String race = "";
    private String attribute = "";
    private  String secondAttribute = "";
    private int level;
    private int maxLevel;
    private int experience;
    private int maxExperience;
    private float mana;
    private float maxMana;
    private  float manaRegeneration;
    private boolean hasGrimoire = false;
    private int state = 0;
    private String title = "";
    private String rank = "";
    private int time;
    private float multiplier;
    private boolean staffBoost;
    private boolean hatBoost;
    private boolean canLink;
    private int cookingLevel;
    private int maxCookingLevel;
    private int cookingExperience;
    private int maxCookingExperience;
    private boolean innateDevil;
    private String devil = "";

    private HashMap<String, Integer> experienceSpells = new HashMap<String, Integer>();

    @Override
    public HashMap<String, Integer> getExperienceSpells() {
        return experienceSpells;
    }

    @Override
    public void setExperienceSpells(HashMap<String, Integer> experienceSpells) {
        this.experienceSpells = experienceSpells;
    }

    @Override
    public void setExperienceSpells(String key, Integer value) {
        experienceSpells.put(key, value);
    }

    @Override
    public <T> T getExperienceSpell(String key) {
        return (T) experienceSpells.get(key);
    }

    @Override
    public boolean hasExperienceSpell(String key) {
        return experienceSpells.containsKey(key);
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public void alterLevel(int value) {
        this.level = this.level + value;
    }

    @Override
    public void setLevel(int value) {
        this.level = value;
    }

    @Override
    public int getMaxLevel() {
        return this.maxLevel;
    }

    @Override
    public void alterMaxLevel(int value) {
        this.maxLevel = this.maxLevel + value;
    }

    @Override
    public void setMaxLevel(int value) {
        this.maxLevel = value;
    }

    @Override
    public int getExperience() {
        return this.experience;
    }

    @Override
    public void alterExperience(int value) {
        this.experience = this.experience + value;
    }

    @Override
    public void setExperience(int value) {
        this.experience = value;
    }

    @Override
    public int getMaxExperience() {
        return this.maxExperience;
    }

    @Override
    public void alterMaxExperience(int value) {
        this.maxExperience = this.maxExperience + value;
    }

    @Override
    public void setMaxExperience(int value) {
        this.maxExperience = value;
    }

    @Override
    public int getCookingLevel() {
        return this.cookingLevel;
    }

    @Override
    public void setCookingLevel(int value) {
        this.cookingLevel = value;
    }

    @Override
    public int getMaxCookingLevel() {
        return this.maxCookingLevel;
    }

    @Override
    public void setMaxCookingLevel(int value) {
        this.maxCookingLevel = value;
    }

    @Override
    public void alterCookingLevel(int value) {
        this.maxCookingLevel += value;
    }

    @Override
    public int getCookingExperience() {
        return this.cookingExperience;
    }

    @Override
    public void setCookingExperience(int value) {
        this.cookingExperience = value;
    }

    @Override
    public int getMaxCookingExperience() {
        return this.maxCookingExperience;
    }

    @Override
    public void setMaxCookingExperience(int value) {
        this.maxCookingExperience = value;
    }

    @Override
    public void alterCookingExperience(int value) {
        this.cookingExperience += value;
    }

    @Override
    public float getMultiplier() {
        return this.multiplier;
    }

    @Override
    public void alterMultiplier(float value) {
        this.multiplier = this.multiplier + value;
    }

    @Override
    public void setMultiplier(float value) {
        this.multiplier = value;
    }

    @Override
    public float getMana() {
        return this.mana;
    }

    @Override
    public void alterMana(float value) {
        this.mana = this.mana + value;
    }

    @Override
    public void setMana(float value) {
        this.mana = value;
    }

    @Override
    public float getMaxMana() {
        return this.maxMana;
    }

    @Override
    public void alterMaxMana(float value) {
        this.maxMana = this.maxMana + value;
    }

    @Override
    public void setMaxMana(float value) {
        this.maxMana = value;
    }

    @Override
    public float getManaRegeneration() {
        return this.manaRegeneration;
    }

    @Override
    public void alterManaRegeneration(float value) {
        this.manaRegeneration = this.manaRegeneration + value;
    }

    @Override
    public void setManaRegeneration(float value) {
        this.manaRegeneration = value;
    }

    @Override
    public boolean hasAttribute() {
        if (Beapi.isNullOrEmpty(this.attribute))
            return false;

        return true;
    }

    @Override
    public void setAttribute(String value) {
        this.attribute = value;
    }

    @Override
    public String getAttribute() {
        return this.attribute;
    }

    @Override
    public boolean hasSecondAttribute() {
        if (Beapi.isNullOrEmpty(this.secondAttribute))
            return false;
        return true;
    }

    @Override
    public void setSecondAttribute(String value) {
        this.secondAttribute = value;
    }

    @Override
    public String getSecondAttribute() {
        return this.secondAttribute;
    }

    @Override
    public boolean isInCombatMode() {
        return this.inCombatMode;
    }

    @Override
    public void setCombatMode(boolean value) {
        this.inCombatMode = value;
    }

    @Override
    public String getRace() {
        return this.race;
    }

    @Override
    public void setRace(String value) {
        this.race = value;
    }

    @Override
    public boolean hasRace() {
        if (Beapi.isNullOrEmpty(this.race))
            return false;

        return true;
    }

    @Override
    public boolean hasGrimoire() {
        return this.hasGrimoire;
    }

    @Override
    public void setGrimoire(boolean value) {
        this.hasGrimoire = value;
    }


    @Override
    public int getState()
    {
        return this.state;
    }

    @Override
    public void alterState(int value)
    {
        this.state = this.state + value;
    }

    @Override
    public void setState(int value)
    {
        this.state = value;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String value) {
        this.title = value;
    }

    @Override
    public boolean hasTitle() {
        if (Beapi.isNullOrEmpty(this.title))
            return false;

        return true;
    }

    @Override
    public String getRank() {
        return this.rank;
    }

    @Override
    public void setRank(String value) {
        this.rank = value;
    }

    @Override
    public boolean hasRank() {
        if (Beapi.isNullOrEmpty(this.rank))
            return false;

        return true;
    }

    @Override
    public int getTime() {
        return this.time;
    }

    @Override
    public void setTime(int value) {
        this.time = value;
    }

    @Override
    public void alterTime(int value) {
        this.time = this.time + value;
    }

    @Override
    public void setStaffBoost(boolean boost) {
        this.staffBoost = boost;
    }

    @Override
    public boolean getStaffBoost() {
        return this.staffBoost;
    }

    @Override
    public void setHatBoost(boolean boost) {
        this.hatBoost = boost;
    }

    @Override
    public boolean getHatBoost() {
        return this.hatBoost;
    }

    @Override
    public boolean getLinkAbility() {
        return canLink;
    }

    @Override
    public void setLinkAbility(boolean can) {
        this.canLink = can;
    }

    @Override
    public void setInnateDevil(boolean flag)
    {
        this.innateDevil = flag;
    }

    @Override
    public boolean getInnateDevil()
    {
        return this.innateDevil;
    }

    @Override
    public void setDevil(String devil)
    {
        this.devil = devil;
    }

    @Override
    public String getDevil()
    {
        return this.devil;
    }
}

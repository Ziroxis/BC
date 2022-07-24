package com.example.block_clover.data.entity;


import com.example.block_clover.api.Beapi;

public class EntityStatsBase implements IEntityStats{


    private boolean inCombatMode = false;

    private String race = "";
    private String attribute = "";
    private int level;
    private int maxLevel;
    private int experience;
    private int maxExperience;
    private float mana;
    private float maxMana;


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
}

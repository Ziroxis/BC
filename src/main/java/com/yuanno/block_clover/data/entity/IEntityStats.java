package com.yuanno.block_clover.data.entity;

public interface IEntityStats {

    // LEVEL
    int getLevel();
    void alterLevel(int value);
    void setLevel(int value);
    int getMaxLevel();
    void alterMaxLevel(int value);
    void setMaxLevel(int value);

    // EXPERIENCE
    int getExperience();
    void alterExperience(int value);
    void setExperience(int value);
    int getMaxExperience();
    void alterMaxExperience(int value);
    void setMaxExperience(int value);

    // MANA
    float getMana();
    void alterMana(float value);
    void setMana(float value);
    float getMaxMana();
    void alterMaxMana(float value);
    void setMaxMana(float value);

    // ATTRIBUTE
    boolean hasAttribute();
    void setAttribute(String value);
    String getAttribute();

    // COMBAT
    boolean isInCombatMode();
    void setCombatMode(boolean value);

    String getRace();
    void setRace(String value);
    boolean hasRace();

    // GRIMOIRE
    boolean hasGrimoire();
    void setGrimoire(boolean value);

    // STATES
    int getState();
    void alterState(int value);
    void setState(int value);

}

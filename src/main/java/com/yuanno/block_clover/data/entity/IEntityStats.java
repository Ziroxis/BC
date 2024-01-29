package com.yuanno.block_clover.data.entity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface IEntityStats {

    // RESET
    void fullReset();

    // YULE
    void setYule(int value);
    void addYule(int value);
    int getYule();


    // SPELL EXPERIENCE
    HashMap<String, Integer> getExperienceSpells();
    void setExperienceSpells(HashMap<String, Integer> experienceSpells);
    void setExperienceSpells(String key, Integer value);
    <T> T getExperienceSpell(String key);
    boolean hasExperienceSpell(String key);

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

    //COOKING
    int getCookingLevel();
    void setCookingLevel(int value);
    int getMaxCookingLevel();
    void setMaxCookingLevel(int value);
    void alterCookingLevel(int value);
    int getCookingExperience();
    void setCookingExperience(int value);
    int getMaxCookingExperience();
    void setMaxCookingExperience(int value);
    void alterCookingExperience(int value);




    // MULTIPLIER
    float getMultiplier();
    void alterMultiplier(float value);
    void setMultiplier(float value);


    // MANA
    float getMana();
    void alterMana(float value);
    void setMana(float value);
    float getMaxMana();
    void alterMaxMana(float value);
    void setMaxMana(float value);
    float getManaRegeneration();
    void alterManaRegeneration(float value);
    void setManaRegeneration(float value);

    // ATTRIBUTE
    boolean hasAttribute();
    void setAttribute(String value);
    String getAttribute();
    boolean hasSecondAttribute();
    void setSecondAttribute(String value);
    String getSecondAttribute();

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

    //TITLES
    String getTitle();
    void setTitle(String value);
    boolean hasTitle();

    //RANKS
    String getRank();
    void setRank(String value);
    boolean hasRank();

    //SPELL RELATED
    int getTime();
    void setTime(int value);
    void alterTime(int value);
	
    // Boosts
    void setStaffBoost(boolean boost);
    boolean getStaffBoost();
    void setHatBoost(boolean boost);
    boolean getHatBoost();

    boolean getLinkAbility();
    void setLinkAbility(boolean can);


    void setChosenAttributes(ArrayList<String> chosenAttributes);
    ArrayList<String> getChosenAttributes();
}

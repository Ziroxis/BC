package com.yuanno.block_clover.api.ability;

public enum AbilityPool {

    GEPPO_LIKE,
    TEKKAI_LIKE,
    SIMPLE_BUSOSHOKU_HAKI,
    MINK_ELECTRO,
    WEATHER_BALLS,
    OTO_ABILITY,
    ADVANCED_BUSOSHOKU_HAKI;

    public int id()
    {
        return ordinal() + 1;
    }}

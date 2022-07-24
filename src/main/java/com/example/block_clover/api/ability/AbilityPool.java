package com.example.block_clover.api.ability;

public enum AbilityPool {

    TECHNIQUE(1),
    OVERALL(2);

    private int id;

    private AbilityPool(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return this.id;
    }
}

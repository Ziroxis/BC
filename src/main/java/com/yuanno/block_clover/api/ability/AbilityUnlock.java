package com.yuanno.block_clover.api.ability;

import net.minecraftforge.common.IExtensibleEnum;

public enum AbilityUnlock implements IExtensibleEnum {

    PROGRESSION,
    COMMAND;

    public static AbilityUnlock create(String name)
    {
        throw new IllegalStateException("Enum not extended");
    }

}

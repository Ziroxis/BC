package com.yuanno.block_clover.init;

import com.yuanno.block_clover.triggers.UnlockAbilityTrigger;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModAdvancements {
    public static final UnlockAbilityTrigger UNLOCK_ABILITY = new UnlockAbilityTrigger();
    public static void register(IEventBus eventBus)
    {
        CriteriaTriggers.register(UNLOCK_ABILITY);
    }
}

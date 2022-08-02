package com.yuanno.block_clover.init;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.effects.LeoPalmaEffect;
import com.yuanno.block_clover.effects.MovementBlockedEffect;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEffects {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Main.MODID);

    public static final RegistryObject<Effect> LEO_PALMA = EFFECTS.register("leo_palma", LeoPalmaEffect::new);
    public static final RegistryObject<Effect> MOVEMENT_BLOCKED = EFFECTS.register("movement_blocked", MovementBlockedEffect::new);
}

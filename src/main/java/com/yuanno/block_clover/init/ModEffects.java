package com.yuanno.block_clover.init;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.effects.*;
import net.minecraft.potion.Effect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEffects {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Main.MODID);

    public static final RegistryObject<Effect> FREEZE = EFFECTS.register("freeze", FreezeEffect::new);
    public static final RegistryObject<Effect> ELECTROCUTED = EFFECTS.register("electrocuted", ElectrocutedEffect::new);
    public static final RegistryObject<Effect> CHRONO_STASIS = EFFECTS.register("chrono_stasis", ChronoStasisEffect::new);
    public static final RegistryObject<Effect> LEO_PALMA = EFFECTS.register("leo_palma", LeoPalmaEffect::new);
    public static final RegistryObject<Effect> MOVEMENT_BLOCKED = EFFECTS.register("movement_blocked", MovementBlockedEffect::new);
    public static final RegistryObject<Effect> SEALING = EFFECTS.register("sealing", SealingEffect::new);
    public static final RegistryObject<Effect> GRAVITY = EFFECTS.register("gravity", GravityEffect::new);
    public static final RegistryObject<Effect> XP_MULTIPLIER =  EFFECTS.register("xp_multiplier", XpMultiplierEffect::new);
    public static final RegistryObject<Effect> MANA_REGENERATION = EFFECTS.register("mana_regeneration", ManaRegenerationEffect::new);
    public static final RegistryObject<Effect> LIFE_CURSE = EFFECTS.register("life_curse", LifeCurseEffect::new);
    public static final RegistryObject<Effect> MAGIC_CURSE = EFFECTS.register("magic_curse", MagicCurseEffect::new);
    public static final RegistryObject<Effect> POISONOUS_CURSE = EFFECTS.register("poisonous_curse", PoisonousCurseEffect::new);
    public static final RegistryObject<Effect> HEALING_CURSE = EFFECTS.register("healing_curse", HealingCurseEffect::new);
    public static final RegistryObject<Effect> IN_EVENT = EFFECTS.register("in_event", InEventEffect::new);
    public static final RegistryObject<Effect> BUBBLED = EFFECTS.register("bubbled", BubbledEffect::new);


    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);

    }

}

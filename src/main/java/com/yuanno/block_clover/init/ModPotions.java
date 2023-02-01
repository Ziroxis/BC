package com.yuanno.block_clover.init;

import com.yuanno.block_clover.Main;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModPotions {

    public static DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, Main.MODID);

    public static final RegistryObject<Potion> MOGURO_POTION = POTIONS.register("moguro_potion", () -> new Potion(new EffectInstance(ModEffects.XP_MULTIPLIER.get(), 6000, 0)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}

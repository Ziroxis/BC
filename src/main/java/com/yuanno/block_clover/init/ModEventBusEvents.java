package com.yuanno.block_clover.init;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.entities.*;
import com.yuanno.block_clover.entities.summons.earth.EarthGolemEntity;
import com.yuanno.block_clover.entities.summons.earth.EarthMinionEntity;
import com.yuanno.block_clover.entities.summons.earth.EarthSummons;
import com.yuanno.block_clover.events.loot.ArtifactAdditionModifier;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event)
    {
        //CURSED SPIRITS
        event.put(EarthSummons.EARTH_MINION.get(), EarthMinionEntity.setCustomAttributes().build());
        event.put(EarthSummons.EARTH_GOLEM.get(), EarthGolemEntity.setCustomAttributes().build());
        event.put(ModEntities.GRIMOIRE_MAGICIAN.get(), GrimoireMagicianEntity.setCustomAttributes().build());
        event.put(ModEntities.BANDIT.get(), BanditEntity.setCustomAttributes().build());
        event.put(ModEntities.VOLCANO_MONSTER.get(), VolcanoMonsterEntity.setCustomAttributes().build());
        event.put(ModEntities.MONKEY_ENTITY.get(), MonkeyEntity.setCustomAttributes().build());
        event.put(ModEntities.CLOVER_SHARK.get(), CloverSharkEntity.setCustomAttributes().build());
    }

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {

        event.getRegistry().registerAll(
                new WitchHatShredAdditionModifier.Serializer().setRegistryName(new ResourceLocation(Main.MODID, "witch_hat_shred_from_witch"))

        );
    }


}
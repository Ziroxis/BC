package com.yuanno.block_clover.init;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.entities.*;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Main.MODID);
    public static final RegistryObject<EntityType<GrimoireMagicianEntity>> GRIMOIRE_MAGICIAN = ENTITIES
            .register("grimoire_magician",
                    () -> EntityType.Builder.of(GrimoireMagicianEntity::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "grimoire_magician").toString()));

    public static final RegistryObject<EntityType<BanditEntity>> BANDIT = ENTITIES
            .register("bandit",
                    () -> EntityType.Builder.of(BanditEntity::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(15)
                            .build(new ResourceLocation(Main.MODID, "bandit").toString()));
    public static final RegistryObject<EntityType<VolcanoMonsterEntity>> VOLCANO_MONSTER = ENTITIES
            .register("volcano_monster",
                    () -> EntityType.Builder.of(VolcanoMonsterEntity::new, EntityClassification.CREATURE)
                            .sized(3f, 6.5f)
                            .setTrackingRange(15)
                            .fireImmune()
                            .build(new ResourceLocation(Main.MODID, "volcano_monster").toString()));
    public static final RegistryObject<EntityType<MonkeyEntity>> MONKEY_ENTITY = ENTITIES
            .register("monkey_entity",
                    () -> EntityType.Builder.of(MonkeyEntity::new, EntityClassification.CREATURE)
                            .sized(1.55f, 2.2f)
                            .setTrackingRange(20)
                            .build(new ResourceLocation(Main.MODID, "monkey_entity").toString()));
    public static final RegistryObject<EntityType<CloverSharkEntity>> CLOVER_SHARK = ENTITIES
            .register("clover_shark",
                    () -> EntityType.Builder.of(CloverSharkEntity::new, EntityClassification.WATER_CREATURE)
                            .sized(2f, 2f)
                            .setTrackingRange(15)
                            .build(new ResourceLocation(Main.MODID, "clover_shark").toString()));
    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}

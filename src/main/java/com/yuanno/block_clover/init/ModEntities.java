package com.yuanno.block_clover.init;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.BeJavapi;
import com.yuanno.block_clover.api.BeModApi;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.entities.beastial.CloverSharkEntity;
import com.yuanno.block_clover.entities.beastial.FlameBoarEntity;
import com.yuanno.block_clover.entities.beastial.MonkeyEntity;
import com.yuanno.block_clover.entities.devils.LilithDevilEntity;
import com.yuanno.block_clover.entities.devils.NahamanDevilEntity;
import com.yuanno.block_clover.entities.devils.WalgnerDevilEntity;
import com.yuanno.block_clover.entities.humanoid.npc.BanditEntity;
import com.yuanno.block_clover.entities.humanoid.npc.GrimoireMagicianEntity;
import com.yuanno.block_clover.entities.humanoid.npc.GuildEntity;
import com.yuanno.block_clover.entities.humanoid.npc.QuestBoardManagerEntity;
import com.yuanno.block_clover.entities.misc.VolcanoMonsterEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Main.MODID);
    public static <T extends Entity> RegistryObject<EntityType<T>> registerEntityType(String localizedName, Supplier<EntityType<T>> supp)
    {
        String resourceName = BeJavapi.getResourceName(localizedName);
        Beapi.getLangMap().put("entity." + Main.MODID + "." + resourceName, localizedName);

        RegistryObject<EntityType<T>> reg = ENTITIES.register(resourceName, supp);

        return reg;
    }
    public static final RegistryObject<EntityType<GrimoireMagicianEntity>> GRIMOIRE_MAGICIAN = registerEntityType("Grimoire Magician", () -> BeModApi.createEntityType(GrimoireMagicianEntity::new).build(Main.MODID + ":grimoire_magician"));
    public static final RegistryObject<EntityType<GuildEntity>> GUILD_ENTITY = registerEntityType("Guild Manager", () -> BeModApi.createEntityType(GuildEntity::new).build(Main.MODID + ":guild_entity"));
    public static final RegistryObject<EntityType<BanditEntity>> BANDIT = ENTITIES
            .register("bandit",
                    () -> EntityType.Builder.of(BanditEntity::new, EntityClassification.CREATURE)
                            .sized(1f, 1.2f)
                            .setTrackingRange(15)
                            .build(new ResourceLocation(Main.MODID, "bandit").toString()));
    public static final RegistryObject<EntityType<QuestBoardManagerEntity>> QUEST_MANAGER = registerEntityType("Quest Manager", () -> BeModApi.createEntityType(QuestBoardManagerEntity::new).build(Main.MODID + ":quest_manager"));

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
    public static final RegistryObject<EntityType<FlameBoarEntity>> FLAME_BOAR = ENTITIES
            .register("flame_boar",
                    () -> EntityType.Builder.of(FlameBoarEntity::new, EntityClassification.CREATURE)
                            .sized(2f, 2f)
                            .setTrackingRange(15)
                            .build(new ResourceLocation(Main.MODID, "flame_boar").toString()));
    public static final RegistryObject<EntityType<WalgnerDevilEntity>> WALGNER_DEVIL = ENTITIES
            .register("walgner_devil",
                    () -> EntityType.Builder.of(WalgnerDevilEntity::new, EntityClassification.CREATURE)
                            .sized(1f, 3f)
                            .setTrackingRange(15)
                            .build(new ResourceLocation(Main.MODID, "walgner_devil").toString()));
    public static final RegistryObject<EntityType<LilithDevilEntity>> LILITH_DEVIL = ENTITIES
            .register("lilith_devil",
                    () -> EntityType.Builder.of(LilithDevilEntity::new, EntityClassification.CREATURE)
                            .sized(1f, 3f)
                            .setTrackingRange(15)
                            .build(new ResourceLocation(Main.MODID, "lilith_devil").toString()));
    public static final RegistryObject<EntityType<NahamanDevilEntity>> NAHAMAN_DEVIL = ENTITIES
            .register("nahaman_devil",
                    () -> EntityType.Builder.of(NahamanDevilEntity::new, EntityClassification.CREATURE)
                            .sized(1f, 3f)
                            .setTrackingRange(15)
                            .build(new ResourceLocation(Main.MODID, "nahaman_devil").toString()));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}

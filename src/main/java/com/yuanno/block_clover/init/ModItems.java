package com.yuanno.block_clover.init;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.items.*;
import com.yuanno.block_clover.items.artifacts.*;
import com.yuanno.block_clover.items.clothes.*;
import com.yuanno.block_clover.items.weapons.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);
    public List<Supplier<Item>> items = new ArrayList<>();

    //BLOCKS
    public static final RegistryObject<BlockItem> JUICER_BLOCK = ITEMS.register("juicer_block", JuicerBlockItem::new);

    //TREES
    //MOGURO
    public static final RegistryObject<BlockItem> MOGURO_PLANK = ITEMS.register("moguro_plank", () -> new BlockItem(ModBlocks.MOGURO_PLANK.get(),
            new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_BLOCKS)));
    public static final RegistryObject<BlockItem> MOGURO_LOG = ITEMS.register("moguro_log", () -> new BlockItem(ModBlocks.MOGURO_LOG.get(),
            new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_BLOCKS)));
    public static final RegistryObject<BlockItem> MOGURO_LEAF = ITEMS.register("moguro_leaf", () -> new BlockItem(ModBlocks.MOGURO_LEAF.get(),
            new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_BLOCKS)));
    public static final RegistryObject<BlockItem> MOGURO_SAPLING = ITEMS.register("moguro_sapling", () -> new BlockItem(ModBlocks.MOGURO_SAPLING.get(),
            new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_BLOCKS)));
    public static final RegistryObject<BlockItem> MOGURO_STAIRS = ITEMS.register("moguro_stairs", () -> new BlockItem(ModBlocks.MOGURO_STAIRS.get(),
            new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_BLOCKS)));
    public static final RegistryObject<BlockItem> MOGURO_SLABS = ITEMS.register("moguro_slabs", () -> new BlockItem(ModBlocks.MOGURO_SLABS.get(),
            new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_BLOCKS)));
    public static final RegistryObject<BlockItem> MOGURO_FENCE = ITEMS.register("moguro_fence", () -> new BlockItem(ModBlocks.MOGURO_FENCE.get(),
            new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_BLOCKS)));
    public static final RegistryObject<BlockItem> MOGURO_FENCE_GATE = ITEMS.register("moguro_fence_gate", () -> new BlockItem(ModBlocks.MOGURO_FENCE_GATE.get(),
            new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_BLOCKS)));
    public static final RegistryObject<BlockItem> MOGURO_PRESSURE_PLATE = ITEMS.register("moguro_pressure_plate", () -> new BlockItem(ModBlocks.MOGURO_PRESSURE_PLATE.get(),
            new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_BLOCKS)));
    public static final RegistryObject<BlockItem> MOGURO_BUTTON = ITEMS.register("moguro_button", () -> new BlockItem(ModBlocks.MOGURO_BUTTON.get(),
            new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_BLOCKS)));
    public static final RegistryObject<BlockItem> MOGURO_DOOR = ITEMS.register("moguro_door", () -> new BlockItem(ModBlocks.MOGURO_DOOR.get(),
            new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_BLOCKS)));
    public static final RegistryObject<BlockItem> MOGURO_TRAPDOOR = ITEMS.register("moguro_trapdoor", () -> new BlockItem(ModBlocks.MOGURO_TRAPDOOR.get(),
            new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_BLOCKS)));
    //ELDER
    public static final RegistryObject<BlockItem> ELDER_PLANK = ITEMS.register("elder_plank", () -> new BlockItem(ModBlocks.ELDER_PLANK.get(),
            new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_BLOCKS)));
    public static final RegistryObject<BlockItem> ELDER_LOG = ITEMS.register("elder_log", () -> new BlockItem(ModBlocks.ELDER_LOG.get(),
            new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_BLOCKS)));
    public static final RegistryObject<BlockItem> ELDER_LEAF = ITEMS.register("elder_leaf", () -> new BlockItem(ModBlocks.ELDER_LEAF.get(),
            new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_BLOCKS)));
    public static final RegistryObject<BlockItem> ELDER_SAPLING = ITEMS.register("elder_sapling", () -> new BlockItem(ModBlocks.ELDER_SAPLING.get(),
            new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_BLOCKS)));
    public static final RegistryObject<BlockItem> ELDER_STAIRS = ITEMS.register("elder_stairs", () -> new BlockItem(ModBlocks.ELDER_STAIRS.get(),
            new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_BLOCKS)));
    public static final RegistryObject<BlockItem> ELDER_SLABS = ITEMS.register("elder_slabs", () -> new BlockItem(ModBlocks.ELDER_SLABS.get(),
            new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_BLOCKS)));
    public static final RegistryObject<BlockItem> ELDER_FENCE = ITEMS.register("elder_fence", () -> new BlockItem(ModBlocks.ELDER_FENCE.get(),
            new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_BLOCKS)));
    public static final RegistryObject<BlockItem> ELDER_FENCE_GATE = ITEMS.register("elder_fence_gate", () -> new BlockItem(ModBlocks.ELDER_FENCE_GATE.get(),
            new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_BLOCKS)));
    public static final RegistryObject<BlockItem> ELDER_PRESSURE_PLATE = ITEMS.register("elder_pressure_plate", () -> new BlockItem(ModBlocks.ELDER_PRESSURE_PLATE.get(),
            new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_BLOCKS)));
    public static final RegistryObject<BlockItem> ELDER_BUTTON = ITEMS.register("elder_button", () -> new BlockItem(ModBlocks.ELDER_BUTTON.get(),
            new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_BLOCKS)));
    public static final RegistryObject<BlockItem> ELDER_DOOR = ITEMS.register("elder_door", () -> new BlockItem(ModBlocks.ELDER_DOOR.get(),
            new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_BLOCKS)));
    public static final RegistryObject<BlockItem> ELDER_TRAPDOOR = ITEMS.register("elder_trapdoor", () -> new BlockItem(ModBlocks.ELDER_TRAPDOOR.get(),
            new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_BLOCKS)));

    
    //ARTIFACTS
    public static final RegistryObject<Item> NIGHT_CROWN = ITEMS.register("night_crown", NightCrownItem::new);
    public static final RegistryObject<Item> LIGHT_WEIGHT_RING = ITEMS.register("light_weight_ring", LightWeightRingArtifactItem::new);
    public static final RegistryObject<Item> CHANGE_MAGIC_ITEM = ITEMS.register("artifact_change", MagicChangeArtifactItem::new);
    public static final RegistryObject<Item> SURPLUS_MAGIC_ITEM = ITEMS.register("artifact_surplus", MagicSurplusArtifactItem::new);
    public static final RegistryObject<Item> FIRE_BELT_ITEM = ITEMS.register("fire_belt", BeltOfFireArtifactItem::new);
    public static final RegistryObject<Item> IMPATIENCE_GLOVES = ITEMS.register("gloves_impatience", GlovesOfImpatienceArtifactItem::new);
    public static final RegistryObject<Item> RABBIT_FOOT = ITEMS.register("rabbit_foot", RabbitFootArtifactItem::new);
    public static final RegistryObject<Item> LUCKY_RING = ITEMS.register("lucky_ring", LuckyRingArtifactItem::new);
    public static final RegistryObject<Item> MITTENS_REGENERATION = ITEMS.register("mittens_regeneration", MittensOfRegenerationArtifactItem::new);
    public static final RegistryObject<Item> BRACELET_BRAVERY = ITEMS.register("bracelet_bravery", BraceletOfBraveryArtifactItem::new);
    public static final RegistryObject<Item> CHARM_PROTECTION = ITEMS.register("protecting_charm", CharmOfProtectionArtifactItem::new);
    public static final RegistryObject<Item> HEALTH_ARTIFACT = ITEMS.register("health_artifact", HealthArtifactItem::new);
    public static final RegistryObject<Item> REGEN_ARTIFACT = ITEMS.register("regen_artifact", RegenArtifactItem::new);
    public static final RegistryObject<Item> NECKLACE_AGITATION = ITEMS.register("necklace_agitation", NecklaceOfAgitationArtifactItem::new);
    public static final RegistryObject<Item> CLOAK_INVISIBILITY = ITEMS.register("cloak_invisibility", CloakOfInvisibilityArtifactItem::new);
    public static final RegistryObject<Item> BELT_AIR = ITEMS.register("air_belt", BeltOfAirArtifactItem::new);
    public static final RegistryObject<Item> BRACELET_PUSH = ITEMS.register("bracelet_push", BraceletOfPushArtifactItem::new);
    public static final RegistryObject<Item> RARE_CANDY = ITEMS.register("rare_candy", RareCandyArtifactItem::new);
    public static final RegistryObject<Item> MANA_ARTIFACT = ITEMS.register("maximizing_mana", ManaArtifactItem::new);
    public static final RegistryObject<Item> MANA_REGENERATION = ITEMS.register("mana_regeneration", ManaRegenerationArtifactItem::new);
    public static final RegistryObject<Item> BAG_GLUTTONY = ITEMS.register("bag_gluttony", BagOfGluttonyArtifactItem::new);
    public static final RegistryObject<Item> GREEN_THUMB = ITEMS.register("green_thumb", GreenThumbArtifactItem::new);
    public static final RegistryObject<Item> RACE_CHANGE = ITEMS.register("race_change", RaceChangeArtifactItem::new);



    //WEAPONS
    public static final RegistryObject<Item> LIGHT_SWORD = ITEMS.register("light_sword", LightSwordItem::new);
    public static final RegistryObject<Item> DEMON_SLAYER = ITEMS.register("demon_slayer_antimagic", DemonSlayerItem::new);
    public static final RegistryObject<Item> KATANA = ITEMS.register("yamis_katana", KatanaItem::new);
    public static final RegistryObject<Item> DEMON_DESTROYER = ITEMS.register("demon_destroyer", DemonDestroyerItem::new);
    public static final RegistryObject<Item> DEMON_DESTROYER_BLACK = ITEMS.register("demon_destroyer_black", DemonDestroyerItem::new);
    public static final RegistryObject<Item> DEMON_DESTROYER_ANTIMAGIC = ITEMS.register("demon_destroyer_antimagic", DemonDestroyerItem::new);

    public static final RegistryObject<Item> DEMON_DWELLER = ITEMS.register("demon_dweller", DemonDwellerItem::new);
    public static final RegistryObject<Item> DEMON_DWELLER_BLACK = ITEMS.register("demon_dweller_black", DemonDwellerItem::new);
    public static final RegistryObject<Item> DEMON_DWELLER_ANTIMAGIC = ITEMS.register("demon_dweller_antimagic", DemonDwellerRustyItem::new);


    //CLOTHES
    public static final RegistryObject<Item> MAGE_HAT = ITEMS.register("mage_hat", ()-> new MageArmorItem("mage", EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> MAGE_CHEST = ITEMS.register("mage_chest", ()-> new MageArmorItem("mage", EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> MAGE_LEGS = ITEMS.register("mage_legs", ()-> new MageArmorItem("mage", EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> MAGE_FEET = ITEMS.register("mage_feet", ()-> new MageArmorItem("mage", EquipmentSlotType.FEET));

    public static final RegistryObject<Item> ASTA_HAT = ITEMS.register("asta_hat", ()-> new AstaClothesItem("asta", EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> ASTA_CHEST = ITEMS.register("asta_chest", ()-> new AstaClothesItem("asta", EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> ASTA_LEGS = ITEMS.register("asta_legs", ()-> new AstaClothesItem("asta", EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> ASTA_BOOTS = ITEMS.register("asta_feet", ()-> new AstaClothesItem("asta", EquipmentSlotType.FEET));

    public static final RegistryObject<Item> ASTA_HAT2 = ITEMS.register("asta_2_hat", ()-> new AstaClothesPostItem("asta_2", EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> ASTA_CHEST2 = ITEMS.register("asta_2_chest", ()-> new AstaClothesPostItem("asta_2", EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> ASTA_LEGS2 = ITEMS.register("asta_2_legs", ()-> new AstaClothesPostItem("asta_2", EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> ASTA_BOOTS2 = ITEMS.register("asta_2_feet", ()-> new AstaClothesPostItem("asta_2", EquipmentSlotType.FEET));

    public static final RegistryObject<Item> YUNO_CHEST = ITEMS.register("yuno_chest", ()-> new YunoClothesItem("yuno", EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> YUNO_LEGS = ITEMS.register("yuno_legs", ()-> new YunoClothesItem("yuno", EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> YUNO_BOOTS = ITEMS.register("yuno_feet", ()-> new YunoClothesItem("yuno", EquipmentSlotType.FEET));

    public static final RegistryObject<Item> GOLDEN_DAWN_CHEST = ITEMS.register("golden_dawn_chest", ()-> new GoldenDawnUniArmorItem("golden_dawn", EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> GOLDEN_DAWN_LEGS = ITEMS.register("golden_dawn_legs", ()-> new GoldenDawnUniArmorItem("golden_dawn", EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> GOLDEN_DAWN_FEET = ITEMS.register("golden_dawn_feet", ()-> new GoldenDawnUniArmorItem("golden_dawn", EquipmentSlotType.FEET));

     public static final RegistryObject<Item> WITCH_HAT = ITEMS.register("witch_hat", WitchHatItem::new);

    //MISC
    public static final RegistryObject<Item> MAGICAL_MEAT = ITEMS.register("magical_meat", MagicalBeastMeatItem::new);
    public static final RegistryObject<Item> COOKED_MAGICAL_MEAT = ITEMS.register("cooked_magical_meat", CoockedMagicalBeastMeatItem::new);
    public static final RegistryObject<Item> WITCH_HAT_SHRED = ITEMS.register("witch_hat_shred",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1).tab(ModItemGroup.BLOCK_CLOVER_MISC)));
    public static final RegistryObject<Item> MAGIC_STAFF = ITEMS.register("magic_staff", MagicStaffItem::new);
    public static final RegistryObject<Item> MODIFYING_CRYSTAL = ITEMS.register("modifying_crystal",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1).tab(ModItemGroup.BLOCK_CLOVER_MISC)));
    public static final RegistryObject<Item> MODIFIED_MAGIC_WAND = ITEMS.register("modified_magic_staff", ModifiedMagicStaffItem::new);
    public static final RegistryObject<Item> MOGURO_JUICE = ITEMS.register("moguro_juice", MoguroJuiceItem::new);

    //SPAWN EGGS
    public static final RegistryObject<SpawnEggItem> VOLCANO_MONSTER_EGG = ITEMS.register("volcano_monster_egg",
            () -> new ModSpawnEggItem(ModEntities.VOLCANO_MONSTER, 0x879995, 0x576ABC, new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_MISC)));
    public static final RegistryObject<SpawnEggItem> MONKEY_EGG = ITEMS.register("monkey_egg",
            () -> new ModSpawnEggItem(ModEntities.MONKEY_ENTITY, 0x879895, 0x577ABC, new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_MISC)));
    public static final RegistryObject<SpawnEggItem> BANDIT_EGG = ITEMS.register("bandit_egg",
            () -> new ModSpawnEggItem(ModEntities.BANDIT, 0x879895, 0x577ABC, new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_MISC)));
    public static final RegistryObject<SpawnEggItem> CLOVER_SHARK = ITEMS.register("shark_egg",
            () -> new ModSpawnEggItem(ModEntities.CLOVER_SHARK, 0x879992, 0x578ABC, new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_MISC)));

}

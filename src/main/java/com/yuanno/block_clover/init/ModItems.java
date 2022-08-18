package com.yuanno.block_clover.init;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.items.CoockedMagicalBeastMeatItem;
import com.yuanno.block_clover.items.MagicalBeastMeatItem;
import com.yuanno.block_clover.items.ModSpawnEggItem;
import com.yuanno.block_clover.items.artifacts.*;
import com.yuanno.block_clover.items.clothes.*;
import com.yuanno.block_clover.items.weapons.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Food;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);
    public List<Supplier<Item>> items = new ArrayList<>();

    //ITEMS
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
    public static final RegistryObject<Item> CHARM_PROTECTION = ITEMS.register("charm_protection", CharmOfProtectionArtifactItem::new);
    public static final RegistryObject<Item> HEALTH_ARTIFACT = ITEMS.register("health_artifact", HealthArtifactItem::new);
    public static final RegistryObject<Item> REGEN_ARTIFACT = ITEMS.register("regen_artifact", RegenArtifactItem::new);
    public static final RegistryObject<Item> NECKLACE_AGITATION = ITEMS.register("necklace_agitation", NecklaceOfAgitationArtifactItem::new);
    public static final RegistryObject<Item> CLOAK_INVISIBILITY = ITEMS.register("cloak_invisibility", CloakOfInvisibilityArtifactItem::new);
    public static final RegistryObject<Item> BELT_AIR = ITEMS.register("belt_air", BeltOfAirArtifactItem::new);
    public static final RegistryObject<Item> BRACELET_PUSH = ITEMS.register("bracelet_push", BraceletOfPushArtifactItem::new);
    public static final RegistryObject<Item> RARE_CANDY = ITEMS.register("rare_candy", RareCandyArtifactItem::new);
    public static final RegistryObject<Item> MANA_ARTIFACT = ITEMS.register("mana_artifact", ManaArtifactItem::new);
    public static final RegistryObject<Item> MANA_REGENERATION = ITEMS.register("mana_regeneration", ManaRegenerationArtifactItem::new);
    public static final RegistryObject<Item> BAG_GLUTTONY = ITEMS.register("bag_gluttony", BagOfGluttonyArtifactItem::new);
    public static final RegistryObject<Item> GREEN_THUMB = ITEMS.register("green_thumb", GreenThumbArtifactItem::new);
    public static final RegistryObject<Item> JUMP_ARTIFACT = ITEMS.register("jump_artifact", JumpyArtifactItem::new);
    public static final RegistryObject<Item> RACE_CHANGE = ITEMS.register("race_change", RaceChangeArtifactItem::new);


    //WEAPONS
    public static final RegistryObject<Item> LIGHT_SWORD = ITEMS.register("light_sword", LightSwordItem::new);
    public static final RegistryObject<Item> DEMON_SLAYER = ITEMS.register("demon_slayer_antimagic", DemonSlayerItem::new);
    public static final RegistryObject<Item> KATANA = ITEMS.register("yamis_katana", KatanaItem::new);
    public static final RegistryObject<Item> DEMON_DESTROYER = ITEMS.register("demon_destroyer", DemonDestroyerItem::new);
    public static final RegistryObject<Item> DEMON_DWELLER = ITEMS.register("demon_dweller", DemonDwellerItem::new);


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

    //MISC
    public static final RegistryObject<Item> MAGICAL_MEAT = ITEMS.register("magical_meat", MagicalBeastMeatItem::new);
    public static final RegistryObject<Item> COOKED_MAGICAL_MEAT = ITEMS.register("cooked_magical_meat", CoockedMagicalBeastMeatItem::new);

    //SPAWN EGGS
    public static final RegistryObject<SpawnEggItem> VOLCANO_MONSTER_EGG = ITEMS.register("volcano_monster_egg",
            () -> new ModSpawnEggItem(ModEntities.VOLCANO_MONSTER, 0x879995, 0x576ABC, new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_MISC)));
    public static final RegistryObject<SpawnEggItem> MONKEY_EGG = ITEMS.register("monkey_egg",
            () -> new ModSpawnEggItem(ModEntities.MONKEY_ENTITY, 0x879895, 0x577ABC, new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_MISC)));
    public static final RegistryObject<SpawnEggItem> BANDIT_EGG = ITEMS.register("bandit_egg",
            () -> new ModSpawnEggItem(ModEntities.BANDIT, 0x879895, 0x577ABC, new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_MISC)));

}

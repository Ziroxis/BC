package com.yuanno.block_clover.init;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.items.clothes.*;
import com.yuanno.block_clover.items.weapons.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);
    public List<Supplier<Item>> items = new ArrayList<>();

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

}

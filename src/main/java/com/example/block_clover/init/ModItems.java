package com.example.block_clover.init;

import com.example.block_clover.Main;
import com.example.block_clover.items.LightSwordItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);
    public List<Supplier<Item>> items = new ArrayList<>();

    public static final RegistryObject<Item> LIGHT_SWORD = ITEMS.register("light_sword",
            () -> new LightSwordItem(new Item.Properties().tab(ItemGroup.TAB_COMBAT).stacksTo(1), 7, 1.9f));
    public static final RegistryObject<Item> DEMON_SLAYER = ITEMS.register("demon_slayer_antimagic",
            () -> new LightSwordItem(new Item.Properties().tab(ItemGroup.TAB_COMBAT).stacksTo(1), 7, 1.9f));

}

package com.yuanno.block_clover.init;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.blocks.AntiMagicBlock;
import com.yuanno.block_clover.blocks.LightningBlock;
import com.yuanno.block_clover.blocks.WindBlock;
import com.yuanno.block_clover.world.trees.MoguroTree;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MODID);
    public List<Supplier<Block>> blocks = new ArrayList<>();

    public static final RegistryObject<Block> ANTIMAGIC = BLOCKS.register("antimagic_block", AntiMagicBlock::new);
    public static final RegistryObject<Block> LIGHTNING = BLOCKS.register("lightning_block", LightningBlock::new);
    public static final RegistryObject<Block> WIND = BLOCKS.register("wind_block", WindBlock::new);

    public static final RegistryObject<Block> MOGURO_PLANK = BLOCKS.register("moguro_plank",
            () -> new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE)
                    .strength(1f, 3f)
                    .harvestTool(ToolType.AXE)
                    .harvestLevel(2)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> MOGURO_LOG = BLOCKS.register("moguro_log",
            () -> new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE)
                    .strength(2f, 7f)
                    .harvestTool(ToolType.AXE)
                    .harvestLevel(2)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> MOGURO_LEAF = BLOCKS.register("moguro_leaf",
            () -> new Block(AbstractBlock.Properties.of(Material.LEAVES, MaterialColor.COLOR_BLUE)
                    .strength(0.5f, 0.5f)
                    .harvestTool(ToolType.AXE)
                    .harvestLevel(0)
                    .sound(SoundType.GRASS)));

    public static final RegistryObject<Block> MOGURO_SAPLING = BLOCKS.register("moguro_sapling",
            () -> new SaplingBlock(new MoguroTree(), AbstractBlock.Properties.of(Material.WOOD)));

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(ModItemGroup.BLOCK_CLOVER_MISC)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}

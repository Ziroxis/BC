package com.yuanno.block_clover.init;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.blocks.*;
import com.yuanno.block_clover.blocks.wood.ModStandingSignBlock;
import com.yuanno.block_clover.blocks.wood.ModWallSignBlock;
import com.yuanno.block_clover.world.trees.ElderTree;
import com.yuanno.block_clover.world.trees.MoguroTree;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.audio.Sound;
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

    public static final RegistryObject<Block> JUICER = BLOCKS.register("juicer_block", JuicerBlock::new);
    public static final RegistryObject<Block> BAKING_OVEN = BLOCKS.register("baking_oven_block", BakingOvenBlock::new);
    public static final RegistryObject<Block> DEVIL_ALTAR = BLOCKS.register("devil_altar_block", DevilAltarBlock::new);

    //Moguro Tree
    public static final RegistryObject<Block> MOGURO_LOG = BLOCKS.register("moguro_log",
            () -> new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE)
                    .strength(2f, 7f)
                    .harvestTool(ToolType.AXE)
                    .harvestLevel(2)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> MOGURO_LEAF = BLOCKS.register("moguro_leaf",
            () -> new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES, MaterialColor.COLOR_BLUE)
                    .strength(0.5f, 0.5f)
                    .harvestTool(ToolType.AXE)
                    .harvestLevel(0)
                    .randomTicks()
                    .noOcclusion()
                    .sound(SoundType.GRASS)));

    public static final RegistryObject<Block> MOGURO_SAPLING = BLOCKS.register("moguro_sapling",
            () -> new SaplingBlock(new MoguroTree(), AbstractBlock.Properties.of(Material.WOOD).noCollission().randomTicks().sound(SoundType.GRASS)));

    public static final RegistryObject<Block> MOGURO_PLANK = BLOCKS.register("moguro_planks",
            () -> new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE)
                    .strength(1f, 3f)
                    .harvestTool(ToolType.AXE)
                    .harvestLevel(2)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> MOGURO_STAIRS = BLOCKS.register("moguro_stairs",
            () -> new StairsBlock(() -> MOGURO_PLANK.get().defaultBlockState(), AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE)
            .strength(1f, 3f)
            .harvestTool(ToolType.AXE)
            .harvestLevel(2)
            .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> MOGURO_SLABS = BLOCKS.register("moguro_slab",
            () -> new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE)
                    .strength(1f, 3f)
                    .harvestTool(ToolType.AXE)
                    .harvestLevel(2)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> MOGURO_FENCE = BLOCKS.register("moguro_fence",
            () -> new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE)
                    .strength(1f, 3f)
                    .harvestTool(ToolType.AXE)
                    .harvestLevel(2)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> MOGURO_FENCE_GATE = BLOCKS.register("moguro_fence_gate",
            () -> new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE)
                    .strength(1f, 3f)
                    .harvestTool(ToolType.AXE)
                    .harvestLevel(2)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> MOGURO_PRESSURE_PLATE = BLOCKS.register("moguro_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE)
                    .strength(1f, 3f)
                    .harvestTool(ToolType.AXE)
                    .harvestLevel(2)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> MOGURO_BUTTON = BLOCKS.register("moguro_button",
            () -> new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE)
                    .strength(1f, 3f)
                    .harvestTool(ToolType.AXE)
                    .harvestLevel(2)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> MOGURO_DOOR = BLOCKS.register("moguro_door",() -> new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE)
            .strength(1f, 3f)
            .harvestTool(ToolType.AXE)
            .harvestLevel(2)
            .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> MOGURO_TRAPDOOR = BLOCKS.register("moguro_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE)
            .strength(1f, 3f)
            .harvestTool(ToolType.AXE)
            .harvestLevel(2)
            .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> MOGURO_SIGN = BLOCKS.register("moguro_sign",
            () -> new ModStandingSignBlock(AbstractBlock.Properties.of(Material.WOOD).strength(1f, 3f)
                    .harvestTool(ToolType.AXE)
                    .harvestLevel(2)
                    .sound(SoundType.WOOD), ModWoodTypes.MOGURO));

    public static final RegistryObject<Block> MOGURO_WALL_SIGN = BLOCKS.register("moguro_wall_sign",
            () -> new ModWallSignBlock(AbstractBlock.Properties.of(Material.WOOD).strength(1f, 3f)
                    .harvestTool(ToolType.AXE)
                    .harvestLevel(2)
                    .sound(SoundType.WOOD), ModWoodTypes.MOGURO));

    //Elder Tree
    public static final RegistryObject<Block> ELDER_LOG = BLOCKS.register("elder_log",
            () -> new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GRAY)
                    .strength(3f, 8f)
                    .harvestTool(ToolType.AXE)
                    .harvestLevel(3)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> ELDER_LEAF = BLOCKS.register("elder_leaf",
            () -> new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES, MaterialColor.COLOR_LIGHT_GRAY)
                    .strength(1.5f, 1.5f)
                    .harvestTool(ToolType.AXE)
                    .harvestLevel(2)
                    .randomTicks()
                    .noOcclusion()
                    .sound(SoundType.GRASS)
            ));

    public static final RegistryObject<Block> ELDER_PLANK = BLOCKS.register("elder_planks",
            () -> new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GRAY)
                    .strength(2f, 4f)
                    .harvestTool(ToolType.AXE)
                    .harvestLevel(3)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> ELDER_STAIRS = BLOCKS.register("elder_stairs",
            () -> new StairsBlock(() -> ELDER_PLANK.get().defaultBlockState(), AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GRAY)
                    .strength(2f, 4f)
                    .harvestTool(ToolType.AXE)
                    .harvestLevel(3)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> ELDER_SLABS = BLOCKS.register("elder_slab",
            () -> new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GRAY)
                    .strength(2f, 4f)
                    .harvestTool(ToolType.AXE)
                    .harvestLevel(3)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> ELDER_FENCE = BLOCKS.register("elder_fence",
            () -> new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GRAY)
                    .strength(2f, 4f)
                    .harvestTool(ToolType.AXE)
                    .harvestLevel(3)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> ELDER_FENCE_GATE = BLOCKS.register("elder_fence_gate",
            () -> new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GRAY)
                    .strength(2f, 4f)
                    .harvestTool(ToolType.AXE)
                    .harvestLevel(3)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> ELDER_PRESSURE_PLATE = BLOCKS.register("elder_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GRAY)
                    .strength(2f, 4f)
                    .harvestTool(ToolType.AXE)
                    .harvestLevel(3)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> ELDER_BUTTON = BLOCKS.register("elder_button",
            () -> new StoneButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GRAY)
                    .strength(2f, 4f)
                    .harvestTool(ToolType.AXE)
                    .harvestLevel(3)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> ELDER_DOOR = BLOCKS.register("elder_door", () -> new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GRAY)
            .strength(2f, 4f)
            .harvestTool(ToolType.AXE)
            .harvestLevel(3)
            .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> ELDER_TRAPDOOR = BLOCKS.register("elder_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GRAY)
            .strength(2f, 4f)
            .harvestTool(ToolType.AXE)
            .harvestLevel(3)
            .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> ELDER_SIGN = BLOCKS.register("elder_sign",
            () -> new ModStandingSignBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2f, 4f)
                    .harvestTool(ToolType.AXE)
                    .harvestLevel(3)
                    .sound(SoundType.WOOD), ModWoodTypes.ELDER));

    public static final RegistryObject<Block> ELDER_WALL_SIGN = BLOCKS.register("elder_wall_sign",
            () -> new ModWallSignBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2f, 4f)
                    .harvestTool(ToolType.AXE)
                    .harvestLevel(3)
                    .sound(SoundType.WOOD), ModWoodTypes.ELDER));

    public static final RegistryObject<Block> ELDER_SAPLING = BLOCKS.register("elder_sapling",
            () -> new SaplingBlock(new ElderTree(), AbstractBlock.Properties.of(Material.WOOD).noCollission().randomTicks().sound(SoundType.ROOTS)));

    public static final RegistryObject<Block> NOMOTATO_BLOCK = BLOCKS.register("nomotato_block", NomotatoBlock::new);
    public static final RegistryObject<Block> DEVIL_SUMMONER = BLOCKS.register("devil_summoner", DevilSummonerBlock::new);
    public static final RegistryObject<Block> QUEST_BOARD = BLOCKS.register("quest_board", QuestBoardBlock::new);
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
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

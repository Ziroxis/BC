package com.yuanno.block_clover.init;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.blocks.tileentities.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModTileEntities {

    private static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Main.MODID);

    public static final RegistryObject<TileEntityType<AntiMagicBlockTileEntity>> ANTIMAGIC_TILEENTITY = registerTileEntity("antimagic_tile",
            () -> TileEntityType.Builder.of(AntiMagicBlockTileEntity::new, ModBlocks.ANTIMAGIC.get()).build(null));
    public static final RegistryObject<TileEntityType<LightningBlockTileEntity>> LIGHTNING_TILEENTITY = registerTileEntity("lightning_tile",
            () -> TileEntityType.Builder.of(LightningBlockTileEntity::new, ModBlocks.LIGHTNING.get()).build(null));
    public static final RegistryObject<TileEntityType<WindBlockTileEntity>> WIND_TILEENTITY = registerTileEntity("wind_tile",
            () -> TileEntityType.Builder.of(WindBlockTileEntity::new, ModBlocks.WIND.get()).build(null));
    public static final RegistryObject<TileEntityType<JuicerBlockTileEntity>> JUICER_TILEENTITY = registerTileEntity("juicer_tile",
            () -> TileEntityType.Builder.of(JuicerBlockTileEntity::new, ModBlocks.JUICER.get()).build(null));
    public static final RegistryObject<TileEntityType<BakingOvenBlockTileEntity>> BAKING_OVEN_TILEENTITY = registerTileEntity("baking_oven_tile",
            () -> TileEntityType.Builder.of(BakingOvenBlockTileEntity::new, ModBlocks.BAKING_OVEN.get()).build(null));

    private static <T extends TileEntity> RegistryObject<TileEntityType<T>> registerTileEntity(String localizedName, Supplier<TileEntityType<T>> type)
    {
        String resourceName = Beapi.getResourceName(localizedName);

        RegistryObject<TileEntityType<T>> reg = TILE_ENTITIES.register(resourceName, type);

        return reg;
    }

    public static void register(IEventBus eventBus)
    {
        TILE_ENTITIES.register(eventBus);
    }
}

package com.yuanno.block_clover.init;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.Beapi;
import com.yuanno.block_clover.blocks.tileentities.AntiMagicBlockTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModTileEntities {

    private static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Main.MODID);

    public static final RegistryObject<TileEntityType<AntiMagicBlockTileEntity>> ANTIMAGIC_TILEENTITY = registerTileEntity("antimagic_tile", () -> TileEntityType.Builder.of(AntiMagicBlockTileEntity::new, ModBlocks.ANTIMAGIC.get()).build(null));

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

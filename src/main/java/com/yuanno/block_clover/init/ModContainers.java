package com.yuanno.block_clover.init;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.blocks.containers.BakingOvenContainer;
import com.yuanno.block_clover.blocks.containers.DevilAltarContainer;
import com.yuanno.block_clover.blocks.containers.JuicerContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {

    public static DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Main.MODID);

    public static final RegistryObject<ContainerType<JuicerContainer>> JUICER_CONTAINER = CONTAINERS.register("juicer_container", () -> IForgeContainerType.create(((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        World world = inv.player.level;
        return new JuicerContainer(windowId, world, pos, inv, inv.player);

    })));

    public static final RegistryObject<ContainerType<DevilAltarContainer>> DEVIL_ALTAR_CONTAINER = CONTAINERS.register("devil_altar_container", () -> IForgeContainerType.create(((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        World world = inv.player.level;
        return new DevilAltarContainer(windowId, world, pos, inv, inv.player);

    })));

    public static final RegistryObject<ContainerType<BakingOvenContainer>> BAKING_OVEN_CONTAINER = CONTAINERS.register("baking_oven_container", () -> IForgeContainerType.create(((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        World world = inv.player.level;
        return new BakingOvenContainer(windowId, world, pos, inv, inv.player);

    })));

    public static void register(IEventBus event) {
        CONTAINERS.register(event);
    }
}

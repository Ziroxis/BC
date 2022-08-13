package com.yuanno.block_clover.items;

import com.yuanno.block_clover.Main;
import com.yuanno.block_clover.api.curios.CuriosApi;
import com.yuanno.block_clover.init.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ArtifactEvents {

    @SubscribeEvent
    public static void bagOfGluttony(ItemTossEvent event)
    {
        PlayerEntity player = event.getPlayer();
        if (CuriosApi.getCuriosHelper().findEquippedCurio(ModItems.BAG_GLUTTONY.get(), player).isPresent())
        {
            event.getEntityItem().remove();
        }
    }

    @SubscribeEvent
    public static void greenThumb(PlayerInteractEvent event)
    {
        if (CuriosApi.getCuriosHelper().findEquippedCurio(ModItems.GREEN_THUMB.get(), event.getPlayer()).isPresent())
        {
            BlockPos pos = event.getPos();
            World world = event.getWorld();
            BlockState blockState = world.getBlockState(pos);
            if (!world.isClientSide) {
                if (blockState.getBlock() instanceof IGrowable && blockState.getBlock() != Blocks.GRASS_BLOCK) {
                    ((IGrowable) blockState.getBlock()).performBonemeal((ServerWorld) world, world.random, pos, blockState);
                    world.levelEvent(2005, pos, 0);
                }
            }
        }
    }
}

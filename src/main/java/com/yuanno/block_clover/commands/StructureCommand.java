package com.yuanno.block_clover.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.yuanno.block_clover.api.Beapi;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.BlockPosArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.IntegrityProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StructureCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher)
    {
        LiteralArgumentBuilder<CommandSource> builder = Commands.literal("beapi").requires(source -> source.hasPermission(3));

        builder
                .then(Commands.literal("save_structure")
                        .then(Commands.argument("from", BlockPosArgument.blockPos())
                                .then(Commands.argument("to", BlockPosArgument.blockPos())
                                        .then(Commands.argument("name", StringArgumentType.word())
                                                .executes(context -> saveStructure(context, BlockPosArgument.getOrLoadBlockPos(context, "from"), BlockPosArgument.getOrLoadBlockPos(context, "to"), StringArgumentType.getString(context, "name"), context.getSource().getPlayerOrException()))))));

        builder
                .then(Commands.literal("load_structure")
                        .executes(context -> loadStructure(context, context.getSource().getPlayerOrException())));

        dispatcher.register(builder);
    }

    private static int saveStructure(CommandContext<CommandSource> context, BlockPos from, BlockPos to, String name, ServerPlayerEntity player)
    {
        for(int i = from.getX(); i < to.getX(); i++)
        {
            for(int j = from.getY(); j < to.getY(); j++)
            {
                for(int k = from.getZ(); k < to.getZ(); k++)
                {
                    BlockPos pos = new BlockPos(i, j, k);
                    BlockState state = player.level.getBlockState(pos);

                    if(state.hasProperty(BlockStateProperties.WATERLOGGED))
                    {
                        state = state.setValue(BlockStateProperties.WATERLOGGED, false);
                        player.level.setBlockAndUpdate(pos, state);
                    }

                    if(state.getBlock() == Blocks.STRUCTURE_VOID)
                        player.level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2 | 16);

                    if(state.getBlock() == Blocks.BARREL)
                    {
                        player.level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2 | 16);
                    }
                }
            }
        }
        if(player.level instanceof ServerWorld)
        {
            BlockPos size = to.subtract(from);
            System.out.println("From: /tp " + from.getX() + " " + from.getY() + " " + from.getZ());
            System.out.println("To: /tp " + to.getX() + " " + to.getY() + " " + to.getZ());
            System.out.println("size: " + size);
            List<Block> bannedBlocks = new ArrayList<Block>();
            bannedBlocks.add(Blocks.WATER);
            Beapi.saveNBTStructure((ServerWorld) player.level, name, from, size, bannedBlocks);

            player.sendMessage(new StringTextComponent("Done"), Util.NIL_UUID);
        }
        return 1;
    }

    private static int loadStructure(CommandContext<CommandSource> context, ServerPlayerEntity player)
    {
        PlacementSettings placement = (new PlacementSettings()).setMirror(Mirror.NONE).setRotation(Rotation.NONE).setIgnoreEntities(true).setChunkPos((ChunkPos) null);
        placement.clearProcessors()
                .addProcessor(BlockIgnoreStructureProcessor.AIR)
                .addProcessor(new IntegrityProcessor(1f)).setRandom(new Random(Util.getMillis()));

        Beapi.loadNBTStructure((ServerWorld) player.level, "guild_tower", player.blockPosition(), placement);
        player.sendMessage(new StringTextComponent("Done"), Util.NIL_UUID);
        return 1;
    }

}

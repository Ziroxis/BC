package com.yuanno.block_clover.blocks;

import com.yuanno.block_clover.blocks.containers.JuicerContainer;
import com.yuanno.block_clover.blocks.tileentities.BakingOvenBlockTileEntity;
import com.yuanno.block_clover.blocks.tileentities.BakingOvenBlockTileEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Random;


public class BakingOvenBlock extends ContainerBlock {
    public static final DirectionProperty FACING = HorizontalBlock.FACING;

    public BakingOvenBlock() {
        super(AbstractBlock.Properties.of(Material.METAL, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(3.5F));
    }

    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader reader) {
        return new BakingOvenBlockTileEntity();
    }

    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult trace) {
        if (!world.isClientSide()) {
            TileEntity tileEntity = world.getBlockEntity(pos);
            if (tileEntity instanceof BakingOvenBlockTileEntity) {
                INamedContainerProvider containerProvider = createContainerProvider(world, pos);

                NetworkHooks.openGui(((ServerPlayerEntity) player), containerProvider, tileEntity.getBlockPos());
            } else {
                throw new IllegalStateException("Container provider is missing!");
            }

        }
        return ActionResultType.SUCCESS;
    }

    private INamedContainerProvider createContainerProvider(World world, BlockPos pos) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.block_clover.baking_oven");
            }

            @Nullable
            @Override
            public Container createMenu(int i, PlayerInventory inv, PlayerEntity player) {
                return new JuicerContainer(i, world, pos, inv, player);
            }
        };
    }

    @Override
    public void onRemove(BlockState state, World world, BlockPos pos, BlockState newstate, boolean sim) {
        if (!state.is(newstate.getBlock())) {
            TileEntity tileentity = world.getBlockEntity(pos);
            if (tileentity instanceof BakingOvenBlockTileEntity) {
                InventoryHelper.dropContents(world, pos, (BakingOvenBlockTileEntity) tileentity);
                world.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, world, pos, newstate, sim);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateContainer) {
        stateContainer.add(FACING);
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
        if (world.getBlockEntity(pos) instanceof BakingOvenBlockTileEntity) {
            BakingOvenBlockTileEntity tileEntity = (BakingOvenBlockTileEntity) world.getBlockEntity(pos);
            if (tileEntity.isOn) {
                //copied from furnace no clue what the args
                double d0 = (double) pos.getX() + 0.5D;
                double d1 = (double) pos.getY() + 1.0D;
                double d2 = (double) pos.getZ() + 0.5D;
                if (rand.nextDouble() < 0.2D) {
                    world.playLocalSound(d0, d1, d2, SoundEvents.AMBIENT_UNDERWATER_LOOP_ADDITIONS, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
                }
                Direction direction = state.getValue(FACING);
                Direction.Axis direction$axis = direction.getAxis();
                double d3 = 0.52D;
                double d4 = rand.nextDouble() * 0.6D - 0.3D;
                double d5 = direction$axis == Direction.Axis.X ? (double) direction.getStepX() * 0.52D : d4;
                double d6 = rand.nextDouble() * 6.0D / 16.0D;
                double d7 = direction$axis == Direction.Axis.Z ? (double) direction.getStepZ() * 0.52D : d4;
                world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}

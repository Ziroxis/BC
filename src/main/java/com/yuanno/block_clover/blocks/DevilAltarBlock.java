package com.yuanno.block_clover.blocks;

import com.yuanno.block_clover.blocks.containers.DevilAltarContainer;
import com.yuanno.block_clover.blocks.tileentities.DevilAltarTileEntity;
import com.yuanno.block_clover.init.ModTileEntities;
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

public class DevilAltarBlock extends ContainerBlock {
    public static final DirectionProperty FACING = HorizontalBlock.FACING;

    public DevilAltarBlock() {
        super(Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(4F));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.MODEL;
    }

    /*
    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader reader) {
        return new DevilAltarTileEntity();
    }
     */
    /*
    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
    return ModTileEntities.DEVIL_ALTAR_TILEENTITY.get().create();
    }

     */

    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult trace) {
        if (!world.isClientSide()) {
            TileEntity tileEntity = world.getBlockEntity(pos);
            if (tileEntity instanceof DevilAltarTileEntity) {
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
                return new TranslationTextComponent("screen.block_clover.devil_altar");
            }

            @Nullable
            @Override
            public Container createMenu(int i, PlayerInventory inv, PlayerEntity player) {
                return new DevilAltarContainer(i, world, pos, inv, player);
            }
        };
    }

    @Override
    public void onRemove(BlockState state, World world, BlockPos pos, BlockState newstate, boolean sim) {
        if (!state.is(newstate.getBlock())) {
            TileEntity tileentity = world.getBlockEntity(pos);
            if (tileentity instanceof DevilAltarTileEntity) {
                //InventoryHelper.dropContents(world, pos, (DevilAltarTileEntity) tileentity);
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

    @Override
        @OnlyIn(Dist.CLIENT)
        public void animateTick(BlockState p_180655_1_, World p_180655_2_, BlockPos p_180655_3_, Random p_180655_4_) {
            super.animateTick(p_180655_1_, p_180655_2_, p_180655_3_, p_180655_4_);

            for(int i = -2; i <= 2; ++i) {
                for(int j = -2; j <= 2; ++j) {
                    if (i > -2 && i < 2 && j == -1) {
                        j = 2;
                    }

                    if (p_180655_4_.nextInt(16) == 0) {
                        for(int k = 0; k <= 1; ++k) {
                            BlockPos blockpos = p_180655_3_.offset(i, k, j);
                            if (p_180655_2_.getBlockState(blockpos).getEnchantPowerBonus(p_180655_2_, blockpos) > 0) {
                                if (!p_180655_2_.isEmptyBlock(p_180655_3_.offset(i / 2, 0, j / 2))) {
                                    break;
                                }

                                p_180655_2_.addParticle(ParticleTypes.ENCHANT, (double)p_180655_3_.getX() + 0.5D, (double)p_180655_3_.getY() + 2.0D, (double)p_180655_3_.getZ() + 0.5D, (double)((float)i + p_180655_4_.nextFloat()) - 0.5D, (double)((float)k - p_180655_4_.nextFloat() - 1.0F), (double)((float)j + p_180655_4_.nextFloat()) - 0.5D);
                            }
                        }
                    }
                }
            }

        }

    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader p_196283_1_) {
        return null;
    }
}
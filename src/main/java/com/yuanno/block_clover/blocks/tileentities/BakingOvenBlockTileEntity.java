package com.yuanno.block_clover.blocks.tileentities;

import com.yuanno.block_clover.data.recipes.JuicerRecipe;
import com.yuanno.block_clover.data.recipes.ModRecipes;
import com.yuanno.block_clover.init.ModItems;
import com.yuanno.block_clover.init.ModTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class BakingOvenBlockTileEntity extends TileEntity implements ITickableTileEntity, ISidedInventory {
    private final ItemStackHandler stackHandler = createStackHandler(this);
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> stackHandler);
    //time it needs to process the leaf
    private short processTime;
    //time the leaf has already been processing
    private short workTime;
    //needed for atomisation with hoppers
    private static final int[] SLOTS_FOR_INPUTS = new int[]{0, 1, 2,3,4,5};
    private static final int[] SLOTS_FOR_DOWN = new int[]{6};

    public boolean isOn = false;

    protected NonNullList<ItemStack> items = NonNullList.withSize(6, ItemStack.EMPTY);

    public BakingOvenBlockTileEntity(TileEntityType<?> tiletype) {
        super(tiletype);
    }

    public BakingOvenBlockTileEntity() {
        this(ModTileEntities.BAKING_OVEN_TILEENTITY.get());
    }

    public int getWorkTime() {
        return workTime;
    }
    //TODO look at this
    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        this.processTime = nbt.getShort("processTime");
        this.workTime = nbt.getShort("workTime");
        this.isOn = nbt.getBoolean("isOn");
        stackHandler.deserializeNBT(nbt.getCompound("inv"));
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, this.items);

        super.load(state, nbt);
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        nbt.put("inv", stackHandler.serializeNBT());
        nbt.putShort("processTime", this.processTime);
        nbt.putShort("workTime", this.workTime);
        nbt.putBoolean("isOn", this.isOn);
        ItemStackHelper.saveAllItems(nbt, this.items);
        return super.save(nbt);
    }

    private ItemStackHandler createStackHandler(BakingOvenBlockTileEntity tile) {
        return new ItemStackHandler(4) {
            @Override
            protected void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return true;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!isItemValid(slot, stack)) {
                    return stack;
                }
                //When the Item gets inserted sets the time to zero
                tile.workTime = 0;
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(cap);
    }


    public void createResult(ItemStack output) {
        //delets the items
        this.stackHandler.extractItem(0, 1, false);
        this.stackHandler.extractItem(1, 1, false);
        this.stackHandler.extractItem(2, 1, false);
        this.stackHandler.extractItem(3,1,false);
        this.stackHandler.extractItem(4,1,false);
        this.stackHandler.extractItem(5,1,false);

        //puts in the new items
        this.stackHandler.insertItem(6, output, false);
    }

    @Override
    public void tick() {
        Inventory inv = new Inventory(stackHandler.getSlots());
        for (int i = 0; i < stackHandler.getSlots(); i++) {
            inv.setItem(i, stackHandler.getStackInSlot(i));
        }

        RecipeManager rm = level.getRecipeManager();
        IRecipeType<JuicerRecipe> juicerRecipe = ModRecipes.JUICER_RECIPE;
        Optional<JuicerRecipe> recipe = rm.getRecipeFor(juicerRecipe, inv, level);

        if (recipe.isPresent()) {

                // ads 1 to the time for every tick and if the time is reached finishes the recipe
                this.workTime += 1;
                isOn = true;
                if (this.workTime >= this.processTime) {
                    //waits until the slot is empty before putting in the new item
                    if (this.stackHandler.getStackInSlot(3).getCount() == 0) {
                        createResult(recipe.get().getResultItem());
                        isOn = false;
                        this.workTime = 0;
                        setChanged();
                    }
            } else {
                isOn = false;
                this.workTime = 0;

                setChanged();
            }
        } else {
            this.workTime = 0;
            isOn = false;
        }
    }

    @Override
    public int[] getSlotsForFace(Direction direction) {
        if (direction == Direction.DOWN) {
            return SLOTS_FOR_DOWN;
        } else {
            return SLOTS_FOR_INPUTS;
        }
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        if (direction != null) {
            int slot = getSlotsForFace(direction)[0];
            if (index == slot) {
                return this.stackHandler.isItemValid(slot, stack);
            }
        }
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        if (direction == Direction.DOWN) {
            if(index == 3) {
                return true;
            } else if (index == 0 && this.stackHandler.getStackInSlot(0).getItem() == Items.BUCKET) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getContainerSize() {
        return 4;
    }

    @Override
    public boolean isEmpty() {
        //checks if all the slots are empty
        return this.stackHandler.getStackInSlot(0).getCount() <= 0 ||
                this.stackHandler.getStackInSlot(1).getCount() <= 0 ||
                this.stackHandler.getStackInSlot(2).getCount() <= 0 ||
                this.stackHandler.getStackInSlot(3).getCount() <= 0;

    }

    @Override
    public ItemStack getItem(int slot) {
        return this.stackHandler.getStackInSlot(slot);
    }

    @Override
    public ItemStack removeItem(int p_70298_1_, int p_70298_2_) {
        // no clue
        return ItemStackHelper.removeItem(this.items, p_70298_1_, p_70298_2_);
    }


    @Override
    public ItemStack removeItemNoUpdate(int p_70304_1_) {
        // no clue
        return ItemStackHelper.takeItem(this.items, p_70304_1_);
    }

    @Override
    public void setItem(int p_70299_1_, ItemStack p_70299_2_) {
        //no clue
        ItemStack itemstack = this.getItem(p_70299_1_);
        boolean flag = !p_70299_2_.isEmpty() && p_70299_2_.sameItem(itemstack) && ItemStack.tagMatches(p_70299_2_, itemstack);
        this.items.set(p_70299_1_, p_70299_2_);
        if (p_70299_2_.getCount() > this.getMaxStackSize()) {
            p_70299_2_.setCount(this.getMaxStackSize());
        }

        if (p_70299_1_ == 0 && !flag) {
            this.workTime = 0;
            this.setChanged();
        }
    }

    @Override
    public boolean stillValid(PlayerEntity p_70300_1_) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return p_70300_1_.distanceToSqr((double) this.worldPosition.getX() + 0.5D, (double) this.worldPosition.getY() + 0.5D, (double) this.worldPosition.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt)
    {
        this.load(null, pkt.getTag());
    }
    @Override
    public void clearContent() {
        this.items.clear();
    }
    }
package com.yuanno.block_clover.blocks.containers;

import com.yuanno.block_clover.init.ModContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nullable;

public class DevilAltarContainer extends Container{
    public final TileEntity tileEntity;
    public final PlayerEntity player;
    private final IItemHandler hander;
    public DevilAltarContainer(int windowID, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player) {
        super(ModContainers.DEVIL_ALTAR_CONTAINER.get(), windowID);
        this.tileEntity = world.getBlockEntity(pos);
        this.player = player;
        this.hander = new InvWrapper(inventory);

        // Pass the IItemHandler (hander) to layoutPlayerInventorySlots
        layoutPlayerInventorySlots(hander, 1, 1);

        if (tileEntity != null) {
            tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {

            });
        }
    }

    public DevilAltarContainer(int windowId, PlayerInventory playerInventory) {
        super(ModContainers.DEVIL_ALTAR_CONTAINER.get(), windowId);
        // Initialize the container without requiring world and block position
        // Adjust the constructor call as needed
        this.player = playerInventory.player;
        this.hander = new InvWrapper(playerInventory);
        this.tileEntity = null; // Set to null since there's no specific tile entity
    }

    @Override
    public void slotsChanged(IInventory inv) {
        super.slotsChanged(inv);
    }

    private int addSingleItemSlot(IItemHandler handler, int index, int x, int y) {
        addSlot(new SlotItemHandler(handler, index, x, y));
        return index + 1;
    }

    private void addSingleItemSlotToCenter(IItemHandler handler, int index) {
        // Assuming the screen size is 176x166 (typical for a GUI)
        int centerX = 88;
        int centerY = 83;

        // Assuming the slot size is 18x18
        int slotSize = 18;

        // Calculate the position for the single item slot in the center
        int x = centerX - slotSize / 2;
        int y = centerY - slotSize / 2;

        addSingleItemSlot(handler, index, x, y);
    }


    private void layoutPlayerInventorySlots(IItemHandler handler, int leftCol, int topRow) {
        // Add the player inventory slots
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                int x = leftCol + col * 18;
                int y = topRow + row * 18;
                addSlot(new SlotItemHandler(handler, col + row * 9 + 9, x, y));
            }
        }

        // Update the topRow to provide space between player inventory and the single item slot
        topRow += 58;

        // Add a single item slot to the center of the screen
        addSingleItemSlotToCenter(handler, 0);

        // Add the remaining player inventory slots
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                int x = leftCol + col * 18;
                int y = topRow + row * 18;
                addSlot(new SlotItemHandler(handler, col + row * 9 + 9 + 1, x, y));  // Adjust index to start after the single item slot
            }
        }
    }






    @Override
    public boolean stillValid(PlayerEntity player) {
        return true;
    }


    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int index) {
        // 36 is the number of slots in the inventory of the player
        /*
        ItemStack itemstack1 = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            itemstack1 = slot.getItem();
            if (index < 36) {
                if (!this.moveItemStackTo(itemstack1, 36, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, 36, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack1;
         */
        return super.quickMoveStack(player, index);
    }
}

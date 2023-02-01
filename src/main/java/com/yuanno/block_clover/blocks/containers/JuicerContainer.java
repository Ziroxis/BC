package com.yuanno.block_clover.blocks.containers;

import com.yuanno.block_clover.blocks.tileentities.JuicerBlockTileEntity;
import com.yuanno.block_clover.init.ModContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;


public class JuicerContainer extends Container {
    private final TileEntity tileEntity;
    private final PlayerEntity player;
    private final IItemHandler hander;
    public static final int SLOTS = 4;


    public JuicerContainer(int windowID, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player ) {
        super(ModContainers.JUICER_CONTAINER.get(), windowID);
        this.tileEntity = world.getBlockEntity(pos);
        this.player = player;
        this.hander = new InvWrapper(inventory);
        layoutPlayerInventorySlots(8, 86);

        if(tileEntity != null) {
            tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                addSlot(new SlotItemHandler(h,0,80,10));
                addSlot(new SlotItemHandler(h,1,80,32));
                addSlot(new SlotItemHandler(h,2,150,54));
                addSlot(new SlotItemHandler(h,3,80,66));

            });
        }
    }


    @Override
    public void slotsChanged(IInventory inv) {
        super.slotsChanged(inv);
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }

        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }

        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        addSlotBox(hander, 9, leftCol, topRow, 9, 18, 3, 18);

        topRow += 58;
        addSlotRange(hander, 0, leftCol, topRow, 9, 18);
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return true;
    }



    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int index) {
        // 36 is the number of slots in the inventory of the player
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
    }
}

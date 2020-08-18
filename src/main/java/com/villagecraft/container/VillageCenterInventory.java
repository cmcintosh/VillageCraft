package com.villagecraft.container;

import com.villagecraft.VillageCraft;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;

public class VillageCenterInventory extends Inventory implements ISidedInventory {
	public ItemStack itemStack;
    private NonNullList<ItemStack> items;
    
    public VillageCenterInventory(int size, ItemStack itemStack){
        super(size);
        this.items = NonNullList.withSize(size, ItemStack.EMPTY);
        if (itemStack == null) {
        	itemStack = new ItemStack((Item)null);
        }
        this.itemStack = itemStack;
        
        if (!this.itemStack.hasTag()) {
        	this.itemStack.setTag(new CompoundNBT());
        }
        readFromNBT(this.itemStack.getTag());
    }
    
    private void readFromNBT(CompoundNBT tagCompound) {
        this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(tagCompound, this.items);
    }

    private void writeToNBT(CompoundNBT tagCompound) {
        ItemStackHelper.saveAllItems(tagCompound, this.items);
    }
    
    @Override
    public int getSizeInventory() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(items, index, count);

    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.items, index);
    }
    
    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
    	VillageCraft.LOGGER.debug("Called Inventory setInventorySlotContents");
        ItemStack itemstack = this.items.get(index);
        this.items.set(index, stack);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        if (stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }
        this.markDirty();
    }
    
    @Override
    public void markDirty() {
    	VillageCraft.LOGGER.debug("Called Inventory Marked dirty");
    	writeToNBT(itemStack.getTag());
    }
    
    @Override
    public void openInventory(PlayerEntity player) {
    }

    @Override
    public void closeInventory(PlayerEntity player) {
        if (!itemStack.hasTag()) {
        	itemStack.setTag(new CompoundNBT());
        }
        writeToNBT(itemStack.getTag());
    }
    
    @Override
    public void clear() {
        this.items.clear();
    }

    public ITextComponent getDisplayName() {
        return itemStack.getTextComponent();
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, Direction direction) {
        return isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
        return true;
    }

}

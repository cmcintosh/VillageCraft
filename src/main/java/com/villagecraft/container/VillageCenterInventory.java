package com.villagecraft.container;

import com.villagecraft.VillageCraft;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.SimpleContainer;
// TODO: ContainerHelper - use ContainerHelper in 1.20;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;

public class VillageCenterInventory extends SimpleContainer implements WorldlyContainer {
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
        	this.itemStack.setTag(new CompoundTag());
        }
        readFromNBT(this.itemStack.getTag());
    }
    
    public VillageCenterInventory(ItemStack stack) {
		this.itemStack = stack;
		this.items = NonNullList.withSize(9, stack);
	}

	private void readFromNBT(CompoundTag tagCompound) {
        this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(tagCompound, this.items);
    }

    private void writeToNBT(CompoundTag tagCompound) {
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
    public void openInventory(Player player) {
    }

    @Override
    public void closeInventory(Player player) {
        if (!itemStack.hasTag()) {
        	itemStack.setTag(new CompoundTag());
        }
        writeToNBT(itemStack.getTag());
    }
    
    @Override
    public void clear() {
        this.items.clear();
    }

    public Component getDisplayName() {
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

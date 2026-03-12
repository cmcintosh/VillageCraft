package com.villagecraft.container;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.NonNullList;

// TODO: Reimplement container for NeoForge 1.20.2
// WorldlyContainer API changed significantly
public class VillageCenterInventory {
	
	private NonNullList<ItemStack> items;
	private ItemStack itemStack;
	
	public VillageCenterInventory(int size, ItemStack itemStack){
		this.items = NonNullList.withSize(size, ItemStack.EMPTY);
		this.itemStack = itemStack != null ? itemStack : ItemStack.EMPTY;
	}
	
	public VillageCenterInventory(ItemStack stack) {
		this(9, stack);
	}
	
	// TODO: Reimplement inventory methods for 1.20.2
	// - getContainerSize() instead of getSizeInventory()
	// - setItem() instead of setInventorySlotContents()
	// - removeItem() instead of removeStackFromSlot()
	// - canTakeItemThroughFace() - new required abstract method
	
	public int getContainerSize() {
		return this.items.size();
	}
	
	public boolean isEmpty() {
		return this.items.isEmpty();
	}
	
	public void setItem(int index, ItemStack stack) {
		this.items.set(index, stack);
	}
	
	public ItemStack removeItem(int index, int count) {
		// TODO: Implement properly
		return ItemStack.EMPTY;
	}
	
	public ItemStack removeItemNoUpdate(int index) {
		// TODO: Implement properly
		return ItemStack.EMPTY;
	}
	
	public void setChanged() {
		// TODO: Save to NBT
	}
	
	public boolean stillValid(Player player) {
		return true;
	}
	
	public void clearContent() {
		this.items.clear();
	}
}
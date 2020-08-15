package com.villagecraft.container;

import java.util.Objects;

import com.villagecraft.init.ModContainer;
import com.villagecraft.tile.TileEntityVillageCenter;
import com.villagecraft.util.Reference;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.BannerItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class VillageCenterContainer extends Container {
	
	protected TileEntityVillageCenter tileEntity;
	protected IInventory inventory;
	
	public VillageCenterContainer(final int windowId, final PlayerInventory playerInventory) {
		super(ModContainer.VILLAGE_CENTER_CONTAINER.get(), windowId);
		this.inventory = new Inventory();
		
	}
	
	public VillageCenterContainer(final int windowId, final PlayerInventory playerInventory,
			final TileEntityVillageCenter tileEntity) { 
		super(ModContainer.VILLAGE_CENTER_CONTAINER.get(), windowId);
		this.tileEntity = tileEntity;
		if (this.inventory == null) {
			this.inventory = new Inventory();
		}
		
		// Main inventory
		int startX = 8;
		int startY = 18;
		int slotSizePlus2 = 18;
		for (int row = 0; row < 4; ++row) { 
			for (int column = 0; column < 9; ++column) {
				
				this.addSlot(new Slot(this.inventory, (row * 9) + column, 
						startX + (column * slotSizePlus2), 
						startY + (row * slotSizePlus2)) {
		            public void onSlotChanged() {
		                this.inventory.markDirty();
		            }

		            @Override
		            public boolean isItemValid(ItemStack stack) {
		                return super.isItemValid(stack);
		            }
		        });
			}
		}
		
		// Main Player Inventory
		int startPlayerInvY = startY * 5 + 12;
		for (int row = 0; row < 3; ++row) {
			for (int column = 0; column < 9; ++column) {
				this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, startX + (column * slotSizePlus2),
						startPlayerInvY + (row * slotSizePlus2)));
			}
		}

//		// Hotbar
//		int hotbarY = startPlayerInvY + (startPlayerInvY / 2) + 7;
//		for (int column = 0; column < 9; ++column) {
//			this.addSlot(new Slot(playerInventory, column, startX + (column * slotSizePlus2), hotbarY));
//		}
	}
	
	public static TileEntityVillageCenter getTileEntity(final PlayerInventory playerInventory,
			final PacketBuffer data) {
		Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
		Objects.requireNonNull(data, "data cannot be null");
		final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
		if (tileAtPos instanceof TileEntityVillageCenter) {
			return (TileEntityVillageCenter) tileAtPos;
		}
		throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
	}
	
	public VillageCenterContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
		this(windowId, playerInventory, getTileEntity(playerInventory, data));
	}


	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		if (this.inventory != null) { 
			return true;
		}
		return this.inventory.isUsableByPlayer(playerIn);
	}
	
	@Override
	public void onContainerClosed(PlayerEntity playerIn) {
	    super.onContainerClosed(playerIn);
	    this.inventory.closeInventory(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index < 36) {
				if (!this.mergeItemStack(itemstack1, 36, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, 36, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}
		return itemstack;
	}
	
}

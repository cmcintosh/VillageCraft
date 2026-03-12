package com.villagecraft.container;

import com.villagecraft.VillageCraft;
import com.villagecraft.init.ModContainer;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.SimpleContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

/**
 * Village Center Container - Main management interface for villages.
 * Shows village info, villager list, and storage access.
 */
public class VillageCenterContainer extends AbstractContainerMenu {
	
	private final BlockEntity blockEntity;
	private final BlockPos pos;
	
	// Container IDs
	public static final int PLAYER_INVENTORY_START = 0;
	public static final int PLAYER_INVENTORY_END = 27;
	public static final int HOTBAR_START = 27;
	public static final int HOTBAR_END = 36;
	public static final int STORAGE_START = 36;
	public static final int STORAGE_END = 45; // 9 storage slots
	
	/**
	 * Constructor from BlockEntity (server-side)
	 */
	public VillageCenterContainer(int id, Inventory playerInventory, BlockEntity blockEntity) {
		super(ModContainer.VILLAGE_CENTER_CONTAINER.get(), id);
		this.blockEntity = blockEntity;
		this.pos = blockEntity != null ? blockEntity.getBlockPos() : BlockPos.ZERO;
		
		// Add player inventory slots
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 9; col++) {
				this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
			}
		}
		
		// Add hotbar slots
		for (int col = 0; col < 9; col++) {
			this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
		}
		
		// Add village storage slots
		for (int col = 0; col < 9; col++) {
			this.addSlot(new Slot(new SimpleContainer(9), col, 8 + col * 18, 35) {
				@Override
				public boolean mayPlace(ItemStack stack) {
					return true;
				}
			});
		}
	}
	
	/**
	 * Client-side constructor called by MenuType factory
	 */
	public VillageCenterContainer(int id, Inventory playerInventory, FriendlyByteBuf extraData) {
		this(id, playerInventory, extraData.readBlockPos());
	}
	
	/**
	 * Constructor with BlockPos (client-side reconstruction)
	 */
	public VillageCenterContainer(int id, Inventory playerInventory, BlockPos pos) {
		super(ModContainer.VILLAGE_CENTER_CONTAINER.get(), id);
		this.pos = pos;
		this.blockEntity = playerInventory.player.level().getBlockEntity(pos);
		
		// Add player inventory slots
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 9; col++) {
				this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
			}
		}
		
		// Add hotbar slots
		for (int col = 0; col < 9; col++) {
			this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
		}
		
		// Add village storage slots
		for (int col = 0; col < 9; col++) {
			this.addSlot(new Slot(new SimpleContainer(9), col, 8 + col * 18, 35) {
				@Override
				public boolean mayPlace(ItemStack stack) {
					return true;
				}
			});
		}
		
		VillageCraft.LOGGER.debug("VillageCenterContainer initialized at {}", pos);
	}
	
	@Override
	public boolean stillValid(Player player) {
		// Check if player is still near the block
		if (this.blockEntity != null && !this.blockEntity.isRemoved()) {
			return player.distanceToSqr(
				this.blockEntity.getBlockPos().getX() + 0.5,
				this.blockEntity.getBlockPos().getY() + 0.5,
				this.blockEntity.getBlockPos().getZ() + 0.5
			) <= 64.0; // 8 blocks squared
		}
		return false;
	}
	
	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		
		if (slot != null && slot.hasItem()) {
			ItemStack slotStack = slot.getItem();
			itemstack = slotStack.copy();
			
			if (index >= STORAGE_START) {
				// Move from storage to player inventory
				if (!this.moveItemStackTo(slotStack, PLAYER_INVENTORY_START, HOTBAR_END, true)) {
					return ItemStack.EMPTY;
				}
			} else if (index >= HOTBAR_START) {
				// Move from hotbar to storage
				if (!this.moveItemStackTo(slotStack, STORAGE_START, STORAGE_END, false)) {
					// If storage full, move to player inventory
					if (!this.moveItemStackTo(slotStack, PLAYER_INVENTORY_START, PLAYER_INVENTORY_END, false)) {
						return ItemStack.EMPTY;
					}
				}
			} else {
				// Move from player inventory to storage
				if (!this.moveItemStackTo(slotStack, STORAGE_START, STORAGE_END, false)) {
					// If storage full, move to hotbar
					if (!this.moveItemStackTo(slotStack, HOTBAR_START, HOTBAR_END, false)) {
						return ItemStack.EMPTY;
					}
				}
			}
			
			if (slotStack.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
			
			if (slotStack.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}
			
			slot.onTake(player, slotStack);
		}
		
		return itemstack;
	}
	
	public BlockPos getPos() {
		return this.pos;
	}
	
	public BlockEntity getBlockEntity() {
		return this.blockEntity;
	}
	
	/**
	 * Get village name for display
	 * TODO: Link to actual village data
	 */
	public String getVillageName() {
		return "Village Center";
	}
	
	/**
	 * Get villager count for display
	 * TODO: Link to actual village data
	 */
	public int getVillagerCount() {
		return 0; // Placeholder
	}
}
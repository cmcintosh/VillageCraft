package com.villagecraft.alchemy.container;

import java.util.Objects;

import com.villagecraft.alchemy.entity.tile.AlchemistTableTile;
import com.villagecraft.init.ModContainer;
import com.villagecraft.tile.TileBasicVillageBlock;

import net.minecraft.block.FurnaceBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.IContainerListener;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;

public class AlchemistTableContainer extends Container {
	
	protected AlchemistTableTile tile;
	protected PlayerInventory playerInventory;
	public boolean showContainerInventory = true;
	protected int startX = 116;
	protected int startY = 19;
	protected int slotSizePlus2 = 18;
	
	// Crafting....
	protected boolean currentlyCrafting = false;
	protected int currentBurnTime = 0;
	protected int currentCookTime = 0;

	public AlchemistTableContainer(int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
		this(windowId, playerInventory, getTileEntity(playerInventory, data));
	}
	
	public AlchemistTableContainer(int id, PlayerInventory inv, TileEntity tile) {
		super(ModContainer.ALCHEMIST_TABLE_CONTAINER.get(), id);
		this.tile = (AlchemistTableTile) tile;
		this.playerInventory = inv;
		mainInventoryDisplay();
		playerInventoryDisplay();
	}
	
	// Builds the main inventory for the container...
	protected void mainInventoryDisplay() { 
		// Crafting slots
		
		// Input slot 1
		this.addSlot(new Slot(this.tile, 0,	37, 13) {
            public void onSlotChanged() {
                this.inventory.markDirty();
            }
            @Override
            public boolean isItemValid(ItemStack stack) {
                return super.isItemValid(stack);
            }
        });
		
		// Fuel Slot
		this.addSlot(new Slot(this.tile, 1, 37, 33) {
            public void onSlotChanged() {
                this.inventory.markDirty();
            }
            @Override
            public boolean isItemValid(ItemStack stack) {
                return super.isItemValid(stack);
            }
        });
		
		// Input Slot
		this.addSlot(new Slot(this.tile, 2, 37, 54) {
            public void onSlotChanged() {
                this.inventory.markDirty();
            }
            @Override
            public boolean isItemValid(ItemStack stack) {
                return super.isItemValid(stack);
            }
        });
		
		// Input slots
		this.addSlot(new Slot(this.tile, 3,	61, 13) {
            public void onSlotChanged() {
                this.inventory.markDirty();
            }
            @Override
            public boolean isItemValid(ItemStack stack) {
                return super.isItemValid(stack);
            }
        });
				
		// Input Slot
		this.addSlot(new Slot(this.tile, 4, 61, 54) {
            public void onSlotChanged() {
                this.inventory.markDirty();
            }
            @Override
            public boolean isItemValid(ItemStack stack) {
                return super.isItemValid(stack);
            }
        });
		
		
		// Output slot
		this.addSlot(new Slot(this.tile, 5, 88, 36) {
            public void onSlotChanged() {
                this.inventory.markDirty();
            }
            @Override
            public boolean isItemValid(ItemStack stack) {
                return super.isItemValid(stack);
            }
        });
		
		
		
		for (int row = 0; row < 3; ++row) { 
			for (int column = 0; column < 3; ++column) {
				
				this.addSlot(new Slot(this.tile, ( (row * 3) + column) + 5, 
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
	}
	
	protected void playerInventoryDisplay() { 
		final int playerInventoryStartX = 8;
		final int playerInventoryStartY = 84;
		final int slotSizePlus2 = 18; // slots are 16x16, plus 2 (for spacing/borders) is 18x18

		// Player Top Inventory slots
		for (int row = 0; row < 3; ++row) {
			for (int column = 0; column < 9; ++column) {
				this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, playerInventoryStartX + (column * slotSizePlus2), playerInventoryStartY + (row * slotSizePlus2)));
			}
		}

		final int playerHotbarY = playerInventoryStartY + slotSizePlus2 * 3 + 4;
		// Player Hotbar slots
		for (int column = 0; column < 9; ++column) {
			this.addSlot(new Slot(playerInventory, column, playerInventoryStartX + (column * slotSizePlus2), playerHotbarY));
		}
	}

	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		return itemstack;
	}
	
	protected static AlchemistTableTile getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
		Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
		Objects.requireNonNull(data, "data cannot be null");
		final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
		if (tileAtPos instanceof AlchemistTableTile) {
			return (AlchemistTableTile) tileAtPos;
		}
		throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		// TODO Auto-generated method stub
		return true;
	}
	
}

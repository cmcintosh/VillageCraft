package com.villagecraft.container;

import java.util.Objects;

import com.villagecraft.tile.TileBasicVillageBlock;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;

public class BasicVillageCraftContainer extends Container {
	
	public TileBasicVillageBlock tile;
	
	protected BasicVillageCraftContainer(ContainerType<?> type, int id, final TileEntity tile) {
		super(type, id);
		this.tile = (TileBasicVillageBlock) tile;
	}
	
	protected BasicVillageCraftContainer(ContainerType<?> type, int id, final TileEntity tile, PlayerInventory inv) {
		super(type, id);
		this.tile = (TileBasicVillageBlock) tile;
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int k = 0; k < 9; ++k) {
			this.addSlot(new Slot(inv, k, 8 + k * 18, 142));
		}
	}

	

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return true;
	}
	
	public TileBasicVillageBlock getTile(){
		return tile;
	}
	
	protected static TileBasicVillageBlock getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
		Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
		Objects.requireNonNull(data, "data cannot be null");
		final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
		if (tileAtPos instanceof TileBasicVillageBlock) {
			return (TileBasicVillageBlock) tileAtPos;
		}
		throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
	}
	
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		return ItemStack.EMPTY;
	}

}

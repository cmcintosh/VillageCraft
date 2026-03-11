package com.villagecraft.container;

import java.util.Objects;

import com.villagecraft.tile.TileBasicVillageBlock;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;

public class BasicVillageCraftContainer extends AbstractContainerMenu {
	
	public TileBasicVillageBlock tile;
	
	protected BasicVillageCraftContainer(MenuType<?> type, int id, final BlockEntity tile) {
		super(type, id);
		this.tile = (TileBasicVillageBlock) tile;
	}
	
	protected BasicVillageCraftContainer(MenuType<?> type, int id, final BlockEntity tile, Inventory inv) {
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
	public boolean stillValid(Player playerIn) {
		return true;
	}
	
	public TileBasicVillageBlock getTile(){
		return tile;
	}
	
	protected static TileBasicVillageBlock getBlockEntity(final Inventory playerInventory, final FriendlyByteBuf data) {
		Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
		Objects.requireNonNull(data, "data cannot be null");
		final BlockEntity tileAtPos = playerInventory.player.level().getBlockEntity(data.readBlockPos());
		if (tileAtPos instanceof TileBasicVillageBlock) {
			return (TileBasicVillageBlock) tileAtPos;
		}
		throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
	}
	
	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		return ItemStack.EMPTY;
	}

}

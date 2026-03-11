package com.villagecraft.container;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Nonnull;

import com.villagecraft.VillageCraft;
import com.villagecraft.init.ModBlocks;
import com.villagecraft.init.ModContainer;
import com.villagecraft.tile.TileBasicVillageBlock;
import com.villagecraft.tile.TileEntityVillageCenter;
import com.villagecraft.util.Reference;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.neoforged.neoforge.items.wrapper.InvWrapper;

public class VillageCenterContainer extends BasicVillageCraftContainer {
	
	public VillageCenterContainer(int windowId, Inventory playerInventory, FriendlyByteBuf data) {
		this(windowId, playerInventory, getBlockEntity(playerInventory, data));
	}
	
	protected void mainInventory() { 
		int startX = 116;
		int startY = 18;
		int slotSizePlus2 = 18;
		for (int row = 0; row < 3; ++row) { 
			for (int column = 0; column < 3; ++column) {
				
				this.addSlot(new Slot(this.tile, ( (row * 3) + column), 
						startX + (column * slotSizePlus2), 
						startY + (row * slotSizePlus2)) {
	            public void setChanged() {
	                this.container.setChanged();
	            }

	            @Override
	            public boolean mayPlace(ItemStack stack) {
	                return super.mayPlace(stack);
	            }
	        });
			}
		}
	}
	

	public VillageCenterContainer(int id, Inventory inv, final BlockEntity tile) {
		super(ModContainer.VILLAGE_CENTER_CONTAINER.get(), id, tile, inv);
		mainInventory();
	}
	
}

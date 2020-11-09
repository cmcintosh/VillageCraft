package com.villagecraft.container;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.villagecraft.VillageCraft;
import com.villagecraft.init.ModBlocks;
import com.villagecraft.init.ModContainer;
import com.villagecraft.tile.TileBasicVillageBlock;
import com.villagecraft.tile.TileEntityVillageCenter;
import com.villagecraft.util.Reference;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.EnchantmentContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.BannerItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraft.client.gui.screen.inventory.ChestScreen;

public class VillageCenterContainer extends BasicVillageCraftContainer {
	
	public VillageCenterContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
		this(windowId, playerInventory, getTileEntity(playerInventory, data));
		
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
	

	public VillageCenterContainer(int id, PlayerInventory inv, final TileEntity tile) {
		super(ModContainer.VILLAGE_CENTER_CONTAINER.get(), id, tile, inv);
		mainInventory();
	}
	
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
			if (!this.mergeItemStack(slot.getStack(), 0, 36, false)) {
				return itemstack;
			}
		return itemstack;
	}
	
}

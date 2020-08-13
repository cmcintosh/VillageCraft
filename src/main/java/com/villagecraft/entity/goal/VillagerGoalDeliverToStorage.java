package com.villagecraft.entity.goal;

import java.util.ArrayList;

import com.villagecraft.VillageCraft;
import com.villagecraft.init.ModVillagerProfessions;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class VillagerGoalDeliverToStorage extends VillagerGoalGoToBlock {
	
	protected ArrayList<Item> filterItems;

	public VillagerGoalDeliverToStorage(Entity entity) {
		super((VillagerEntity) entity, net.minecraft.block.Blocks.CHEST, false, 1);
		this.poiType = ModVillagerProfessions.CHEST.get();
		this.cooldownTicks = 20;
	}
	
	public boolean shouldExecute() {
		if (!super.shouldExecute()) {
			return false;
		}
		
		if (!this.villager.getVillagerInventory().isEmpty()) { 
			
			return true;
		}
		return false;
	}
	
	public void tick() { 
		this.stayInRange = !(this.villager.getVillagerInventory().isEmpty());
		super.tick();
		if (this.targetBlockPos != null) {
			double distanceFromBlock = this.targetBlockPos.distanceSq(this.villager.getPosX(), this.villager.getPosY(), this.villager.getPosZ(), true);
			
			if (distanceFromBlock < 10) {
				openChest();
				depositItems();
				closeChest();
			}	
		}
	}
	
	protected void depositItems() {
		boolean depositedItems = false;
		int totalDepositeditems = 0;
		ChestTileEntity chest = this.getChest();
		
		Inventory inventory = villager.getVillagerInventory();
		
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack itemStack = villager.getVillagerInventory().getStackInSlot(i);
			
			if (this.filterItems != null) {
				if (!this.filterItems.contains(itemStack.getItem())) {
					continue;
				}
			}
			
			int stackCount = itemStack.getCount();
			int originalStackCount = stackCount;
			for (int j = 0; j < chest.getSizeInventory(); j++) {
				ItemStack chestStack = chest.getStackInSlot(j);
				
				if (chestStack.getItem() == itemStack.getItem() && chestStack.getCount() < 64) {
					int chestCount = chestStack.getCount();
					@SuppressWarnings("deprecation")
					int remainingCount = 64- chestCount;
					int amountToAdd = (stackCount > remainingCount) ? stackCount - remainingCount : stackCount;
					VillageCraft.LOGGER.debug("Remaining count: " + remainingCount + ":: Amount to add: " + amountToAdd);
					chestStack.setCount(amountToAdd + chestCount);
					chest.setInventorySlotContents(j, chestStack);
					itemStack.setCount(stackCount - amountToAdd); 
					inventory.setInventorySlotContents(i, itemStack);
					totalDepositeditems += amountToAdd;
					depositedItems = true;
					if ( stackCount == 0) {
						VillageCraft.LOGGER.debug("Removing inventory from slot " + i);
						inventory.removeStackFromSlot(i);
						ResourceLocation location = new ResourceLocation("vcm", "villager_happy");
						SoundEvent event = new SoundEvent(location);
						villager.playSound(event, 100, 1);
						depositedItems = true;
						break;
					}
				}
			}
			
			if (stackCount == originalStackCount) { 
				VillageCraft.LOGGER.debug("Empty chests");
				for (int j = 0; j < chest.getSizeInventory(); j++) {
					if (chest.getStackInSlot(j).getCount() == 0) {
						chest.setInventorySlotContents(j, itemStack);
						inventory.removeStackFromSlot(i);
						
						ResourceLocation location = new ResourceLocation("vcm", "villager_happy");
						SoundEvent event = new SoundEvent(location);
						villager.playSound(event, 100, 1);
						depositedItems = true;
						totalDepositeditems += stackCount;
						break;
					}
				}
				
			}
		}
		if (depositedItems) { 
			this.villager.setXp(totalDepositeditems + this.villager.getXp());
		}
		
	}
	
	private ChestTileEntity getChest() { 
		return (ChestTileEntity) this.villager.world.getTileEntity(this.targetBlockPos);
	}
	
	private void openChest() { 
		ChestTileEntity chest = this.getChest();
		this.villager.world.addBlockEvent(this.targetBlockPos, chest.getBlockState().getBlock(), 1, 1);
		this.villager.world.notifyNeighborsOfStateChange(this.targetBlockPos, chest.getBlockState().getBlock());
	}
	
	private void closeChest() { 
		ChestTileEntity chest = this.getChest();
		this.villager.world.addBlockEvent(this.targetBlockPos, chest.getBlockState().getBlock(), 1, 1);
		this.villager.world.notifyNeighborsOfStateChange(this.targetBlockPos, chest.getBlockState().getBlock());
	}
}




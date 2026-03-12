package com.villagecraft.entity.goal;

import java.util.ArrayList;

import com.villagecraft.VillageCraft;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class VillagerGoalDeliverToStorage extends VillagerGoalGoToBlock {
	
	protected ArrayList<Item> filterItems;

	public VillagerGoalDeliverToStorage(Villager entity) {
		super(entity, Blocks.CHEST, false, 1);
		// TODO: POI system changed - reimplement poiType
	}
	
	@Override
	public boolean canUse() {
		// TODO: Reimplement for 1.20.2
		return false;
	}
	
	@Override
	public void tick() { 
		// TODO: Reimplement chest interaction for 1.20.2
		// - getInventory() instead of getVillagerInventory()
		// - getContainerSize() instead of getSizeInventory()
		// - removeItem() instead of removeStackFromSlot()
		// - setItem() instead of setInventorySlotContents()
		VillageCraft.LOGGER.debug("VillagerGoalDeliverToStorage.tick() - needs 1.20.2 update");
	}
}
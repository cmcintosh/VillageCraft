package com.villagecraft.entity.goal;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.villagecraft.VillageCraft;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.Level;

/**
 * Goal for villagers to deliver items to storage chests.
 * Used by workers and farmers to deposit gathered resources.
 * Updated for NeoForge 1.20.2
 */
public class VillagerGoalDeliverToStorage extends VillagerGoalGoToBlock {
	
	protected ArrayList<Item> filterItems;
	private int deliveryCooldown = 0;
	private static final int DELIVERY_COOLDOWN_TICKS = 100; // 5 seconds between deliveries
	private static final int MIN_ITEMS_TO_DELIVER = 8; // Need at least 8 items to consider delivery
	
	private boolean hasDelivered = false;

	public VillagerGoalDeliverToStorage(Villager entity) {
		super(entity, Blocks.CHEST, false, 2.0, 0.5);
		this.filterItems = new ArrayList<>();
		this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}
	
	/**
	 * Constructor with item filter - only deliver specific items
	 */
	public VillagerGoalDeliverToStorage(Villager entity, List<Item> allowedItems) {
		this(entity);
		this.filterItems.addAll(allowedItems);
	}
	
	@Override
	public boolean canUse() {
		// Check cooldown
		if (deliveryCooldown > 0) {
			deliveryCooldown--;
			return false;
		}
		
		// Check if villager has enough items to deliver
		if (!hasItemsToDeliver()) {
			return false;
		}
		
		// Find nearest chest
		if (targetBlockPos == null) {
			targetBlockPos = findNearestChest();
		}
		
		return targetBlockPos != null;
	}
	
	@Override
	public boolean canContinueToUse() {
		// Continue if we haven't delivered yet
		if (hasDelivered) {
			return false;
		}
		
		// Check if chest still exists
		if (targetBlockPos == null || !villager.level().getBlockState(targetBlockPos).is(Blocks.CHEST)) {
			return false;
		}
		
		return super.canContinueToUse();
	}
	
	@Override
	public void start() {
		super.start();
		hasDelivered = false;
		VillageCraft.LOGGER.debug("Villager {} is going to deliver items to storage", villager.getUUID());
	}
	
	@Override
	protected void onReachBlock() {
		super.onReachBlock();
		
		// Deliver items to the chest
		deliverItems();
	}
	
	@Override
	public void tick() {
		super.tick();
		
		// Check if we're close enough to interact
		if (!hasDelivered && reachedBlock) {
			double distSq = villager.distanceToSqr(
				targetBlockPos.getX() + 0.5,
				targetBlockPos.getY() + 0.5,
				targetBlockPos.getZ() + 0.5
			);
			
			if (distSq <= 4.0) { // Within 2 blocks
				deliverItems();
			}
		}
	}
	
	@Override
	public void stop() {
		super.stop();
		hasDelivered = false;
		deliveryCooldown = DELIVERY_COOLDOWN_TICKS;
		VillageCraft.LOGGER.debug("Villager {} finished delivery attempt", villager.getUUID());
	}
	
	/**
	 * Check if villager has items to deliver
	 */
	private boolean hasItemsToDeliver() {
		// 1.20.2: Use getInventory() and getContainerSize()
		if (villager.getInventory() == null) {
			return false;
		}
		
		int itemCount = 0;
		for (int i = 0; i < villager.getInventory().getContainerSize(); i++) {
			ItemStack stack = villager.getInventory().getItem(i);
			if (!stack.isEmpty()) {
				// If filter is empty, any item counts
				// If filter has items, only count those
				if (filterItems.isEmpty() || filterItems.contains(stack.getItem())) {
					itemCount += stack.getCount();
				}
			}
		}
		
		return itemCount >= MIN_ITEMS_TO_DELIVER;
	}
	
	/**
	 * Find nearest chest within search radius
	 */
	private BlockPos findNearestChest() {
		BlockPos villagerPos = villager.blockPosition();
		Level level = villager.level();
		
		BlockPos nearest = null;
		double nearestDist = Double.MAX_VALUE;
		int searchRadius = 50;
		
		for (int y = -5; y <= 5; y++) {
			for (int x = -searchRadius; x <= searchRadius; x++) {
				for (int z = -searchRadius; z <= searchRadius; z++) {
					BlockPos pos = villagerPos.offset(x, y, z);
					
					if (level.getBlockState(pos).is(Blocks.CHEST)) {
						double dist = pos.distSqr(villagerPos);
						if (dist < nearestDist) {
							nearestDist = dist;
							nearest = pos;
						}
					}
				}
			}
		}
		
		return nearest;
	}
	
	/**
	 * Deliver items from villager inventory to chest
	 */
	private void deliverItems() {
		if (hasDelivered || targetBlockPos == null) {
			return;
		}
		
		Level level = villager.level();
		if (level.isClientSide()) {
			return;
		}
		
		// Get the chest block entity
		if (!(level.getBlockEntity(targetBlockPos) instanceof ChestBlockEntity chest)) {
			return;
		}
		
		int itemsDelivered = 0;
		
		// Transfer items from villager to chest
		for (int i = 0; i < villager.getInventory().getContainerSize(); i++) {
			ItemStack villagerStack = villager.getInventory().getItem(i);
			
			if (villagerStack.isEmpty()) {
				continue;
			}
			
			// Check if item matches filter (if filter is set)
			if (!filterItems.isEmpty() && !filterItems.contains(villagerStack.getItem())) {
				continue;
			}
			
			// Try to add to chest
			ItemStack remaining = addToChest(chest, villagerStack);
			
			// Update villager inventory
			if (remaining.isEmpty()) {
				// All items transferred
				villager.getInventory().setItem(i, ItemStack.EMPTY);
				itemsDelivered += villagerStack.getCount();
			} else {
				// Partial transfer
				int transferred = villagerStack.getCount() - remaining.getCount();
				villager.getInventory().setItem(i, remaining);
				itemsDelivered += transferred;
			}
		}
		
		if (itemsDelivered > 0) {
			hasDelivered = true;
			VillageCraft.LOGGER.debug("Villager {} delivered {} items to chest at {}", 
				villager.getUUID(), itemsDelivered, targetBlockPos);
			
			// Play success sound/particles
			for (int i = 0; i < 3; i++) {
				double x = villager.getX() + (villager.getRandom().nextDouble() - 0.5) * 0.5;
				double y = villager.getY() + villager.getEyeHeight();
				double z = villager.getZ() + (villager.getRandom().nextDouble() - 0.5) * 0.5;
				level.addParticle(
					net.minecraft.core.particles.ParticleTypes.HAPPY_VILLAGER,
					x, y, z, 0, 0.1, 0
				);
			}
		}
	}
	
	/**
	 * Add items to chest, return any that couldn't fit
	 */
	private ItemStack addToChest(ChestBlockEntity chest, ItemStack stack) {
		// 1.20.2: Chest inventory access
		// Get the chest's inventory handler
		for (int i = 0; i < chest.getContainerSize(); i++) {
			ItemStack chestStack = chest.getItem(i);
			
			if (chestStack.isEmpty()) {
				// Empty slot - place items here
				chest.setItem(i, stack.copy());
				return ItemStack.EMPTY;
			}
			
			if (ItemStack.isSameItem(chestStack, stack) && 
			    chestStack.getCount() < chestStack.getMaxStackSize()) {
				// Same item - merge stacks
				int space = chestStack.getMaxStackSize() - chestStack.getCount();
				int toAdd = Math.min(space, stack.getCount());
				
				chestStack.grow(toAdd);
				stack.shrink(toAdd);
				
				if (stack.isEmpty()) {
					return ItemStack.EMPTY;
				}
			}
		}
		
		// Return any items that couldn't fit
		return stack;
	}
	
	/**
	 * Add an item to the delivery filter
	 */
	public void addFilterItem(Item item) {
		this.filterItems.add(item);
	}
	
	/**
	 * Clear the delivery filter
	 */
	public void clearFilter() {
		this.filterItems.clear();
	}
}

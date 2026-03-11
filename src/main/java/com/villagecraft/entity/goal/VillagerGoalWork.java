package com.villagecraft.entity.goal;

import com.villagecraft.VillageCraft;
import com.villagecraft.capabilities.CapabilityVillagerAttribute;
import com.villagecraft.capabilities.IVillagerHunger;
import com.villagecraft.init.ModVillagerProfessions;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.village.PointOfInterestType;

/**
 * VillagerGoalWork implements profession-based work behavior for villagers.
 * 
 * Each profession has specific work behaviors:
 * - FARMER: Harvests crops, replants seeds
 * - FISHERMAN: Catches fish (simulated at job site)
 * - SHEPHERD: Shears sheep
 * - FLETCHER: Works at fletching table
 * - CLERIC: Works at brewing stand
 * - WEAPONS_SMITH: Works at grindstone
 * - TOOL_SMITH: Works at smithing table
 * - ARMORER: Works at blast furnace
 * - BUTCHER: Works at smoker
 * - LEATHERWORKER: Works at cauldron
 * - MASON: Works at stonecutter
 * - LIBRARIAN: Works at lectern
 * - Others: Generic work at job site
 * 
 * Work schedule: 
 * - Active only during day (6000-18000 ticks)
 * - Returns to job site to work
 * - Takes breaks when hungry
 * 
 * @author VillageCraft Team
 * @since 1.0
 * @see VillagerGoalBase
 */
public class VillagerGoalWork extends VillagerGoalBase {
	
	// Work cooldown to prevent instant work loops
	protected int workCooldown = 0;
	protected static final int WORK_COOLDOWN_MAX = 100; // 5 seconds
	protected static final int WORK_DURATION = 40; // 2 seconds per work action
	
	// Work range
	protected static final double JOB_SITE_RANGE = 3.0D;
	protected static final double WORK_SCAN_RANGE = 50.0D;
	
	// Profession work state
	protected BlockPos currentWorkTarget;
	protected int workProgress = 0;
	protected boolean isWorkingAtSite = false;
	
	// Crop farm specific
	protected BlockPos targetCrop;
	protected boolean hasSeeds = false;
	
	/**
	 * Creates a new work goal for the villager
	 * @param entity The villager entity
	 */
	public VillagerGoalWork(VillagerEntity entity) {
		super(entity);
	}
	
	/**
	 * Checks if the villager can work right now
	 * Conditions:
	 * - Must have a profession (not NONE)
	 * - Must be day time
	 * - Not critically hungry
	 * - Has job site
	 */
	@Override
	public boolean shouldExecute() {
		// Check if we have a valid profession
		VillagerProfession profession = this.villager.getVillagerData().getProfession();
		if (profession == VillagerProfession.NONE || profession == VillagerProfession.NITWIT) {
			return false;
		}
		
		// Only work during day
		if (!isDayTime()) {
			return false;
		}
		
		// Don't work if starving
		if (isCriticallyHungry()) {
			return false;
		}
		
		// Check if villager has a job site
		return this.villager.getBrain().getMemory(MemoryModuleType.JOB_SITE).isPresent();
	}
	
	/**
	 * Continue executing if we should still be working
	 */
	@Override
	public boolean shouldContinueExecuting() {
		return shouldExecute();
	}
	
	/**
	 * Called when the goal starts executing
	 */
	@Override
	public void startExecuting() {
		super.startExecuting();
		this.transitionToState(VillagerState.WORKING);
		this.workCooldown = WORK_COOLDOWN_MAX;
		
		VillageCraft.LOGGER.debug("Villager " + this.villager.getUniqueID() + 
			" started working as " + this.villager.getVillagerData().getProfession().getName());
	}
	
	/**
	 * Called when the goal stops executing
	 */
	@Override
	public void resetTask() {
		super.resetTask();
		this.currentWorkTarget = null;
		this.workProgress = 0;
		this.isWorkingAtSite = false;
		this.targetCrop = null;
	}
	
	/**
	 * Main work behavior - called every tick while working
	 */
	@Override
	protected void tickWorking() {
		// Handle cooldown
		if (this.workCooldown > 0) {
			this.workCooldown--;
			return;
		}
		
		VillagerProfession profession = this.villager.getVillagerData().getProfession();
		
		// Execute profession-specific work
		switch(profession.getRegistryName().getPath()) {
			case "farmer":
				doFarmerWork();
				break;
			case "fisherman":
				doFishermanWork();
				break;
			case "shepherd":
				doShepherdWork();
				break;
			case "fletcher":
				doFletcherWork();
				break;
			case "librarian":
				doLibrarianWork();
				break;
			case "cleric":
				doClericWork();
				break;
			case "weaponsmith":
			doSmithWork();
				break;
			case "toolsmith":
				doSmithWork();
				break;
			case "armorer":
				doSmithWork();
				break;
			case "butcher":
				doButcherWork();
				break;
			case "leatherworker":
				doLeatherworkWork();
				break;
			case "mason":
				doMasonWork();
				break;
			default:
				doGenericWork();
				break;
		}
		
		// Update hunger while working
		this.villager.getCapability(CapabilityVillagerAttribute.VILLAGER_HUNGER).ifPresent(h -> {
			if (h.getValue() > 0) {
				h.setValue(h.getValue() - 1);
			}
		});
	}
	
	/**
	 * Farmer profession work:
	 * - Harvest mature crops
	 * - Replant seeds
	 * - Move between farm and job site
	 */
	protected void doFarmerWork() {
		// Check if we're at the farm or need to go there
		if (this.targetCrop == null) {
			// Look for mature crops nearby
			this.targetCrop = findMatureCrop();
			
			if (this.targetCrop != null) {
				// Navigate to the crop
				this.villager.getNavigator().tryMoveToXYZ(
					this.targetCrop.getX(),
					this.targetCrop.getY(),
					this.targetCrop.getZ(),
					this.villager.getAIMoveSpeed()
				);
			} else {
				// No crops to harvest, do generic work
				doGenericWork();
			}
		} else {
			// We're heading to a crop
			double distance = this.getBlockPosDistance(
				this.getVillagerBlockPos(), 
				this.targetCrop
			);
			
			if (distance < 2.0D) {
				// At the crop, harvest it
				harvestCrop(this.targetCrop);
				this.playWorkingAnimation();
				this.workCooldown = WORK_COOLDOWN_MAX;
				this.targetCrop = null;
			} else if (this.villager.getNavigator().noPath()) {
				// Pathing failed, clear target
				this.targetCrop = null;
			}
		}
	}
	
	/**
	 * Find a mature crop to harvest
	 * @return BlockPos of mature crop or null if none found
	 */
	protected BlockPos findMatureCrop() {
		BlockPos villagerPos = this.getVillagerBlockPos();
		int range = 50;
		
		for (int x = -range; x <= range; x++) {
			for (int y = -2; y <= 2; y++) {
				for (int z = -range; z <= range; z++) {
					BlockPos checkPos = villagerPos.add(x, y, z);
					BlockState state = this.villager.world.getBlockState(checkPos);
					
					// Check if it's a mature crop
					if (state.getBlock() instanceof CropsBlock) {
						CropsBlock crop = (CropsBlock) state.getBlock();
						if (crop.isMaxAge(state)) {
							return checkPos;
						}
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Harvest a mature crop
	 * @param cropPos Position of the crop to harvest
	 */
	protected void harvestCrop(BlockPos cropPos) {
		BlockState state = this.villager.world.getBlockState(cropPos);
		
		if (state.getBlock() instanceof CropsBlock) {
			CropsBlock crop = (CropsBlock) state.getBlock();
			
			// Harvest the crop (break and drops items)
			this.villager.world.destroyBlock(cropPos, true);
			
			// Replant if we have seeds
			if (hasSeedsInInventory()) {
				// Get the seed item for this crop
				ItemStack seedStack = getSeedsForCrop(crop);
				if (seedStack != null && !seedStack.isEmpty()) {
					// Try to replant
					BlockState plantedState = crop.getDefaultState().with(CropsBlock.AGE, 0);
					this.villager.world.setBlockState(cropPos, plantedState);
					
					// Consume a seed
					seedStack.shrink(1);
				}
			}
			
			VillageCraft.LOGGER.debug("Farmer harvested crop at " + cropPos);
		}
	}
	
	/**
	 * Check if the villager has seeds in inventory
	 * @return true if seeds are available
	 */
	protected boolean hasSeedsInInventory() {
		Inventory inv = this.villager.getVillagerInventory();
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack stack = inv.getStackInSlot(i);
			if (isSeedItem(stack)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Get seeds for the specified crop type
	 * @param crop The crop block
	 * @return ItemStack of seeds or null
	 */
	protected ItemStack getSeedsForCrop(CropsBlock crop) {
		// This is a simplified version - in reality we'd need to map crops to seeds
		Inventory inv = this.villager.getVillagerInventory();
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack stack = inv.getStackInSlot(i);
			if (isSeedItem(stack)) {
				return stack;
			}
		}
		return null;
	}
	
	/**
	 * Check if an item stack is a seed
	 * @param stack ItemStack to check
	 * @return true if it's a seed item
	 */
	protected boolean isSeedItem(ItemStack stack) {
		return stack.getItem() == Items.WHEAT_SEEDS ||
			   stack.getItem() == Items.CARROT ||
			   stack.getItem() == Items.POTATO ||
			   stack.getItem() == Items.BEETROOT_SEEDS ||
			   stack.getItem() == Items.PUMPKIN_SEEDS ||
			   stack.getItem() == Items.MELON_SEEDS;
	}
	
	/**
	 * Fisherman work - simulate fishing at the job site
	 */
	protected void doFishermanWork() {
		workAtJobSite(() -> {
			// Simulate fishing - play animation and maybe add fish
			this.playWorkingAnimation();
			
			// Small chance to catch fish
			if (this.villager.getRNG().nextInt(10) == 0) {
				ItemStack fish = new ItemStack(Items.COD);
				this.villager.getVillagerInventory().addItem(fish);
				VillageCraft.LOGGER.debug("Fisherman caught a fish");
			}
		});
	}
	
	/**
	 * Shepherd work - look for sheep to shear
	 */
	protected void doShepherdWork() {
		// Look for nearby sheep
		// This would require finding sheep entities and shearing them
		// For now, do generic work
		doGenericWork();
	}
	
	/**
	 * Fletcher work - works at fletching table
	 */
	protected void doFletcherWork() {
		workAtJobSite(() -> {
			this.playWorkingAnimation();
		});
	}
	
	/**
	 * Librarian work - studies at lectern
	 */
	protected void doLibrarianWork() {
		workAtJobSite(() -> {
			this.playWorkingAnimation();
		});
	}
	
	/**
	 * Cleric work - works at brewing stand
	 */
	protected void doClericWork() {
		workAtJobSite(() -> {
			this.playWorkingAnimation();
		});
	}
	
	/**
	 * Smith work (weaponsmith, toolsmith, armorer)
	 */
	protected void doSmithWork() {
		workAtJobSite(() -> {
			this.playWorkingAnimation();
		});
	}
	
	/**
	 * Butcher work - works at smoker
	 */
	protected void doButcherWork() {
		workAtJobSite(() -> {
			this.playWorkingAnimation();
		});
	}
	
	/**
	 * Leatherworker work - works at cauldron
	 */
	protected void doLeatherworkWork() {
		workAtJobSite(() -> {
			this.playWorkingAnimation();
		});
	}
	
	/**
	 * Mason work - works at stonecutter
	 */
	protected void doMasonWork() {
		workAtJobSite(() -> {
			this.playWorkingAnimation();
		});
	}
	
	/**
	 * Generic work - go to job site and play work animation
	 */
	protected void doGenericWork() {
		workAtJobSite(() -> {
			this.playWorkingAnimation();
		});
	}
	
	/**
	 * Generic job site work template
	 * @param workAction Runnable to execute when at the job site
	 */
	protected void workAtJobSite(Runnable workAction) {
		this.villager.getBrain().getMemory(MemoryModuleType.JOB_SITE).ifPresent(jobSite -> {
			GlobalPos pos = jobSite;
			double distance = this.getBlockPosDistance(
				this.getVillagerBlockPos(), 
				pos.getPos()
			);
			
			if (distance > JOB_SITE_RANGE) {
				// Navigate to job site
				if (this.villager.getNavigator().noPath()) {
					this.villager.getNavigator().tryMoveToXYZ(
						pos.getPos().getX(),
						pos.getPos().getY(),
						pos.getPos().getZ(),
						this.villager.getAIMoveSpeed()
					);
				}
			} else {
				// At job site, do work
				workAction.run();
				this.workCooldown = WORK_COOLDOWN_MAX;
			}
		});
	}
}

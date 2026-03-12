package com.villagecraft.entity.goal;

import java.util.EnumSet;
import java.util.List;

import com.villagecraft.VillageCraft;
import com.villagecraft.init.ModVillagerProfessions;
import com.villagecraft.item.profession_tokens.ItemProfessionToken;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.Item;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;

/**
 * Base goal class for VillageCraft villagers.
 * Updated for NeoForge 1.20.2 compatibility.
 */
public class VillagerGoalBase extends Goal {
	
	/** Flag set for controlling mob movement - MOBility (bit 0) */ 
	protected static final int FLAG_MOVE = 1;
	/** Flag set for controling mob movement - LOOK (bit 1) */
	protected static final int FLAG_LOOK = 2;
	/** Flag set for controling mob movement - JUMP (bit 2) */
	protected static final int FLAG_JUMP = 3;
	/** Targeting conditions for finding entities */
	protected static final TargetingConditions CLOSEST_ENTITY_CONDITIONS = TargetingConditions.forNonCombat().range(10.0D).selector((entity) -> !entity.isSpectator() && ((LivingEntity)entity).isPickable());
	
	protected Villager villager;
	
	// Related to tick/cooldowns
	protected int ticksToNextRun = 0;
	protected int cooldownTicks = 20;
	
	// Related to searching
	protected double maxScanRange = 100D;
	
	// Protected variables related to tasks
	protected LivingEntity targetLivingEntity;
	
	protected boolean hasFoundTarget = false;
	protected boolean hasReachedTarget = false;

	protected GlobalPos poiTarget;
	protected PoiType targetPoiType = null;
	
	
	/**
	 * Main Entry point for running goals.  It runs the code relevant to this entity and allows
	 * for running sub-entity logic.
	 * Updated for NeoForge 1.20.2
	 */
	public VillagerGoalBase(Villager villager) {
		this.villager = villager;
		// In 1.20.2, we need to set flags using EnumSet of Goal.Flag
		this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}
	
	public Villager getVillager() {
		return this.villager;
	}
	
	public Villager setVillager(Villager villager) {
		return this.villager = villager;
	}
	
	public VillagerGoalBase getProfessionGoal() {
		VillagerData villagerData = this.villager.getVillagerData();
		
		return null;
	}
	
	public VillagerData getVillagerData() {
		return this.villager.getVillagerData();
	}
	
	public CompoundTag getVillagerNBT() {
		// 1.20.2: saveWithoutId() signature changed
		return this.villager.saveWithoutId(new CompoundTag());
	}
	
	public void checkVillagerForProfession() {
		List<Item> items = getVillagerInventoryList();
		
		if (items.contains(ItemProfessionToken.properties)) {
			// TODO: Implement profession checking
		}
	}
	
	public List<Item> getVillagerInventoryList() {
		return null;
	}
	
	public boolean villagerIsInProfession() {
		return false;
	}
	
	public PoiType getVillagerPOI() {
		// TODO: Implement POI lookup for 1.20.2
		return this.targetPoiType;
	}
	

	protected boolean start = true;
	boolean active = false;
	String profession = "base";
	protected int professionCoolDown = 0;
	
	
	@Override
	public boolean canUse() {
		return false;
	}
	
	/**
	 * Run the goal logic
	 * 
	 * @return boolean - whether the goal finished successfully or not
	 */
	public boolean runGoal() {
		
		// Increment the count of how many times the villager has
		// run the goal, this is used to determine if the villager
		// should start looking for a new target.
		
		if (this.shouldStartRunning()) {
			// TODO: Implement goal execution
		}
		
		return true;
	}
	
	@Override
	public void tick() {
		runGoal();
	}
	
	
	private int scanCount = 0;
	protected int maxScanCount = 40;
	protected boolean searchStarted = false;
	
	protected boolean shouldStartRunning() {
		
		this.scanCount++;
		
		if (scanCount < maxScanCount) {
			this.scanCount = 0;
			return true;
		} else {
			return false;
		}
	}
	
	
	protected boolean shouldReachTarget(BlockPos target) {
		return false;
	}
	
	public int getCooldownTicks() {
		return this.cooldownTicks;
	}

	
	protected double getMaxScanRange() {
		
		return this.maxScanRange;
	}
	
	protected int getNumberOfNearbyVillagers() {
		int count = 0;
		
		// TODO: Implement nearby villager counting for 1.20.2
		
		return count;
	}
	
	protected int getNumberOfNearbyGolems() {
		int count = 0;
		
		// TODO: Implement nearby golem counting for 1.20.2
		
		return count;
	}
	
	protected boolean checkItemHasRoom(Item item) {
		return false;
	}
	
	protected int checkItemCount(Item item) {
		return 0;
	}
	
	protected void addItemToInventory(LivingEntity entity, Item item) {
		
	}
	
	
	public InteractionHand getInteractionTargetHand(LivingEntity entity) {
		InteractionHand hand = null;
		
		if (entity.getItemInHand(InteractionHand.MAIN_HAND) != null) 
			hand = InteractionHand.MAIN_HAND;
		
		return hand;
	}

}
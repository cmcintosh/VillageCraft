package com.villagecraft.entity.goal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.UUID;

import com.villagecraft.VillageCraft;
import com.villagecraft.block.BlockVillageCenter;
import com.villagecraft.capabilities.CapabilityVillagerAttribute;
import com.villagecraft.capabilities.IVillagerAttribute;
import com.villagecraft.capabilities.IVillagerHonor;
import com.villagecraft.capabilities.IVillagerHunger;
import com.villagecraft.capabilities.VillagerHungerAttribute;
import com.villagecraft.entity.professions.VillagerCraftBaseProfession;
import com.villagecraft.init.ModVillagerProfessions;
import com.villagecraft.item.profession_tokens.ItemProfessionToken;
import com.villagecraft.tile.TileEntityVillageCenter;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.villager.VillagerData;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.PointOfInterestManager;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.village.PointOfInterestManager.Status;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.LazyOptional;

public class VillagerGoalBase extends Goal {
	
	protected VillagerEntity villager;
	protected PointOfInterestManager poiManager;
	
	// Related to tick/cooldowns
	protected int ticksToNextRun = 0;
	protected int cooldownTicks = 20;
	
	// Related to searching
	protected double maxScanRange = 100D;
	
	// Protected variables related to tasks
	protected LivingEntity targetLivingEntity;
	protected Block targetBlockType;
	protected BlockPos targetPosition;
	
	protected CompoundNBT extraVillagerData;
	protected ItemProfessionToken token;
	
	protected IVillagerHunger hunger;
	protected IVillagerHonor honor;
	
	protected Random rand = new Random();
	
	public VillagerGoalBase(VillagerEntity entity) { 
		super();
		villager = entity;
		ServerWorld world = (ServerWorld) entity.getEntityWorld();
		poiManager = world.getPointOfInterestManager();
		extraVillagerData = new CompoundNBT();
	}
	
	
	
	protected boolean hasProfessionToken() { 
		
//		this.villager.getVillagerInventory().
		return false;
	}
	

	@Override
	public boolean shouldExecute() {
		
		if (this.shouldExecuteHungerTick()) {
			this.hungerTick();
		}
		
		if (this.villager.getVillagerData().getProfession() instanceof VillagerCraftBaseProfession) { 
			VillagerCraftBaseProfession profession = (VillagerCraftBaseProfession) villager.getVillagerData().getProfession(); 
			return profession.shouldExecuteGoal(villager);
		}
		
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
    	if (this.villager.getVillagerData().getProfession() instanceof VillagerCraftBaseProfession) { 
			VillagerCraftBaseProfession profession = (VillagerCraftBaseProfession) villager.getVillagerData().getProfession(); 
			profession.startExecutingGoal(villager);
		}
    }
	
	public boolean shouldContinueExecuting() {
		if (this.villager.getVillagerData().getProfession() instanceof VillagerCraftBaseProfession) { 
			VillagerCraftBaseProfession profession = (VillagerCraftBaseProfession) villager.getVillagerData().getProfession(); 
			return profession.shouldExecuteGoal(villager);
		}
		return false;
	}
	
	/**
	 * Tick here
	 */
	public void tick() {
		
		// 1. Check if the villager is associated with a village
		
		// 1. a. If not associated try to locate a village center.

		// 1. b. If village center located, move towards the center, and when within 5f register.
		
		// Villager Hunger Related tasks 
		
		
		// Villager Thirst Related tasks
		
		// Villager Want(s) Related tasks
		
		// Villager Quest Related tasks		
		
		if (this.villager.getVillagerData().getProfession() instanceof VillagerCraftBaseProfession) { 
			VillagerCraftBaseProfession profession = (VillagerCraftBaseProfession) villager.getVillagerData().getProfession(); 
			profession.tickGoal(villager);
		}
	}
	
	protected BlockPos getVillagerBlockPos() { 
		if (this.villager != null) {
			return new BlockPos(this.villager.getPosX(), this.villager.getPosY(), this.villager.getPosZ());
		}
		return null;
	}
		
	/**
	 * return a block position object for a entity.
	 */
	protected BlockPos getEntityBlockPos(Entity entity) {
		return new BlockPos(entity.getPosX(),entity.getPosY(), entity.getPosZ());
	}
	
	/**
	 * return a block position object for a entity.
	 */
	protected BlockPos getEntityPrevBlockPos(Entity entity) {
		return new BlockPos(entity.prevPosX, entity.prevPosY, entity.prevPosZ);
	}
	
	
	/**
	 * Heal target living entity
	 */
	protected void healTargetLivingEntity(float amount) { 
		this.targetLivingEntity.heal(amount);
	}
	
	/**
	 * Attack target living entity
	 */
	protected void attackTargetLivingEntity(LivingEntity targetEntity) { 
		this.villager.setAttackTarget(targetEntity);
		this.villager.attackEntityAsMob(targetEntity);
	}
	
	/**
	 * Follow target living entity
	 */
	protected double targetLivingEntityRange;
	protected double maxFollowRange = 10;
	protected void followTargetLivingEntity(LivingEntity targetEntity) {
		targetLivingEntityRange = this.distanceToTargetLivingEntity(targetEntity);
		if (targetLivingEntityRange > maxFollowRange) {
			villager.getNavigator().tryMoveToEntityLiving(targetEntity, villager.getAIMoveSpeed());	
		}
		
	}
	
	/**
	 * Return the distance to the living entity.
	 */
	protected double distanceToTargetLivingEntity(LivingEntity targetEntity) { 
		return this.getBlockPosDistance(this.getVillagerBlockPos(), this.getEntityBlockPos(targetEntity));
	}
	
	/**
	 * Goto target living entity
	 */
	protected void gotoTargetLivingEntity(LivingEntity targetEntity) { 
		villager.getNavigator().tryMoveToEntityLiving(targetEntity, villager.getAIMoveSpeed());
	}
	
	/**
	 * In Melee range of target living entity
	 */
	protected boolean inMeleeRangeTargetLivingEntity(Entity targetEntity) { 
		double range = this.getBlockPosDistance(this.getVillagerBlockPos(), this.getEntityBlockPos(targetEntity));
		return (range < 3);
	}
	
	/**
	 * Locate a entity of type
	 */
	protected List findLivingEntitiesWithinAABB(Class entityType) { 
		List<IronGolemEntity> list = this.villager.world
				.getEntitiesWithinAABB(entityType, 
						this.villager.getBoundingBox().grow(maxScanRange)
				);
		return list;
	}
	
	/**
	 * @Section Block Related
	 */
	
	// Returns the distances to a block, given two block pos;
	protected double getBlockPosDistance(BlockPos a, BlockPos b) {
		return a.distanceSq(b.getX(), b.getY(), b.getZ(), true);
	}
	
	// Return all blocks of a Poi type, and meet our block filters.
	protected Stream<BlockPos> findAllBlocks(PointOfInterestType poi, PointOfInterestManager.Status status) {
		return poiManager.findAll(poi.getPredicate(), new BlockFilter(), this.getVillagerBlockPos(), (int) this.maxScanRange, status);	
	}
	
	// Return the closest block of a Poi type.
	protected BlockPos closestTargetPos;
	protected double lastClosest;
	protected BlockPos findClosestBlock(PointOfInterestType poi, PointOfInterestManager.Status status, BlockPos center) { 
		Stream<BlockPos> blocks = this.findAllBlocks(poi, status);
		
		BlockPos min = null;
		blocks.forEach(b -> {
			double thisDistance = this.getBlockPosDistance(center, b);
			if (closestTargetPos == null) {
				closestTargetPos = b;
				lastClosest = thisDistance;
			}
			else if (thisDistance < lastClosest) {
				closestTargetPos = b;
				lastClosest = thisDistance;
			}
		});
		min = closestTargetPos;
		closestTargetPos = null;
		lastClosest = 0;
		return min;
	}
	
	/**
	 * Provides a block filter
	 */
	public class BlockFilter implements Predicate<BlockPos> { 
		public boolean test(BlockPos t) {
			return true;
		} 
	}
	
	/**
	 * Goto blockpos
	 */
	protected void gotoBlockPos(BlockPos targetBlock) { 
		this.villager.getNavigator().tryMoveToXYZ(targetBlock.getX(), targetBlock.getY(), targetBlock.getZ(), this.villager.getAIMoveSpeed());
	}
	
	/**
	 * @Section Hunger Related
	 */
		protected int lastHungerTick = 0;
		protected int maxHungerTicks = 24000 / 10; 
		protected int hungerLevel = 4;
		protected int theftHungerLevel = 2;
		protected int starvationLevel = 1;
		protected int maxHonorTheft = -4;
		
		boolean hasAte = false;
		boolean hasCheckedInventory = false;
		boolean hasCheckedGround = false;
		boolean hasCheckedPOI = false;
		boolean hasCheckedChests = false;
		ItemStack foundFood = null;
		Block foundFoodBlock = null;
		BlockPos targetFoodBlock = null;
		protected boolean foundFoodOnGround = false;
		protected ItemEntity foodItem;
		protected ChestTileEntity targetChest = null;
		
		// Check if we should execute the hunger tick
		public boolean shouldExecuteHungerTick() {
			// TODO Auto-generated method stub
			lastHungerTick++;
			if (lastHungerTick == maxHungerTicks) {
				lastHungerTick = 0;
				return true;
			}
			return false;
		}
	
		protected void hungerTick() { 
			this.villager.getCapability(CapabilityVillagerAttribute.VILLAGER_HUNGER).ifPresent(h -> {
				
				if (h.getName() == "hunger") {
						int currentHunger = h.getValue();
						if (currentHunger > 0) {
							currentHunger--;
							h.setValue(currentHunger);
						}
						
						if (currentHunger < this.hungerLevel) {
						//	this.eat(h);
						}
						
						if (currentHunger < this.starvationLevel) {
						//	starving();
						}
						
					}
			});
		}
		
		   protected void spawnParticles(IParticleData particleData) {
		      for(int i = 0; i < 5; ++i) {
		         double d0 = this.rand.nextGaussian() * 0.02D;
		         double d1 = this.rand.nextGaussian() * 0.02D;
		         double d2 = this.rand.nextGaussian() * 0.02D;
		         this.villager.world.addParticle(particleData, this.villager.getPosXRandom(1.0D), this.villager.getPosYRandom() + 1.0D, this.villager.getPosZRandom(1.0D), d0, d1, d2);
		      }

		   }
		
		/**
		 * If a villager cant entity deal damage.
		 */
		public void starving() { 
			villager.setShakeHeadTicks(5);
			ResourceLocation location = new ResourceLocation("vcm", "villager_grunt");
			// @TODO: Configurable on/off
			SoundEvent event = new SoundEvent(location);
			villager.playSound(event, 100, 1);
			
			// villager.setHealth((float) (villager.getHealth() - 1));
		}
		
		protected void eat(IVillagerHunger h) { 
			
			// Try to eat from Inventory.
			eatFromInventory(h);
			
			// Try to find food in chest
			eatFromChest(h);
			
			// Try to find food on ground.
			eatFromGround(h);
			
			// Try to purchase food from merchant, trader, cook, or barkeeper.
			
			// Try to steal food form villager
			if (h.getValue() <= this.theftHungerLevel) {
				stealFromVillager(h);
			}
		}
		
		/**
		 * Try and still food from another villager
		 */
		protected void stealFromVillager(IVillagerHunger h) {
			if (this.targetLivingEntity != null) {
				if (villager.getDistance(this.targetLivingEntity) > 2) {
					villager.getNavigator().tryMoveToEntityLiving(this.targetLivingEntity, villager.getAIMoveSpeed());
				}
				else {
					VillageCraft.LOGGER.debug("Attacking target");
					VillagerEntity targetVillager = (VillagerEntity) this.targetLivingEntity;
					this.attackVillagerEntity(targetVillager, 1);
					this.foundFood = this.getFoodInInventory(targetVillager);
					this.eatFoodItem(h);
				}
			} else {
				this.findLivingEntitiesWithinAABB(VillagerEntity.class).forEach(c -> { 
					if ( ((VillagerEntity) c) != this.villager) {
						// Check if this villager has food.
						if (this.hasFoodInInventory((VillagerEntity) c)) {
							this.targetLivingEntity = (VillagerEntity) c;
							return;
						}
					}
				});
			}
		}
		
		/**
		 * Get food from inventory
		 */
		protected void eatFromInventory(IVillagerHunger h) { 
			if (this.hasFoodInInventory(villager)) {
				ItemStack food = this.getFoodInInventory(villager);
				this.eatFoodItem(h);
			}
		}
		
		/**
		 * East food from a chest
		 */
		protected void eatFromChest(IVillagerHunger h) {
			if (foodInChest()) {
				if (this.getBlockPosDistance(villager.getPosition(), this.targetFoodBlock) >= 2) {
					VillageCraft.LOGGER.debug("moving to chest");
					villager.getNavigator().tryMoveToXYZ(this.targetFoodBlock.getX(), this.targetFoodBlock.getY(), this.targetFoodBlock.getZ(), villager.getAIMoveSpeed() * 2);	
				} 
				
				if (this.targetChest != null){
					// get item from container and eat.
					for (int i = 0; i < this.targetChest.getSizeInventory(); i++) {
						ItemStack check = this.targetChest.getStackInSlot(i);
						if (check.getItem().isFood()) {
							this.foundFood = check;
							this.eatFoodItem(h);
							return;
						}
					}
				}
			}
		}
		
		/**
		 * Eat food from ground.
		 */
		protected void eatFromGround(IVillagerHunger h) {
			if (this.foodIsOnGround()) {
				if (this.foodItem != null) {
					float distancetoFood = villager.getDistance(this.foodItem); 
					if (distancetoFood >= 2)
					{
						villager.getNavigator().tryMoveToXYZ(this.foodItem.getPosX(), this.foodItem.getPosY(), this.foodItem.getPosZ(), villager.getAIMoveSpeed() * 2);	
					} else {
						villager.getVillagerInventory().addItem(this.foodItem.getItem().getStack());
						this.eatFoodItem(h);	
					}
				}
			}
		}
		
		/**
		 * Eats a food item.
		 * @param h
		 */
		protected void eatFoodItem(IVillagerHunger h) {
			if (this.foundFood == null) { return; }
			if (this.foundFood.getItem() == null) { return; }
			int healing = this.foundFood.getItem().getFood().getHealing();
			int foodAmount = (int) ( healing * this.foundFood.getItem().getFood().getSaturation() ) * 2;
			h.setValue(foodAmount + h.getValue());
			villager.heal(healing);
			if (this.foundFood != null) {
				this.foundFood.getItem().getFood().getEffects().forEach((e) -> {
					if (e != null) {
						villager.addPotionEffect(e.getFirst());
					}
				});
			}
			this.foundFood.setCount(this.foundFood.getCount() -1);
			this.foodItem = null;
			this.foundFoodBlock = null;
			this.targetChest = null;
			
		}
		
		protected boolean hasFoodInInventory(VillagerEntity enitity) { 
			ItemStack food = this.getFoodInInventory(enitity);
			if (food != null ) {
				this.foundFood = food;
				return true;
			}
			return false;
		}
		
		/*
		 * Returns food items from inventory.
		 */
		protected ItemStack getFoodInInventory(VillagerEntity enitity) { 
			ItemStack food = null;
			Inventory villagerInventory = enitity.getVillagerInventory();
			for (int i = 0; i < villagerInventory.getSizeInventory(); i++) {
				ItemStack check = villagerInventory.getStackInSlot(i);
				if (check.isFood()) {
					return check;
				}
			}
			return food;
		}
		
		/**
		 * Locate food on ground.
		 */
		
		
		protected boolean foodIsOnGround() {
			ItemStack food = this.getClosestFoodOnGround();
			return this.foundFoodOnGround;
		}
		
		// Looks for foods on the ground
		protected ItemStack getClosestFoodOnGround() { 
			ItemStack food = null;
			List<ItemEntity> list = villager.getEntityWorld().getEntitiesWithinAABB(ItemEntity.class, this.villager.getBoundingBox().grow((double)100.0D));
			if (!list.isEmpty()) {
				for(ItemEntity itemEntity : list) {
					if (itemEntity.getItem().isFood()) {
						this.foundFood = itemEntity.getItem().getStack();
						this.foodItem = itemEntity;
						this.foundFoodOnGround = true;
						return itemEntity.getItem().getStack();
					}
				}
			} 
			return food;
		}
		 
		/**
		 * Locate food in a chest?
		 * maybe check the inventory of our workshop for food. or look just for closest chest if our honor is low.
		 */
		protected boolean foodInChest() { 
			ItemStack food = null;
			BlockPos targetBlock = this.findClosestBlock(ModVillagerProfessions.CHEST.get(), Status.ANY, this.getVillagerBlockPos());
			ChestTileEntity chest = (ChestTileEntity) this.villager.world.getTileEntity(targetBlock);
			
			if (chest != null) {
				for (int i = 0; i < chest.getSizeInventory(); i++) {
					ItemStack check = chest.getStackInSlot(i);
					if (check.isFood()) {
						this.targetFoodBlock = targetBlock;
						this.targetChest = chest;
						return true;
					}
				}
			}
			
			return false;
		}
		
		
	
		/**
		 * Returns the Hunger capability for a villager.
		 * @return
		 */
		public LazyOptional<IVillagerHunger> getHunger() {
			return this.villager.getCapability(CapabilityVillagerAttribute.VILLAGER_HUNGER);
		}
	
	/**
	 *  @Section Honor Related
	 */
	
		/**
		 * Returns the Honor capability for a villager.
		 */
		public LazyOptional<IVillagerHonor> getHonor() { 
			return this.villager.getCapability(CapabilityVillagerAttribute.VILLAGER_HONOR); 
		}
	
	/**
	 * @Section Combat Related
	 */
	
		/**
		 * Allow attacking another villager.
		 * @param entity
		 * @param amount
		 */
		public void attackVillagerEntity(VillagerEntity entity, int amount) { 
			entity.setShakeHeadTicks(5);
			entity.performHurtAnimation();
			ResourceLocation location = new ResourceLocation("vcm", "villager_grunt");
			SoundEvent event = new SoundEvent(location);
			entity.playSound(event, 100, 1);
			entity.setHealth((float) (entity.getHealth() - amount));
		}
	
	/**
	 * @Section Chest interactions.
	 */
		protected ArrayList<Item> filterItems;
		protected BlockPos targetContainerBlockPos;
		
		/**
		 * Deposit Items into a container
		 * @return
		 */
		protected boolean depositItems() {
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
			return depositedItems;
		}
		
		private ChestTileEntity getChest() { 
			return (ChestTileEntity) this.villager.world.getTileEntity(this.targetContainerBlockPos);
		}
		
		private void openChest() { 
			ChestTileEntity chest = this.getChest();
			this.villager.world.addBlockEvent(this.targetContainerBlockPos, chest.getBlockState().getBlock(), 1, 1);
			this.villager.world.notifyNeighborsOfStateChange(this.targetContainerBlockPos, chest.getBlockState().getBlock());
		}
		
		private void closeChest() { 
			ChestTileEntity chest = this.getChest();
			this.villager.world.addBlockEvent(this.targetContainerBlockPos, chest.getBlockState().getBlock(), 1, 1);
			this.villager.world.notifyNeighborsOfStateChange(this.targetContainerBlockPos, chest.getBlockState().getBlock());
		}
}

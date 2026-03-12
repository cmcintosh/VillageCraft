package com.villagecraft.entity.goal;

import java.util.EnumSet;
import java.util.List;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.InteractionHand;
import com.villagecraft.VillageCraft;

/**
 * Goal for clerics/healers to heal Iron Golems.
 * Updated for NeoForge 1.20.2
 */
public class HealGolemGoal extends VillagerGoalBase {
	
	public IronGolem targetGolem;
	private int healCooldown = 0;
	private static final int HEAL_COOLDOWN_TICKS = 20;
	private static final double HEAL_RANGE = 3.0;
	private static final double DETECTION_RANGE = 10.0;
	private static final float HEAL_AMOUNT = 5.0f; // 2.5 hearts

	public HealGolemGoal(Villager entity) {
		super(entity);
		// 1.20.2: Use setFlags with EnumSet instead of setMutexFlags
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		// Check if villager is a healer/cleric profession
		if (villager.getVillagerData().getProfession() != net.minecraft.world.entity.npc.VillagerProfession.CLERIC) {
			return false;
		}
		
		// Look for nearby damaged Iron Golems
		List<IronGolem> golems = villager.level().getEntitiesOfClass(
			IronGolem.class,
			villager.getBoundingBox().inflate(DETECTION_RANGE),
			golem -> golem.getHealth() < golem.getMaxHealth()
		);
		
		if (!golems.isEmpty()) {
			targetGolem = golems.get(0); // Target the closest damaged golem
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean canContinueToUse() {
		if (targetGolem == null || !targetGolem.isAlive()) {
			return false;
		}
		return targetGolem.getHealth() < targetGolem.getMaxHealth() && 
			   villager.distanceToSqr(targetGolem) < DETECTION_RANGE * DETECTION_RANGE;
	}
	
	@Override
	public void start() {
		VillageCraft.LOGGER.debug("Villager {} started healing Iron Golem {}", villager.getUUID(), targetGolem.getUUID());
	}
	
	@Override
	public void stop() {
		targetGolem = null;
		healCooldown = 0;
		VillageCraft.LOGGER.debug("Villager {} stopped healing", villager.getUUID());
	}
	
	@Override
	public void tick() {
		if (targetGolem == null || !targetGolem.isAlive()) {
			return;
		}
		
		// Move toward the golem if not close enough
		double distanceSq = villager.distanceToSqr(targetGolem);
		if (distanceSq > HEAL_RANGE * HEAL_RANGE) {
			villager.getNavigation().moveTo(targetGolem, 0.6);
			return;
		}
		
		// Look at the golem
		villager.getLookControl().setLookAt(targetGolem);
		
		// Handle heal cooldown
		if (healCooldown > 0) {
			healCooldown--;
			return;
		}
		
		// Attempt to heal
		healGolem();
	}
	
	public void healGolem() {
		if (targetGolem == null || !targetGolem.isAlive()) {
			return;
		}
		
		// 1.20.2: Check if villager has healing items (potions, golden apples, etc.)
		boolean hasHealItem = false;
		for (int i = 0; i < villager.getInventory().getContainerSize(); i++) {
			ItemStack stack = villager.getInventory().getItem(i);
			if (stack.is(Items.POTION) || stack.is(Items.SPLASH_POTION) || 
			    stack.is(Items.LINGERING_POTION) || stack.is(Items.GOLDEN_APPLE)) {
				hasHealItem = true;
				villager.getInventory().removeItem(i, 1);
				break;
			}
		}
		
		// Always heal (villages provide basic healing for their golems)
		// Heal the golem
		targetGolem.heal(HEAL_AMOUNT);
		
		// 1.20.2: swing() instead of swingArm()
		villager.swing(InteractionHand.MAIN_HAND);
		
		// Spawn healing particles
		if (!villager.level().isClientSide()) {
			for (int i = 0; i < 4; i++) {
				double x = targetGolem.getX() + (villager.getRandom().nextDouble() - 0.5) * targetGolem.getBbWidth();
				double y = targetGolem.getY() + villager.getRandom().nextDouble() * targetGolem.getBbHeight();
				double z = targetGolem.getZ() + (villager.getRandom().nextDouble() - 0.5) * targetGolem.getBbWidth();
				villager.level().addParticle(
					net.minecraft.core.particles.ParticleTypes.HAPPY_VILLAGER,
					x, y, z, 0, 0.2, 0
				);
			}
		}
		
		healCooldown = HEAL_COOLDOWN_TICKS;
		VillageCraft.LOGGER.debug("Villager {} healed Iron Golem for {} health", villager.getUUID(), HEAL_AMOUNT);
	}
	
}
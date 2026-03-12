package com.villagecraft.entity.goal;

import java.util.EnumSet;
import java.util.List;

import com.villagecraft.VillageCraft;

import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;

/**
 * Goal for Bard villagers to perform music.
 * Bards wander and play music that buffs nearby villagers and golems.
 * Updated for NeoForge 1.20.2
 */
public class WanderBardPerformGoal extends VillagerGoalBase {

	protected boolean playedSong = false;
	protected int performanceTicks = 0;
	protected int cooldownTicks = 0;
	
	private static final int PERFORMANCE_DURATION = 200; // 10 seconds performance
	private static final int COOLDOWN_DURATION = 600; // 30 seconds cooldown
	private static final int MAX_AUDIENCE_RANGE = 16;
	private static final int MIN_AUDIENCE = 2; // Need at least 2 listeners
	
	public WanderBardPerformGoal(Villager entity) {
		super(entity);
		this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}
	
	@Override
	public boolean canUse() { 
		// Check cooldown
		if (cooldownTicks > 0) {
			cooldownTicks--;
			return false;
		}
		
		// Only BARD profession can use this goal
		// Note: Need to check if custom BARD profession exists
		// For now, allow Clerics to also perform (backup performers)
		if (villager.getVillagerData().getProfession() != 
			net.minecraft.world.entity.npc.VillagerProfession.CLERIC) {
			// Check for custom BARD profession using profession name
			// This would need custom profession registry
			return false;
		}
		
		// Check if there are enough nearby villagers/golems to perform for
		int audience = getNearbyAudienceCount();
		if (audience < MIN_AUDIENCE) {
			return false;
		}
		
		// Check if bard has space to move around
		return hasSpaceToPerform();
	}
	
	@Override
	public boolean canContinueToUse() {
		if (performanceTicks >= PERFORMANCE_DURATION) {
			return false;
		}
		
		// Continue if we still have an audience
		int audience = getNearbyAudienceCount();
		return audience >= MIN_AUDIENCE;
	}
	
	@Override
	public void start() {
		super.start();
		playedSong = false;
		performanceTicks = 0;
		VillageCraft.LOGGER.debug("Bard {} started performing at {}", 
			villager.getUUID(), villager.blockPosition());
	}
	
	@Override
	public void tick() {
		performanceTicks++;
		
		// Play music at start
		if (!playedSong && performanceTicks == 1) {
			playMusic();
		}
		
		// Wander slowly while performing
		if (villager.tickCount % 10 == 0) {
			// Random small movement
			double offsetX = (villager.getRandom().nextDouble() - 0.5) * 2;
			double offsetZ = (villager.getRandom().nextDouble() - 0.5) * 2;
			
			villager.getNavigation().moveTo(
				villager.getX() + offsetX,
				villager.getY(),
				villager.getZ() + offsetZ,
				0.3 // Slow movement during performance
			);
		}
		
		// Periodically face audience members
		if (villager.tickCount % 40 == 0) {
			// Find nearest audience member to face
			var nearest = findNearestAudienceMember();
			if (nearest != null) {
				villager.getLookControl().setLookAt(nearest);
			}
		}
		
		// Apply performance effects
		if (villager.tickCount % 60 == 0) { // Every 3 seconds
			applyPerformanceBuffs();
		}
		
		// Spawn particle effects
		if (villager.tickCount % 20 == 0) { // Every second
			spawnNoteParticles();
		}
		
		super.tick();
	}
	
	@Override
	public void stop() {
		super.stop();
		playedSong = false;
		cooldownTicks = COOLDOWN_DURATION;
		VillageCraft.LOGGER.debug("Bard {} finished performing", villager.getUUID());
	}
	
	/**
	 * Play the bard's music
	 */
	protected void playMusic() {
		playedSong = true;
		
		if (villager.level().isClientSide()) {
			return;
		}
		
		// 1.20.2: Play sound using level.playSound with Holder<SoundEvent>
		villager.level().playSound(
			null, // No specific player
			villager.getX(),
			villager.getY(),
			villager.getZ(),
			SoundEvents.NOTE_BLOCK_GUITAR.value(), // Use .value() for Holder<SoundEvent>
			SoundSource.NEUTRAL,
			1.0F, // Volume
			1.0F + (villager.getRandom().nextFloat() - 0.5F) * 0.5F // Pitch variation
		);
		
		VillageCraft.LOGGER.debug("Bard {} played music", villager.getUUID());
	}
	
	/**
	 * Count nearby villagers and golems as audience
	 */
	private int getNearbyAudienceCount() {
		int count = 0;
		
		// Count nearby villagers
		List<Villager> villagers = villager.level().getEntitiesOfClass(
			Villager.class,
			villager.getBoundingBox().inflate(MAX_AUDIENCE_RANGE),
			v -> v != villager // Exclude self
		);
		count += villagers.size();
		
		// Count nearby golems (they also enjoy music!)
		List<?> golems = villager.level().getEntitiesOfClass(
			IronGolem.class,
			villager.getBoundingBox().inflate(MAX_AUDIENCE_RANGE),
			golem -> true
		);
		count += golems.size();
		
		return count;
	}
	
	/**
	 * Find the nearest audience member to face
	 */
	private Villager findNearestAudienceMember() {
		List<Villager> villagers = villager.level().getEntitiesOfClass(
			Villager.class,
			villager.getBoundingBox().inflate(MAX_AUDIENCE_RANGE),
			v -> v != villager
		);
		
		Villager nearest = null;
		double nearestDist = Double.MAX_VALUE;
		
		for (Villager v : villagers) {
			double dist = villager.distanceToSqr(v);
			if (dist < nearestDist) {
				nearestDist = dist;
				nearest = v;
			}
		}
		
		return nearest;
	}
	
	/**
	 * Apply buff effects to nearby audience
	 */
	private void applyPerformanceBuffs() {
		if (villager.level().isClientSide()) {
			return;
		}
		
		// Get nearby villagers
		List<Villager> villagers = villager.level().getEntitiesOfClass(
			Villager.class,
			villager.getBoundingBox().inflate(MAX_AUDIENCE_RANGE),
			v -> v != villager
		);
		
		for (Villager audience : villagers) {
			// Small chance to heal from bard's music
			if (villager.getRandom().nextFloat() < 0.1) { // 10% chance
				audience.heal(1.0F);
				
				// Show happy particles
				spawnHappyParticles(audience);
			}
		}
	}
	
	/**
	 * Spawn note particles around the bard
	 */
	private void spawnNoteParticles() {
		if (villager.level().isClientSide()) {
			return;
		}
		
		// Random note color
		float noteColor = villager.getRandom().nextFloat();
		
		for (int i = 0; i < 2; i++) {
			double x = villager.getX() + (villager.getRandom().nextDouble() - 0.5) * 0.5;
			double y = villager.getY() + villager.getEyeHeight();
			double z = villager.getZ() + (villager.getRandom().nextDouble() - 0.5) * 0.5;
			
			villager.level().addParticle(
				net.minecraft.core.particles.ParticleTypes.NOTE,
				x, y, z,
				noteColor, // Color data in X component
				0.0,
				0.0
			);
		}
	}
	
	/**
	 * Spawn happy particles for healed audience
	 */
	private void spawnHappyParticles(Villager target) {
		if (!(target.level() instanceof net.minecraft.server.level.ServerLevel serverLevel)) {
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			double x = target.getX() + (target.getRandom().nextDouble() - 0.5) * 0.5;
			double y = target.getY() + target.getEyeHeight();
			double z = target.getZ() + (target.getRandom().nextDouble() - 0.5) * 0.5;
			
			serverLevel.sendParticles(
				net.minecraft.core.particles.ParticleTypes.HAPPY_VILLAGER,
				x, y, z,
				1, // count
				0, 0.1, 0,
				0.1 // speed
			);
		}
	}
	
	/**
	 * Check if there's space to perform
	 */
	private boolean hasSpaceToPerform() {
		BlockPos pos = villager.blockPosition();
		Level level = villager.level();
		
		// Check if we can move around
		for (int x = -2; x <= 2; x++) {
			for (int z = -2; z <= 2; z++) {
				BlockPos checkPos = pos.offset(x, 0, z);
				if (!level.getBlockState(checkPos).getCollisionShape(level, checkPos).isEmpty()) {
					return false; // Blocked
				}
			}
		}
		
		return true;
	}
}
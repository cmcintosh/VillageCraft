package com.villagecraft.entity.goal;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import com.villagecraft.VillageCraft;

import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

/**
 * Goal for Bard villagers to perform songs.
 * Bards wander and play songs that provide health and mood bonuses to listeners.
 * Songs include melodies like "Village Life" and "Morning Cheer" that boost morale.
 * Updated for NeoForge 1.20.2
 */
public class WanderBardPerformGoal extends VillagerGoalBase {

	protected boolean playedSong = false;
	protected int performanceTicks = 0;
	protected int cooldownTicks = 0;
	protected BardSong currentSong = null;
	protected int songLineIndex = 0;
	protected int nextLyricTick = 0;
	
	// Performance constants
	private static final int PERFORMANCE_DURATION = 300; // 15 seconds performance
	private static final int COOLDOWN_DURATION = 600; // 30 seconds cooldown
	private static final int MAX_AUDIENCE_RANGE = 20; // Increased range for players
	private static final int MIN_AUDIENCE = 1; // Need at least 1 listener
	private static final int BUFF_INTERVAL = 40; // Apply buffs every 2 seconds
	
	// Song collection
	private static final BardSong[] SONGS = {
		new BardSong("The Village Life", 
			SoundEvents.NOTE_BLOCK_GUITAR.value(),
			new String[]{
				"♪ In this village where we dwell ♪",
				"♪ Stories of our home we tell ♪", 
				"♪ Through the day and through the night ♪",
				"♪ Our village shines with golden light ♪"
			}, 1.0f),
		new BardSong("Morning Cheer",
			SoundEvents.NOTE_BLOCK_HARP.value(),
			new String[]{
				"♪ Rise and shine, a new day born ♪",
				"♪ Chase away the morning's mourn ♪",
				"♪ With hearts so light and spirits high ♪",
				"♪ Together we reach for the sky ♪"
			}, 1.5f),
		new BardSong("Traveler's Rest",
			SoundEvents.NOTE_BLOCK_FLUTE.value(),
			new String[]{
				"♪ Rest your feet, weary soul ♪",
				"♪ Let the village make you whole ♪",
				"♪ Here you'll find a friendly face ♪",
				"♪ And a warm and gentle embrace ♪"
			}, 2.0f),
		new BardSong("The Golem's Watch",
			SoundEvents.NOTE_BLOCK_CHIME.value(),
			new String[]{
				"♪ Iron guardian strong and true ♪",
				"♪ Watching over me and you ♪",
				"♪ Through the night you stand so tall ♪",
				"♪ Protecting one and protecting all ♪"
			}, 1.5f)
	};

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
		
		// Allow any employed villager to perform as a bard
		// Not just Clerics - this represents village entertainment
		if (villager.getVillagerData().getProfession() == 
			net.minecraft.world.entity.npc.VillagerProfession.NITWIT) {
			return false; // Nitwits don't perform
		}
		
		// Check if there are enough nearby listeners
		int audience = getNearbyAudienceCount();
		if (audience < MIN_AUDIENCE) {
			return false;
		}
		
		// Check if bard has space to perform
		return hasSpaceToPerform();
	}
	
	@Override
	public boolean canContinueToUse() {
		if (performanceTicks >= PERFORMANCE_DURATION) {
			return false;
		}
		
		// Continue if we still have an audience (can drop below minimum mid-performance)
		int audience = getNearbyAudienceCount();
		return audience >= 1; // At least one person remains
	}
	
	@Override
	public void start() {
		super.start();
		playedSong = false;
		performanceTicks = 0;
		songLineIndex = 0;
		nextLyricTick = 0;
		
		// Pick a random song
		currentSong = SONGS[villager.getRandom().nextInt(SONGS.length)];
		
		VillageCraft.LOGGER.debug("Bard {} started performing '{}' at {}", 
			villager.getUUID(), currentSong.name, villager.blockPosition());
		
		// Announce performance start
		announcePerformance();
	}
	
	@Override
	public void tick() {
		performanceTicks++;
		
		// Play music at start
		if (!playedSong && performanceTicks == 1) {
			playSong();
		}
		
		// Sing lyrics periodically
		if (performanceTicks >= nextLyricTick && songLineIndex < currentSong.lyrics.length) {
			singLyric();
			nextLyricTick = performanceTicks + 60; // Next line in 3 seconds
		}
		
		// Wander slowly while performing
		if (villager.tickCount % 10 == 0) {
			double offsetX = (villager.getRandom().nextDouble() - 0.5) * 2;
			double offsetZ = (villager.getRandom().nextDouble() - 0.5) * 2;
			
			villager.getNavigation().moveTo(
				villager.getX() + offsetX,
				villager.getY(),
				villager.getZ() + offsetZ,
				0.25 // Slow movement during performance
			);
		}
		
		// Face audience periodically
		if (villager.tickCount % 40 == 0) {
			var nearest = findNearestAudienceMember();
			if (nearest != null) {
				villager.getLookControl().setLookAt(nearest);
			}
		}
		
		// Apply performance buffs regularly
		if (performanceTicks % BUFF_INTERVAL == 0) {
			applyPerformanceBuffs();
			applyMoodBuff();
		}
		
		// Spawn note particles
		if (villager.tickCount % 10 == 0) {
			spawnNoteParticles();
		}
		
		super.tick();
	}
	
	@Override
	public void stop() {
		super.stop();
		playedSong = false;
		cooldownTicks = COOLDOWN_DURATION;
		
		// Send ending message
		String ending = "♪ Thank you for listening! ♪";
		broadcastToAudience(Component.literal(ending));
		
		VillageCraft.LOGGER.debug("Bard {} finished performing", villager.getUUID());
	}
	
	/**
	 * Announce performance start to nearby audience
	 */
	private void announcePerformance() {
		String announcement = "♪ Now performing: " + currentSong.name + " ♪";
		broadcastToAudience(Component.literal(announcement));
	}
	
	/**
	 * Sing the next lyric line
	 */
	private void singLyric() {
		if (currentSong == null || songLineIndex >= currentSong.lyrics.length) {
			return;
		}
		
		String lyric = currentSong.lyrics[songLineIndex];
		broadcastToAudience(Component.literal(lyric));
		songLineIndex++;
	}
	
	/**
	 * Broadcast message to all nearby audience members
	 */
	private void broadcastToAudience(Component message) {
		// Send to nearby players
		List<Player> players = villager.level().getEntitiesOfClass(
			Player.class,
			villager.getBoundingBox().inflate(MAX_AUDIENCE_RANGE),
			player -> player.distanceToSqr(villager) <= MAX_AUDIENCE_RANGE * MAX_AUDIENCE_RANGE
		);
		
		for (Player player : players) {
			player.sendSystemMessage(Component.literal("§6[Bard] §f").append(message));
		}
	}
	
	/**
	 * Play the bard's song
	 */
	protected void playSong() {
		playedSong = true;
		
		if (villager.level().isClientSide()) {
			return;
		}
		
		// Play the song's sound with pitch variation
		villager.level().playSound(
			null,
			villager.getX(),
			villager.getY(),
			villager.getZ(),
			currentSong.soundEvent,
			SoundSource.NEUTRAL,
			0.8F, // Volume
			1.0F + (villager.getRandom().nextFloat() - 0.5F) * 0.3F // Pitch variation
		);
		
		VillageCraft.LOGGER.debug("Bard {} started playing '{}'", villager.getUUID(), currentSong.name);
	}
	
	/**
	 * Apply health buffs to nearby audience
	 */
	private void applyPerformanceBuffs() {
		if (villager.level().isClientSide()) {
			return;
		}
		
		// Heal villagers
		List<Villager> villagers = villager.level().getEntitiesOfClass(
			Villager.class,
			villager.getBoundingBox().inflate(MAX_AUDIENCE_RANGE),
			v -> v != villager
		);
		
		for (Villager audience : villagers) {
			// Health bonus: 1-2 hearts depending on song
			float healAmount = currentSong.healBonus;
			audience.heal(healAmount);
			
			// Show happy particles on successful heal
			if (audience.getRandom().nextFloat() < 0.3) {
				spawnHappyParticles(audience);
			}
		}
		
		// Heal players too
		List<Player> players = villager.level().getEntitiesOfClass(
			Player.class,
			villager.getBoundingBox().inflate(MAX_AUDIENCE_RANGE),
			player -> player.distanceToSqr(villager) <= MAX_AUDIENCE_RANGE * MAX_AUDIENCE_RANGE
		);
		
		for (Player player : players) {
			// Smaller heal for players (0.5 - 1 heart)
			float playerHeal = currentSong.healBonus * 0.5f;
			player.heal(playerHeal);
			
			// Show hearts when healed
			if (playerHeal >= 0.5f && player instanceof ServerPlayer) {
				((ServerPlayer)player).getFoodData().eat(1, 0.1f); // Small saturation bonus too
			}
		}
	}
	
	/**
	 * Apply mood/motivation buff through saturation and message
	 */
	private void applyMoodBuff() {
		if (villager.level().isClientSide()) {
			return;
		}
		
		// Give buff messages to players
		List<Player> players = villager.level().getEntitiesOfClass(
			Player.class,
			villager.getBoundingBox().inflate(MAX_AUDIENCE_RANGE),
			player -> player.distanceToSqr(villager) <= MAX_AUDIENCE_RANGE * MAX_AUDIENCE_RANGE
		);
		
		String[] moodMessages = {
			"§dYou feel inspired by the music...",
			"§dThe song lifts your spirits!",
			"§dYou feel more optimistic...",
			"§dThe melody brings you peace."
		};
		
		for (Player player : players) {
			// Random mood message
			if (player.getRandom().nextFloat() < 0.2) {
				String message = moodMessages[player.getRandom().nextInt(moodMessages.length)];
				player.sendSystemMessage(Component.literal(message));
			}
			
			// Slight hunger restoration (inspired to eat)
			if (player instanceof ServerPlayer serverPlayer) {
				if (serverPlayer.getFoodData().getFoodLevel() < 20) {
					serverPlayer.getFoodData().eat(1, 0.05f);
				}
			}
		}
	}
	
	/**
	 * Count nearby villagers, golems, and players as audience
	 */
	private int getNearbyAudienceCount() {
		int count = 0;
		
		// Count nearby villagers
		List<Villager> villagers = villager.level().getEntitiesOfClass(
			Villager.class,
			villager.getBoundingBox().inflate(MAX_AUDIENCE_RANGE),
			v -> v != villager
		);
		count += villagers.size();
		
		// Count nearby golems
		List<IronGolem> golems = villager.level().getEntitiesOfClass(
			IronGolem.class,
			villager.getBoundingBox().inflate(MAX_AUDIENCE_RANGE),
			golem -> true
		);
		count += golems.size();
		
		// Count nearby players
		List<Player> players = villager.level().getEntitiesOfClass(
			Player.class,
			villager.getBoundingBox().inflate(MAX_AUDIENCE_RANGE),
			player -> true
		);
		count += players.size();
		
		return count;
	}
	
	/**
	 * Find the nearest audience member to face
	 */
	private net.minecraft.world.entity.Entity findNearestAudienceMember() {
		var nearest = findNearestEntity(villager.level().getEntitiesOfClass(
			Villager.class,
			villager.getBoundingBox().inflate(MAX_AUDIENCE_RANGE),
			v -> v != villager
		));
		
		if (nearest != null) return nearest;
		
		// Check for players too
		var nearestPlayer = findNearestEntity(villager.level().getEntitiesOfClass(
			Player.class,
			villager.getBoundingBox().inflate(MAX_AUDIENCE_RANGE),
			player -> true
		));
		
		return nearestPlayer;
	}
	
	private <T extends net.minecraft.world.entity.Entity> T findNearestEntity(List<T> entities) {
		if (entities.isEmpty()) return null;
		
		T nearest = null;
		double nearestDist = Double.MAX_VALUE;
		
		for (T entity : entities) {
			double dist = villager.distanceToSqr(entity);
			if (dist < nearestDist) {
				nearestDist = dist;
				nearest = entity;
			}
		}
		
		return nearest;
	}
	
	/**
	 * Spawn note particles around the bard
	 */
	private void spawnNoteParticles() {
		if (villager.level().isClientSide()) {
			return;
		}
		
		float noteColor = villager.getRandom().nextFloat();
		
		for (int i = 0; i < 3; i++) {
			double x = villager.getX() + (villager.getRandom().nextDouble() - 0.5) * 0.5;
			double y = villager.getY() + villager.getEyeHeight() + (villager.getRandom().nextDouble() - 0.5);
			double z = villager.getZ() + (villager.getRandom().nextDouble() - 0.5) * 0.5;
			
			villager.level().addParticle(
				net.minecraft.core.particles.ParticleTypes.NOTE,
				x, y, z,
				noteColor,
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
		
		for (int i = 0; i < 2; i++) {
			double x = target.getX() + (target.getRandom().nextDouble() - 0.5) * 0.5;
			double y = target.getY() + target.getEyeHeight();
			double z = target.getZ() + (target.getRandom().nextDouble() - 0.5) * 0.5;
			
			serverLevel.sendParticles(
				net.minecraft.core.particles.ParticleTypes.HAPPY_VILLAGER,
				x, y, z,
				1,
				0, 0.1, 0,
				0.1
			);
		}
	}
	
	/**
	 * Check if there's space to perform
	 */
	private boolean hasSpaceToPerform() {
		BlockPos pos = villager.blockPosition();
		Level level = villager.level();
		
		for (int x = -2; x <= 2; x++) {
			for (int z = -2; z <= 2; z++) {
				BlockPos checkPos = pos.offset(x, 0, z);
				if (!level.getBlockState(checkPos).getCollisionShape(level, checkPos).isEmpty()) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Data class for bard songs
	 */
	private static class BardSong {
		final String name;
		final net.minecraft.sounds.SoundEvent soundEvent;
		final String[] lyrics;
		final float healBonus;
		
		BardSong(String name, net.minecraft.sounds.SoundEvent soundEvent, String[] lyrics, float healBonus) {
			this.name = name;
			this.soundEvent = soundEvent;
			this.lyrics = lyrics;
			this.healBonus = healBonus;
		}
	}
}

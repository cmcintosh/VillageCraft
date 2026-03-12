package com.villagecraft.entity.goal;

import java.util.EnumSet;
import java.util.List;

import com.villagecraft.VillageCraft;

import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import net.minecraft.core.particles.ParticleOptions;

/**
 * Base goal for all musician professions (Bard, Singer, Drummer, Bassist).
 * Provides performance-based buffs with synergy when multiple musicians are active.
 * 
 * NeoForge 1.20.2 - Musical Ensemble System
 */
public abstract class WanderMusicianPerformGoal extends VillagerGoalBase {

    protected boolean playing = false;
    protected int performanceTicks = 0;
    protected int cooldownTicks = 0;
    protected int songLineIndex = 0;
    protected int nextLyricTick = 0;
    
    // Performance constants
    protected static final int PERFORMANCE_DURATION = 300; // 15 seconds
    protected static final int COOLDOWN_DURATION = 600; // 30 seconds
    protected static final int MAX_AUDIENCE_RANGE = 24; // Slightly larger range for ensemble
    protected static final int MIN_AUDIENCE = 1;
    protected static final int BUFF_INTERVAL = 40; // 2 seconds
    protected static final int LYRIC_INTERVAL = 60; // 3 seconds
    protected static final int PARTICLE_INTERVAL = 5; // Every 5 ticks
    
    // Synergy constants
    protected static final int SYNERGY_RANGE = 32; // Range to detect other musicians
    protected static final float SOLO_MULTIPLIER = 1.0f;
    protected static final float DUET_MULTIPLIER = 1.25f; // 2 musicians
    protected static final float TRIO_MULTIPLIER = 1.50f; // 3 musicians
    protected static final float ENSEMBLE_MULTIPLIER = 2.0f; // 4 musicians
    
    protected MusicianSong currentSong;
    
    public WanderMusicianPerformGoal(Villager villager, float speed) {
        super(villager, speed);
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }
    
    @Override
    public boolean canUse() {
        if (cooldownTicks > 0) {
            cooldownTicks--;
            return false;
        }
        
        if (!isProfessionMusician()) {
            return false;
        }
        
        int nearbyAudience = countNearbyAudience();
        return nearbyAudience >= MIN_AUDIENCE && getRandom().nextInt(100) < 5; // 5% chance per tick
    }
    
    @Override
    public boolean canContinueToUse() {
        if (!playing) return false;
        return performanceTicks < PERFORMANCE_DURATION && countNearbyAudience() >= 1;
    }
    
    @Override
    public void start() {
        this.playing = true;
        this.performanceTicks = 0;
        this.songLineIndex = 0;
        this.nextLyricTick = LYRIC_INTERVAL;
        this.currentSong = getSong();
        
        // Announce performance
        Villager villager = this.villager;
        float synergy = calculateSynergyMultiplier();
        String synergyText = getSynergyText(synergy);
        
        villager.level().players().forEach(player -> {
            if (player instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
                if (player.distanceTo(villager) < MAX_AUDIENCE_RANGE) {
                    player.sendSystemMessage(Component.literal("\u00a7d[Music] \u00a7f" + getMusicianName() + " begins " + synergyText + "playing '" + currentSong.getName() + "'!"));
                }
            }
        });
        
        this.villager.getNavigation().stop();
        VillageCraft.LOGGER.debug("{} starting performance: {}", getMusicianName(), currentSong.getName());
    }
    
    @Override
    public void tick() {
        if (!playing) return;
        
        performanceTicks++;
        
        // Spawn particles
        if (performanceTicks % PARTICLE_INTERVAL == 0) {
            spawnPerformanceParticles();
        }
        
        // Send lyrics
        if (performanceTicks >= nextLyricTick && songLineIndex < currentSong.getLyrics().length) {
            sendLyric(currentSong.getLyrics()[songLineIndex]);
            songLineIndex++;
            nextLyricTick += LYRIC_INTERVAL;
        }
        
        // Apply buffs
        if (performanceTicks % BUFF_INTERVAL == 0) {
            applyPerformanceBuffs();
        }
        
        // Look at nearest audience member
        lookAtNearestAudience();
    }
    
    @Override
    public void stop() {
        if (playing) {
            float synergy = calculateSynergyMultiplier();
            if (synergy >= ENSEMBLE_MULTIPLIER - 0.1f) {
                // Full ensemble - send finale message
                sendMessage("\u00a76\u2726 The musical ensemble concludes with a magnificent finale! \u2726");
            }
        }
        
        this.playing = false;
        this.cooldownTicks = COOLDOWN_DURATION;
        VillageCraft.LOGGER.debug("{} finished performance, cooldown: {} ticks", getMusicianName(), COOLDOWN_DURATION);
    }
    
    /**
     * Apply performance buffs to nearby villagers and players
     * Scales with synergy multiplier
     */
    protected void applyPerformanceBuffs() {
        float synergyMultiplier = calculateSynergyMultiplier();
        float intensity = currentSong.getIntensity() * synergyMultiplier;
        
        // Apply musician-specific buffs
        applyMusicianBuffs(intensity, synergyMultiplier);
        
        // Apply synergy bonus buffs
        if (synergyMultiplier >= DUET_MULTIPLIER) {
            applySynergyBuffs(synergyMultiplier);
        }
    }
    
    /**
     * Each musician type implements their specific buffs
     */
    protected abstract void applyMusicianBuffs(float intensity, float synergyMultiplier);
    
    /**
     * Bonus buffs from synergy
     */
    protected void applySynergyBuffs(float synergyMultiplier) {
        // Base synergy: minor regeneration at duet level+
        if (synergyMultiplier >= DUET_MULTIPLIER) {
            List<Villager> nearbyVillagers = getNearbyVillagers(MAX_AUDIENCE_RANGE);
            for (Villager villager : nearbyVillagers) {
                // Minor health boost during performances with multiple musicians
                if (villager.getHealth() < villager.getMaxHealth()) {
                    villager.heal(0.5f * (synergyMultiplier - 1.0f));
                }
            }
            
            // Players also get healing
            List<Player> nearbyPlayers = getNearbyPlayers(MAX_AUDIENCE_RANGE);
            for (Player player : nearbyPlayers) {
                if (!player.isCreative() && player.getHealth() < player.getMaxHealth()) {
                    player.heal(0.25f * (synergyMultiplier - 1.0f));
                }
            }
        }
    }
    
    /**
     * Calculate synergy multiplier based on nearby active musicians
     */
    protected float calculateSynergyMultiplier() {
        int musicianCount = countNearbyActiveMusicians();
        
        if (musicianCount >= 4) return ENSEMBLE_MULTIPLIER;
        if (musicianCount >= 3) return TRIO_MULTIPLIER;
        if (musicianCount >= 2) return DUET_MULTIPLIER;
        return SOLO_MULTIPLIER;
    }
    
    /**
     * Count active musicians (performing or with recent performance) in range
     */
    protected int countNearbyActiveMusicians() {
        int count = 0;
        List<Villager> villagers = getNearbyVillagers(SYNERGY_RANGE);
        
        for (Villager villager : villagers) {
            if (isVillagerMusician(villager) && isVillagerActive(villager)) {
                count++;
            }
        }
        
        return count;
    }
    
    /**
     * Check if a villager is a musician profession
     */
    protected abstract boolean isVillagerMusician(Villager villager);
    
    /**
     * Check if villager is currently active (performing or recently performed)
     */
    protected boolean isVillagerActive(Villager villager) {
        // This is a simplified check - in reality we'd track active performers
        // For now, we check if they're close and have our goal
        return villager.distanceTo(this.villager) < SYNERGY_RANGE;
    }
    
    /**
     * Get descriptive text for synergy level
     */
    protected String getSynergyText(float synergy) {
        if (synergy >= ENSEMBLE_MULTIPLIER - 0.1f) return "an \u00a76ENSEMBLE\u00a7f ";
        if (synergy >= TRIO_MULTIPLIER - 0.1f) return "a \u00a75trio\u00a7f ";
        if (synergy >= DUET_MULTIPLIER - 0.1f) return "a \u00a7bduet\u00a7f ";
        return "";
    }
    
    /**
     * Spawn musician-specific particles
     */
    protected abstract void spawnPerformanceParticles();
    
    /**
     * Get the song for this musician type
     */
    protected abstract MusicianSong getSong();
    
    /**
     * Check if this villager's profession is a musician
     */
    protected abstract boolean isProfessionMusician();
    
    /**
     * Get the display name of this musician type
     */
    protected abstract String getMusicianName();
    
    /**
     * Send a lyric line to nearby players
     */
    protected void sendLyric(String lyric) {
        Villager villager = this.villager;
        villager.level().players().forEach(player -> {
            if (player instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
                if (player.distanceTo(villager) < MAX_AUDIENCE_RANGE) {
                    player.sendSystemMessage(Component.literal(getLyricPrefix() + lyric));
                }
            }
        });
    }
    
    /**
     * Get the prefix for lyric messages
     */
    protected abstract String getLyricPrefix();
    
    /**
     * Send a message to nearby players
     */
    protected void sendMessage(String message) {
        Villager villager = this.villager;
        villager.level().players().forEach(player -> {
            if (player instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
                if (player.distanceTo(villager) < MAX_AUDIENCE_RANGE) {
                    player.sendSystemMessage(Component.literal(message));
                }
            }
        });
    }
    
    /**
     * Data class for musician songs
     */
    protected static class MusicianSong {
        private final String name;
        private final SoundEvent instrument;
        private final String[] lyrics;
        private final float intensity;
        
        public MusicianSong(String name, SoundEvent instrument, String[] lyrics, float intensity) {
            this.name = name;
            this.instrument = instrument;
            this.lyrics = lyrics;
            this.intensity = intensity;
        }
        
        public String getName() { return name; }
        public SoundEvent getInstrument() { return instrument; }
        public String[] getLyrics() { return lyrics; }
        public float getIntensity() { return intensity; }
    }
}

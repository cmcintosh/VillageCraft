package com.villagecraft.entity.manager;

import com.villagecraft.VillageCraft;
import com.villagecraft.entity.goal.WanderMusicianPerformGoal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Manager for tracking musical ensemble performances.
 * Coordinates buffs when multiple musicians perform simultaneously.
 * 
 * NeoForge 1.20.2 - Musical Ensemble System
 */
public class EnsembleManager {
    
    // Active performances: Villager UUID -> Performance data
    private static final Map<UUID, PerformanceData> activePerformances = new HashMap<>();
    
    // Performance data structure
    public static class PerformanceData {
        public final UUID villagerId;
        public final BlockPos location;
        public final long startTime;
        public final int range;
        public final MusicianType type;
        public float intensity;
        
        public PerformanceData(UUID villagerId, BlockPos location, long startTime, 
                              int range, MusicianType type, float intensity) {
            this.villagerId = villagerId;
            this.location = location;
            this.startTime = startTime;
            this.range = range;
            this.type = type;
            this.intensity = intensity;
        }
    }
    
    public enum MusicianType {
        BARD("Bard", 1.0f, 0xFFD700),        // Gold
        SINGER("Singer", 0.9f, 0xFF69B4),    // Hot pink
        DRUMMER("Drummer", 1.1f, 0x8B4513),    // Saddle brown
        BASSIST("Bassist", 1.0f, 0x4169E1);   // Royal blue
        
        private final String displayName;
        private final float basePower;
        private final int particleColor;
        
        MusicianType(String displayName, float basePower, int particleColor) {
            this.displayName = displayName;
            this.basePower = basePower;
            this.particleColor = particleColor;
        }
        
        public String getDisplayName() { return displayName; }
        public float getBasePower() { return basePower; }
        public int getParticleColor() { return particleColor; }
    }
    
    /**
     * Register a new performance
     * @return PerformanceData for this musician
     */
    public static PerformanceData registerPerformance(UUID villagerId, BlockPos location, 
                                                       long gameTime, int range, 
                                                       MusicianType type, float intensity) {
        PerformanceData data = new PerformanceData(villagerId, location, gameTime, range, type, intensity);
        activePerformances.put(villagerId, data);
        VillageCraft.LOGGER.debug("Registered {} performance at {} with intensity {}", type.displayName, location, intensity);
        return data;
    }
    
    /**
     * End a performance and remove from tracking
     */
    public static void endPerformance(UUID villagerId) {
        PerformanceData removed = activePerformances.remove(villagerId);
        if (removed != null) {
            VillageCraft.LOGGER.debug("Ended {} performance", removed.type.displayName);
        }
    }
    
    /**
     * Get the number of active performances in a given area
     */
    public static int getActiveMusicianCount(BlockPos center, int range) {
        int count = 0;
        for (PerformanceData data : activePerformances.values()) {
            if (data.location.distSqr(center) <= range * range) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Calculate synergy multiplier for performances in an area
     */
    public static float calculateSynergyMultiplier(BlockPos center, int range) {
        int count = getActiveMusicianCount(center, range);
        
        switch (count) {
            case 0:
            case 1:
                return 1.0f; // Solo
            case 2:
                return 1.25f; // Duet
            case 3:
                return 1.50f; // Trio
            default:
                return 2.0f; // Full ensemble (4+)
        }
    }
    
    /**
     * Check if a specific musician type is currently performing in range
     */
    public static boolean isMusicianTypeActive(BlockPos center, int range, MusicianType type) {
        for (PerformanceData data : activePerformances.values()) {
            if (data.type == type && data.location.distSqr(center) <= range * range) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Get all active performance types in range as a bitmask
     */
    public static int getActiveTypeMask(BlockPos center, int range) {
        int mask = 0;
        for (PerformanceData data : activePerformances.values()) {
            if (data.location.distSqr(center) <= range * range) {
                mask |= (1 << data.type.ordinal());
            }
        }
        return mask;
    }
    
    /**
     * Get display names of all active musician types in range
     */
    public static String getActiveMusiciansText(BlockPos center, int range) {
        int mask = getActiveTypeMask(center, range);
        if (mask == 0) return "";
        
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        
        for (MusicianType type : MusicianType.values()) {
            if ((mask & (1 << type.ordinal())) != 0) {
                if (!first) sb.append(", ");
                sb.append(type.displayName);
                first = false;
            }
        }
        
        return sb.toString();
    }
    
    /**
     * Get the ensemble tier name
     */
    public static String getEnsembleTierName(int musicianCount) {
        switch (musicianCount) {
            case 0:
            case 1:
                return "Solo";
            case 2:
                return "Duet";
            case 3:
                return "Trio";
            case 4:
                return "Quartet";
            default:
                return "Full Ensemble";
        }
    }
    
    /**
     * Get color for ensemble tier
     */
    public static int getEnsembleTierColor(int musicianCount) {
        switch (musicianCount) {
            case 0:
            case 1:
                return 0xFFFFFF; // White
            case 2:
                return 0x00BFFF; // Deep sky blue
            case 3:
                return 0x9370DB; // Medium purple
            case 4:
                return 0xFFD700; // Gold
            default:
                return 0xFF4500; // Orange red
        }
    }
    
    /**
     * Cleanup stale performances (called periodically)
     */
    public static void cleanup(long currentTime, long maxAge) {
        activePerformances.entrySet().removeIf(entry -> {
            boolean stale = (currentTime - entry.getValue().startTime) > maxAge;
            if (stale) {
                VillageCraft.LOGGER.debug("Cleaned up stale performance for {}", entry.getKey());
            }
            return stale;
        });
    }
    
    /**
     * Clear all tracked performances
     */
    public static void clear() {
        activePerformances.clear();
    }
}

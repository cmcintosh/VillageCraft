package com.villagecraft.entity.goal;

import com.villagecraft.entity.manager.EnsembleManager;
import com.villagecraft.entity.manager.EnsembleManager.MusicianType;
import com.villagecraft.init.ModVillagerProfessions;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;

import java.util.List;
import java.util.UUID;

/**
 * Goal for Singer villagers to perform.
 * Singers provide saturation and experience buffs.
 * Part of the Musical Ensemble system.
 * 
 * NeoForge 1.20.2
 */
public class SingerPerformGoal extends WanderMusicianPerformGoal {
    
    private UUID performanceId;
    
    public SingerPerformGoal(Villager villager, float speed) {
        super(villager, speed);
    }
    
    @Override
    protected boolean isVillagerMusician(Villager villager) {
        return villager.getVillagerData().getProfession() == ModVillagerProfessions.SINGER.get();
    }
    
    @Override
    protected boolean isProfessionMusician() {
        return villager.getVillagerData().getProfession() == ModVillagerProfessions.SINGER.get();
    }
    
    @Override
    protected String getMusicianName() {
        return "Singer";
    }
    
    @Override
    protected String getLyricPrefix() {
        return "\u00a7d[Singer] \u00a7f";
    }
    
    @Override
    protected MusicianSong getSong() {
        // Singer-specific songs
        MusicianSong[] songs = {
            new MusicianSong("Village's Voice", 
                SoundEvents.NOTE_BLOCK_HARP.value(),
                new String[]{
                    "\u266a A melody rises from the heart \u266a",
                    "\u266a Each note a work of art \u266a",
                    "\u266a Voices joined in harmony \u266a",
                    "\u266a Echoing through the village free \u266a"
                }, 1.2f),
            new MusicianSong("Chorus of the Ages",
                SoundEvents.NOTE_BLOCK_CHIME.value(),
                new String[]{
                    "\u266a Songs passed down through time \u266a",
                    "\u266a Stories in every rhyme \u266a",
                    "\u266a The village voice rings clear \u266a",
                    "\u266a For all who gather here \u266a"
                }, 1.5f),
            new MusicianSong("Aria of Plenty",
                SoundEvents.NOTE_BLOCK_BELL.value(),
                new String[]{
                    "\u266a Abundance flows from song \u266a",
                    "\u266a Where voices grow strong \u266a",
                    "\u266a Blessings on the land bestowed \u266a",
                    "\u266a Through every note that's sowed \u266a"
                }, 1.8f)
        };
        return songs[getRandom().nextInt(songs.length)];
    }
    
    @Override
    protected void applyMusicianBuffs(float intensity, float synergyMultiplier) {
        float singerPower = 1.0f * synergyMultiplier;
        
        // Buff villagers with saturation (hunger reduction)
        List<Villager> nearbyVillagers = getNearbyVillagers(MAX_AUDIENCE_RANGE);
        for (Villager villager : nearbyVillagers) {
            // Minor saturation
            villager.addEffect(new MobEffectInstance(
                MobEffects.SATURATION, 
                100, // 5 seconds
                0, 
                false, 
                false, 
                true
            ));
            
            // Minor health regen
            if (villager.getHealth() < villager.getMaxHealth()) {
                villager.heal(0.5f * singerPower * intensity);
            }
        }
        
        // Buff players with XP orbs and saturation
        List<Player> nearbyPlayers = getNearbyPlayers(MAX_AUDIENCE_RANGE);
        for (Player player : nearbyPlayers) {
            // Saturation
            player.addEffect(new MobEffectInstance(
                MobEffects.SATURATION,
                100, // 5 seconds
                0,
                false,
                false,
                true
            ));
            
            // Chance for XP inspiration based on synergy
            if (getRandom().nextFloat() < 0.3f * synergyMultiplier) {
                player.giveExperiencePoints(2);
                if (getRandom().nextFloat() < 0.1f) {
                    sendMessageToPlayer(player, "\u00a7d\u2726 The singer's voice fills you with inspiration! \u2726");
                }
            }
        }
    }
    
    @Override
    protected void spawnPerformanceParticles() {
        // Singer particles - music notes and heart particles
        if (villager.level().isClientSide()) return;
        
        float synergy = calculateSynergyMultiplier();
        int particleCount = synergy >= ENSEMBLE_MULTIPLIER - 0.1f ? 6 : 
                           synergy >= TRIO_MULTIPLIER - 0.1f ? 5 :
                           synergy >= DUET_MULTIPLIER - 0.1f ? 4 : 3;
        
        for (int i = 0; i < particleCount; i++) {
            double offsetX = (getRandom().nextDouble() - 0.5) * 2.0;
            double offsetY = getRandom().nextDouble() * 2.0;
            double offsetZ = (getRandom().nextDouble() - 0.5) * 2.0;
            
            // Note particles
            villager.level().sendParticles(
                ParticleTypes.NOTE,
                villager.getX() + offsetX,
                villager.getY() + offsetY + 1.5,
                villager.getZ() + offsetZ,
                1, 0, 0, 0, 0
            );
        }
        
        // Heart particles occasionally
        if (getRandom().nextFloat() < 0.1f * synergy) {
            villager.level().sendParticles(
                ParticleTypes.HEART,
                villager.getX(),
                villager.getY() + 2.0,
                villager.getZ(),
                3, 0.5, 0.5, 0.5, 0
            );
        }
    }
    
    @Override
    public void start() {
        super.start();
        // Register with ensemble manager
        performanceId = UUID.randomUUID();
        EnsembleManager.registerPerformance(
            performanceId,
            villager.blockPosition(),
            villager.level().getGameTime(),
            MAX_AUDIENCE_RANGE,
            MusicianType.SINGER,
            currentSong.getIntensity()
        );
    }
    
    @Override
    public void stop() {
        super.stop();
        // Unregister with ensemble manager
        if (performanceId != null) {
            EnsembleManager.endPerformance(performanceId);
        }
    }
}

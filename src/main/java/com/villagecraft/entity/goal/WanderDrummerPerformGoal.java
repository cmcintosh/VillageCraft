package com.villagecraft.entity.goal;

import java.util.EnumSet;
import java.util.List;

import com.villagecraft.VillageCraft;
import com.villagecraft.init.ModVillagerProfessions;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;

/**
 * Goal for Drummer villagers to perform with rhythmic buffs.
 * Drummers provide speed and haste effects.
 * Part of the Musical Ensemble system.
 *
 * NeoForge 1.20.2
 */
public class WanderDrummerPerformGoal extends VillagerGoalBase {

    private boolean playing = false;
    private int performanceTicks = 0;
    private int cooldownTicks = 0;
    private int beatIndex = 0;
    private int nextBeatTick = 0;

    private static final int PERFORMANCE_DURATION = 300;
    private static final int COOLDOWN_DURATION = 600;
    private static final int MAX_AUDIENCE_RANGE = 24;
    private static final int MIN_AUDIENCE = 1;

    private static final String[][] BEATS = {
        {"\u25cf *Boom* *Boom* *tap* *tap* \u25cf", "\u25cf *Boom-boom* *tap* \u25cf", "\u25cf *Rat-a-tat* *BOOM* \u25cf"},
        {"\u25cf *Thump* *thump* *crash* \u25cf", "\u25cf *Rolling thunder* \u25cf", "\u25cf *Power fill* \u25cf"},
        {"\u25cf *Steady march* *left-right* \u25cf", "\u25cf *Building tempo* \u25cf", "\u25cf *Peak energy* \u25cf"}
    };

    private String[] currentBeat;

    public WanderDrummerPerformGoal(Villager villager) {
        super(villager);
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (cooldownTicks > 0) {
            cooldownTicks--;
            return false;
        }

        if (villager.getVillagerData().getProfession() != ModVillagerProfessions.DRUMMER.get()) {
            return false;
        }

        int nearbyAudience = countNearbyAudience();
        return nearbyAudience >= MIN_AUDIENCE && villager.getRandom().nextInt(100) < 6;
    }

    @Override
    public boolean canContinueToUse() {
        if (!playing) return false;
        return performanceTicks < PERFORMANCE_DURATION && countNearbyAudience() >= 1;
    }

    @Override
    public void start() {
        super.start();
        this.playing = true;
        this.performanceTicks = 0;
        this.beatIndex = 0;
        this.nextBeatTick = 40;
        this.currentBeat = BEATS[villager.getRandom().nextInt(BEATS.length)];

        VillageCraft.LOGGER.debug("Drummer {} started performing", villager.getUUID());

        announceToAudience(Component.literal("\u00a76[Drummer] \u00a7fThe beat begins to pulse..."));

        villager.level().playSound(null, villager.getX(), villager.getY(), villager.getZ(),
            SoundEvents.NOTE_BLOCK_BASEDRUM.value(), SoundSource.NEUTRAL, 1.2f, 1.0f);
        
        villager.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (!playing) return;

        performanceTicks++;

        if (performanceTicks >= nextBeatTick && beatIndex < currentBeat.length) {
            announceToAudience(Component.literal("\u00a76[Drummer] \u00a7f" + currentBeat[beatIndex]));
            beatIndex++;
            nextBeatTick += 40;

            playDrumSound();
        }

        if (performanceTicks % 40 == 0) {
            applyDrummerBuffs();
        }

        if (performanceTicks % 4 == 0) {
            spawnDrummerParticles();
        }

        lookAtNearestAudience();
    }

    @Override
    public void stop() {
        if (playing) {
            announceToAudience(Component.literal("\u00a76[Drummer] \u00a7fThe rhythm fades to stillness..."));
        }
        this.playing = false;
        this.cooldownTicks = COOLDOWN_DURATION;
        VillageCraft.LOGGER.debug("Drummer finished performance");
    }

    private void playDrumSound() {
        if (villager.level().isClientSide()) return;

        float pitch = 0.8f + villager.getRandom().nextFloat() * 0.4f;
        var sound = villager.getRandom().nextBoolean() ? 
            SoundEvents.NOTE_BLOCK_SNARE.value() : SoundEvents.NOTE_BLOCK_BASEDRUM.value();

        villager.level().playSound(null, villager.getX(), villager.getY(), villager.getZ(),
            sound, SoundSource.NEUTRAL, 0.8f, pitch);
    }

    private void applyDrummerBuffs() {
        if (villager.level().isClientSide()) return;

        int musicianCount = countNearbyMusicians();
        float synergyMultiplier = calculateSynergyMultiplier(musicianCount);

        List<Villager> villagers = villager.level().getEntitiesOfClass(Villager.class,
            villager.getBoundingBox().inflate(MAX_AUDIENCE_RANGE), v -> v != villager);

        for (Villager audience : villagers) {
            audience.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,
                100, (int)(synergyMultiplier * 0.5), false, false, true));

            if (musicianCount >= 2) {
                audience.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,
                    60, 0, false, false, true));
            }
        }

        List<Player> players = villager.level().getEntitiesOfClass(Player.class,
            villager.getBoundingBox().inflate(MAX_AUDIENCE_RANGE), p -> true);

        for (Player player : players) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,
                100, (int)(synergyMultiplier * 0.5), false, false, true));

            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,
                100, (int)(synergyMultiplier * 0.5) - 1, false, false, true));

            if (musicianCount >= 3) {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,
                    60, 0, false, false, true));
            }

            if (player.getRandom().nextFloat() < 0.05f * musicianCount) {
                player.sendSystemMessage(Component.literal(
                    "\u00a76\u25cf The " + getEnsembleTierName(musicianCount) + " energizes you! \u25cf"
                ));
            }
        }
    }

    private void spawnDrummerParticles() {
        if (villager.level().isClientSide()) return;

        int musicianCount = countNearbyMusicians();

        for (int i = 0; i < 3 + musicianCount; i++) {
            double x = villager.getX() + (villager.getRandom().nextDouble() - 0.5) * 1.0;
            double y = villager.getY() + 0.5;
            double z = villager.getZ() + (villager.getRandom().nextDouble() - 0.5) * 1.0;

            villager.level().addParticle(
                musicianCount >= 4 ? ParticleTypes.CAMPFIRE_COSY_SMOKE : ParticleTypes.CLOUD,
                x, y, z, 0.0, 0.1, 0.0
            );
        }

        if (musicianCount >= 3 && villager.getRandom().nextFloat() < 0.2) {
            villager.level().addParticle(ParticleTypes.FLAME,
                villager.getX(), villager.getY() + 1.0, villager.getZ(),
                0.0, 0.05, 0.0);
        }
    }

    private int countNearbyAudience() {
        if (villager.level().isClientSide()) return 0;

        int count = 0;
        List<Villager> villagers = villager.level().getEntitiesOfClass(Villager.class,
            villager.getBoundingBox().inflate(MAX_AUDIENCE_RANGE), v -> v != villager);
        count += villagers.size();

        List<Player> players = villager.level().getEntitiesOfClass(Player.class,
            villager.getBoundingBox().inflate(MAX_AUDIENCE_RANGE), p -> true);
        count += players.size();

        return count;
    }

    private int countNearbyMusicians() {
        if (villager.level().isClientSide()) return 0;

        int count = 1;
        List<Villager> nearby = villager.level().getEntitiesOfClass(Villager.class,
            villager.getBoundingBox().inflate(32.0), v -> {
                if (v == villager) return false;
                String prof = v.getVillagerData().getProfession().toString().toLowerCase();
                return prof.contains("bard") || prof.contains("singer") ||
                       prof.contains("drummer") || prof.contains("bassist");
            });
        return count + nearby.size();
    }

    private float calculateSynergyMultiplier(int count) {
        if (count >= 4) return 2.0f;
        if (count >= 3) return 1.5f;
        if (count >= 2) return 1.25f;
        return 1.0f;
    }

    private String getEnsembleTierName(int count) {
        if (count >= 4) return "ensemble";
        if (count >= 3) return "trio";
        if (count >= 2) return "duet";
        return "solo";
    }

    private void announceToAudience(Component message) {
        if (villager.level().isClientSide()) return;

        List<Player> players = villager.level().getEntitiesOfClass(Player.class,
            villager.getBoundingBox().inflate(MAX_AUDIENCE_RANGE * 2), p -> true);

        for (Player player : players) {
            player.sendSystemMessage(message);
        }
    }

    private void lookAtNearestAudience() {
        List<Villager> villagers = villager.level().getEntitiesOfClass(Villager.class,
            villager.getBoundingBox().inflate(MAX_AUDIENCE_RANGE), v -> v != villager);

        if (!villagers.isEmpty()) {
            Villager nearest = villagers.get(0);
            double nearestDist = villager.distanceToSqr(nearest);

            for (Villager v : villagers) {
                double dist = villager.distanceToSqr(v);
                if (dist < nearestDist) {
                    nearestDist = dist;
                    nearest = v;
                }
            }

            villager.getLookControl().setLookAt(nearest, 30.0f, 30.0f);
        }
    }
}

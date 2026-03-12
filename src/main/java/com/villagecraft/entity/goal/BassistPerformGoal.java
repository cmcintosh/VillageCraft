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
 * Goal for Bassist villagers to perform with deep resonance buffs.
 * Bassists provide resistance, absorption, and knockback immunity.
 * Part of the Musical Ensemble system.
 *
 * NeoForge 1.20.2
 */
public class BassistPerformGoal extends VillagerGoalBase {

    private boolean playing = false;
    private int performanceTicks = 0;
    private int cooldownTicks = 0;
    private int lineIndex = 0;
    private int nextLineTick = 0;

    private static final int PERFORMANCE_DURATION = 300;
    private static final int COOLDOWN_DURATION = 600;
    private static final int MAX_AUDIENCE_RANGE = 22;
    private static final int MIN_AUDIENCE = 1;

    private static final String[][] BASS_LINES = {
        {"\u266b *Thrum... thrum...* \u266b", "\u266b *Deep vibrations* \u266b", "\u266b *Ground shaking* \u266b"},
        {"\u266b *Boom... boom... boom...* \u266b", "\u266b *Steady foundation* \u266b", "\u266b *Solid base* \u266b"},
        {"\u266b *Low and strong* \u266b", "\u266b *Holding steady* \u266b", "\u266b *Foundation true* \u266b"}
    };

    private String[] currentLine;

    public BassistPerformGoal(Villager villager) {
        super(villager);
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (cooldownTicks > 0) {
            cooldownTicks--;
            return false;
        }

        if (villager.getVillagerData().getProfession() != ModVillagerProfessions.BASSIST.get()) {
            return false;
        }

        int nearbyAudience = countNearbyAudience();
        return nearbyAudience >= MIN_AUDIENCE && villager.getRandom().nextInt(100) < 5;
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
        this.lineIndex = 0;
        this.nextLineTick = 70;
        this.currentLine = BASS_LINES[villager.getRandom().nextInt(BASS_LINES.length)];

        VillageCraft.LOGGER.debug("Bassist {} started performing", villager.getUUID());

        announceToAudience(Component.literal("\u00a79[Bassist] \u00a7fDeep tones begin to resonate..."));

        villager.level().playSound(null, villager.getX(), villager.getY(), villager.getZ(),
            SoundEvents.NOTE_BLOCK_BASS.value(), SoundSource.NEUTRAL, 1.0f, 0.7f);
        
        villager.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (!playing) return;

        performanceTicks++;

        if (performanceTicks >= nextLineTick && lineIndex < currentLine.length) {
            announceToAudience(Component.literal("\u00a79[Bassist] \u00a7f" + currentLine[lineIndex]));
            lineIndex++;
            nextLineTick += 70;

            playBassSound();
        }

        if (performanceTicks % 40 == 0) {
            applyBassistBuffs();
        }

        if (performanceTicks % 8 == 0) {
            spawnBassistParticles();
        }

        lookAtNearestAudience();
    }

    @Override
    public void stop() {
        if (playing) {
            announceToAudience(Component.literal("\u00a79[Bassist] \u00a7fThe deep tones fade to silence..."));
        }
        this.playing = false;
        this.cooldownTicks = COOLDOWN_DURATION;
        VillageCraft.LOGGER.debug("Bassist finished performance");
    }

    private void playBassSound() {
        if (villager.level().isClientSide()) return;

        float pitch = 0.5f + villager.getRandom().nextFloat() * 0.2f;
        villager.level().playSound(null, villager.getX(), villager.getY(), villager.getZ(),
            SoundEvents.NOTE_BLOCK_BASS.value(), SoundSource.NEUTRAL, 1.0f, pitch);
    }

    private void applyBassistBuffs() {
        if (villager.level().isClientSide()) return;

        int musicianCount = countNearbyMusicians();
        float synergyMultiplier = calculateSynergyMultiplier(musicianCount);

        List<Villager> villagers = villager.level().getEntitiesOfClass(Villager.class,
            villager.getBoundingBox().inflate(MAX_AUDIENCE_RANGE), v -> v != villager);

        for (Villager audience : villagers) {
            audience.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,
                100, (int)(synergyMultiplier * 0.5), false, false, true));

            if (musicianCount >= 2) {
                audience.addEffect(new MobEffectInstance(MobEffects.REGENERATION,
                    60, 0, false, false, true));
            }
        }

        List<Player> players = villager.level().getEntitiesOfClass(Player.class,
            villager.getBoundingBox().inflate(MAX_AUDIENCE_RANGE), p -> true);

        for (Player player : players) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,
                100, (int)(synergyMultiplier * 0.5), false, false, true));

            if (musicianCount >= 2) {
                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION,
                    100, 0, false, false, true));
            }

            if (musicianCount >= 3) {
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE,
                    200, 0, false, false, true));
            }

            if (player.getRandom().nextFloat() < 0.05f * musicianCount) {
                player.sendSystemMessage(Component.literal(
                    "\u00a79\u266b The " + getEnsembleTierName(musicianCount) + " fortifies you! \u266b"
                ));
            }
        }
    }

    private void spawnBassistParticles() {
        if (villager.level().isClientSide()) return;

        int musicianCount = countNearbyMusicians();

        for (int i = 0; i < 2 + musicianCount; i++) {
            double x = villager.getX() + (villager.getRandom().nextDouble() - 0.5) * 1.2;
            double y = villager.getY() + villager.getRandom().nextDouble() * 0.5;
            double z = villager.getZ() + (villager.getRandom().nextDouble() - 0.5) * 1.2;

            villager.level().addParticle(ParticleTypes.GLOW, x, y, z, 0.0, 0.02, 0.0);
        }

        if (musicianCount >= 2) {
            villager.level().addParticle(ParticleTypes.GLOW_SQUID_INK,
                villager.getX(), villager.getY() + 1.0, villager.getZ(),
                0.0, 0.05, 0.0);
        }

        if (musicianCount >= 4 && villager.getRandom().nextFloat() < 0.15) {
            villager.level().addParticle(ParticleTypes.TOTEM_OF_UNDYING,
                villager.getX(), villager.getY() + 1.5, villager.getZ(),
                0.0, 0.1, 0.0);
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

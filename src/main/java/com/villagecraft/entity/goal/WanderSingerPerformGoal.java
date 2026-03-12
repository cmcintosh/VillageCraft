package com.villagecraft.entity.goal;

import java.util.EnumSet;
import java.util.List;

import com.villagecraft.VillageCraft;
import com.villagecraft.init.ModVillagerProfessions;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;

/**
 * Goal for Singer villagers to perform songs with vocal buffs.
 * Singers provide saturation and XP inspiration.
 * Part of the Musical Ensemble system.
 * 
 * NeoForge 1.20.2
 */
public class WanderSingerPerformGoal extends VillagerGoalBase {

    protected boolean playing = false;
    protected int performanceTicks = 0;
    protected int cooldownTicks = 0;
    protected int songLineIndex = 0;
    protected int nextLyricTick = 0;

    private static final int PERFORMANCE_DURATION = 300;
    private static final int COOLDOWN_DURATION = 600;
    private static final int MAX_AUDIENCE_RANGE = 20;
    private static final int MIN_AUDIENCE = 1;

    private static final String[][] SONGS = {
        {"\u266a O voices raised in harmony \u266a", "\u266a Sing of village unity \u266a", "\u266a Hearts and minds in sync as one \u266a", "\u266a Until the day is done \u266a"},
        {"\u266a A song of plenty, sweet and clear \u266a", "\u266a For all gathered here \u266a", "\u266a Inspiration fills the air \u266a", "\u266a Banishing all care \u266a"},
        {"\u266a The village chorus strong and true \u266a", "\u266a We sing for me and you \u266a", "\u266a Our voices lift the spirit high \u266a", "\u266a Reaching for the sky \u266a"}
    };

    private String[] currentSong;

    public WanderSingerPerformGoal(Villager villager) {
        super(villager);
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (cooldownTicks > 0) {
            cooldownTicks--;
            return false;
        }

        // Only singers perform
        if (villager.getVillagerData().getProfession() != ModVillagerProfessions.SINGER.get()) {
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
        this.songLineIndex = 0;
        this.nextLyricTick = 60;
        this.currentSong = SONGS[villager.getRandom().nextInt(SONGS.length)];

        VillageCraft.LOGGER.debug("Singer {} started performing at {}", villager.getUUID(), villager.blockPosition());

        announceToAudience(Component.literal("\u00a7d[Singer] \u00a7fA beautiful voice rises in song..."));

        villager.level().playSound(null, villager.getX(), villager.getY(), villager.getZ(),
            SoundEvents.VILLAGER_CELEBRATE, SoundSource.NEUTRAL, 1.0f, 1.0f);
        
        villager.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (!playing) return;

        performanceTicks++;

        if (performanceTicks >= nextLyricTick && songLineIndex < currentSong.length) {
            announceToAudience(Component.literal("\u00a7d[Singer] \u00a7f" + currentSong[songLineIndex]));
            songLineIndex++;
            nextLyricTick += 60;
        }

        if (performanceTicks % 40 == 0) {
            applySingerBuffs();
        }

        if (performanceTicks % 5 == 0) {
            spawnSingerParticles();
        }

        lookAtNearestAudience();
    }

    @Override
    public void stop() {
        if (playing) {
            announceToAudience(Component.literal("\u00a7d[Singer] \u00a7fThe song fades into a gentle hum..."));
        }
        this.playing = false;
        this.cooldownTicks = COOLDOWN_DURATION;
        VillageCraft.LOGGER.debug("Singer finished performance");
    }

    private void applySingerBuffs() {
        if (villager.level().isClientSide()) return;

        int musicianCount = countNearbyMusicians();
        float synergyMultiplier = calculateSynergyMultiplier(musicianCount);

        List<Villager> villagers = villager.level().getEntitiesOfClass(Villager.class,
            villager.getBoundingBox().inflate(MAX_AUDIENCE_RANGE), v -> v != villager);

        for (Villager audience : villagers) {
            audience.heal(0.5f * synergyMultiplier);
            if (musicianCount >= 2) {
                audience.heal(0.3f * synergyMultiplier);
            }
        }

        List<Player> players = villager.level().getEntitiesOfClass(Player.class,
            villager.getBoundingBox().inflate(MAX_AUDIENCE_RANGE), p -> true);

        for (Player player : players) {
            player.giveExperiencePoints(1);

            if (musicianCount >= 2) {
                player.giveExperiencePoints(1);
            }

            if (player.getRandom().nextFloat() < 0.05f * musicianCount) {
                player.sendSystemMessage(Component.literal(
                    "\u00a7d\u266a The " + getEnsembleTierName(musicianCount) + " inspires you! \u266a"
                ));
            }
        }
    }

    private void spawnSingerParticles() {
        if (villager.level().isClientSide()) return;

        int musicianCount = countNearbyMusicians();
        int particleCount = 3 + Math.min(musicianCount - 1, 4);

        for (int i = 0; i < particleCount; i++) {
            double x = villager.getX() + (villager.getRandom().nextDouble() - 0.5) * 0.8;
            double y = villager.getY() + villager.getEyeHeight() + (villager.getRandom().nextDouble() - 0.3);
            double z = villager.getZ() + (villager.getRandom().nextDouble() - 0.5) * 0.8;

            villager.level().addParticle(ParticleTypes.NOTE, x, y, z,
                villager.getRandom().nextFloat(), 0.0, 0.0);
        }

        if (musicianCount >= 3 && villager.getRandom().nextFloat() < 0.2) {
            villager.level().addParticle(ParticleTypes.GLOW, villager.getX(),
                villager.getY() + 2.0, villager.getZ(), 0.0, 0.05, 0.0);
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

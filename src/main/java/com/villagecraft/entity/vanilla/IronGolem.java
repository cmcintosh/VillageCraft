package com.villagecraft.entity.vanilla;

import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nullable;

import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Saddleable;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;

/**
 * Custom Iron Golem entity for VillageCraft.
 * TODO: Complete 1.20.2 migration
 */
public class IronGolem extends net.minecraft.world.entity.animal.IronGolem implements Saddleable {
	
	public IronGolem(EntityType<? extends net.minecraft.world.entity.animal.IronGolem> type, Level worldIn) {
		super(type, worldIn);
	}
	
	public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
            .add(Attributes.MAX_HEALTH, 100.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.25D)
            .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
            .add(Attributes.ATTACK_DAMAGE, 15.0D);
    }
	
	// TODO: Reimplement overrides for 1.20.2 - dropCustomDeathLoot signature changed
	// TODO: Reimplement getDismountLocationForPassenger - signature changed
	// TODO: Reimplement shouldDropExperience - signature changed
	
	@Nullable
	@Override
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
		return null;
	}
	
	@Override
	public boolean isSaddleable() {
		return false;
	}
	
	@Override
	public void equipSaddle(@Nullable SoundSource source) {
		// Saddle implementation
	}
	
	@Override
	public boolean isSaddled() {
		return false;
	}

}
package com.villagecraft.entity.vanilla;

import javax.annotation.Nullable;

import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Saddleable;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.DefaultAttributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;

/**
 * Custom Iron Golem entity for VillageCraft.
 * Uses 1.20.1 compatible API.
 */
public class IronGolem extends net.minecraft.world.entity.animal.IronGolem implements Saddleable {
	
    private static final Ingredient TEMPTATION_ITEMS = Ingredient.of(Items.CARROT, Items.POTATO, Items.BEETROOT);
	
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
	
	@Override
	protected void dropCustomDeathLoot() {
		super.dropCustomDeathLoot();
	}
	
	protected boolean shouldDropExperience() {
		return true;
	}

	@Nullable
	@Override
	public AgeableMob getBreedOffspring() {
		return null;
	}

	@Override
	public boolean isSaddled() {
		return false;
	}

	@Override
	public boolean isSaddleable() {
		return false;
	}

	@Override
	public void equipSaddle(SoundSource soundSource) {
		
	}

	/**
	 * For vehicles, the first passenger is generally considered the controller and "drives" the vehicle.
	 */
	@Nullable
	public Player getControllingPassenger() {
		return this.getPassengers().isEmpty() ? null : (Player) this.getPassengers().get(0);
	}
	
	public Vec3 getDismountLocationForPassenger() {
		return super.getDismountLocationForPassenger();
	}
}

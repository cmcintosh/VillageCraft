package com.villagecraft.entity.vanilla;

import javax.annotation.Nullable;


import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.BoostHelper;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IEquipable;
import net.minecraft.entity.IRideable;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class IronGolem extends GolemEntity implements IRideable, IEquipable {
	
    private static final DataParameter<Boolean> SADDLED = EntityDataManager.createKey(PigEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> BOOST_TIME = EntityDataManager.createKey(PigEntity.class, DataSerializers.VARINT);
	private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.CARROT, Items.POTATO, Items.BEETROOT);
	private final BoostHelper field_234214_bx_ = new BoostHelper(this.dataManager, BOOST_TIME, SADDLED);
	private final AttributeModifierManager attributes;
	
	public IronGolem(EntityType<? extends GolemEntity> type, World worldIn) {
		super(type, worldIn);
		AttributeModifierMap.MutableAttribute map = IronGolem.registerAttributes();
		this.attributes = new AttributeModifierManager(GlobalEntityTypeAttributes.getAttributesForEntity(type));
		
		final double baseSpeed = this.attributes.getAttributeValue(Attributes.MOVEMENT_SPEED);
		final double baseHealth = this.attributes.getAttributeValue(Attributes.MAX_HEALTH);
		
		// Multiply base health and base speed by one and a half
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(baseSpeed * 1.5D);
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(baseHealth * 1.5D);
		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(2);
		this.getAttribute(Attributes.ATTACK_SPEED).setBaseValue(128D);
		
	}
	
	public static AttributeModifierMap.MutableAttribute registerAttributes() {
      return AttributeModifierMap.func_233803_a_()
    		  .createMutableAttribute(Attributes.MAX_HEALTH)
    		  .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE)
    		  .createMutableAttribute(Attributes.MOVEMENT_SPEED)
    		  .createMutableAttribute(Attributes.ARMOR)
    		  .createMutableAttribute(Attributes.ARMOR_TOUGHNESS)
    		  .createMutableAttribute(net.minecraftforge.common.ForgeMod.SWIM_SPEED.get())
    		  .createMutableAttribute(net.minecraftforge.common.ForgeMod.NAMETAG_DISTANCE.get())
    		  .createMutableAttribute(net.minecraftforge.common.ForgeMod.ENTITY_GRAVITY.get());
    }
	
	
	
		
	@Override
	public boolean func_230264_L__() {
		// TODO Auto-generated method stub
		return this.isAlive() && !this.isChild();
	}

	@Override
	public void func_230266_a_(SoundCategory p_230266_1_) {
	  this.field_234214_bx_.setSaddledFromBoolean(true);
      if (p_230266_1_ != null) {
         this.world.playMovingSound((PlayerEntity)null, this, SoundEvents.ENTITY_IRON_GOLEM_STEP, p_230266_1_, 0.5F, 1.0F);
      }		
	}

	@Override
	public boolean isHorseSaddled() {
		// TODO Auto-generated method stub
		return this.field_234214_bx_.getSaddled();
	}

	@Override
	public boolean boost() {
		// TODO Auto-generated method stub
		return this.field_234214_bx_.boost(this.getRNG());
	}

	@Override
	public void travelTowards(Vector3d travelVec) {
		super.travel(travelVec);
	}

	@Override
	public float getMountedSpeed() {
		// TODO Auto-generated method stub
		return (float)this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.225F;
	}
	
	/**
	  * For vehicles, the first passenger is generally considered the controller and "drives" the vehicle. For example,
	  * Pigs, Horses, and Boats are generally "steered" by the controlling passenger.
	  */
	 @Nullable
	 public Entity getControllingPassenger() {
	    return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
	 }
	 
	 /**
	    * returns true if all the conditions for steering the entity are met. For pigs, this is true if it is being ridden
	    * by a player and the player is holding a carrot-on-a-stick
	    */
	   public boolean canBeSteered() {
	      Entity entity = this.getControllingPassenger();
	      if (!(entity instanceof PlayerEntity)) {
	         return false;
	      } else {
	         PlayerEntity playerentity = (PlayerEntity)entity;
	         return playerentity.getHeldItemMainhand().getItem() == Items.IRON_SWORD || playerentity.getHeldItemOffhand().getItem() == Items.IRON_SWORD;
	      }
	      
	   }
	   
}

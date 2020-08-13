package com.villagecraft.entity.goal;

import java.util.EnumSet;
import java.util.List;

import com.villagecraft.init.ModVillagerProfessions;
import com.villagecraft.util.Reference;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class HealGolemGoal extends VillagerGoalBase {
	
	public IronGolemEntity golem;

	public HealGolemGoal(VillagerEntity entity) {
		super(entity);
		this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean shouldExecute() {
		
		List<IronGolemEntity> list = this.villager.world.getEntitiesWithinAABB(IronGolemEntity.class, this.villager.getBoundingBox().grow((double)100.0D));
	    if (!list.isEmpty()) {
	       for(IronGolemEntity golem : list) {
	          if (!golem.isInvisible()) {
	              this.golem = golem;
	              if (golem.getHealth() < golem.getMaxHealth()) {		                   
				   this.healGolem();
	              }
	              return true;
	            }
	         }
	      }
		return false;
	}
	
	
	@Override
	public void resetTask()
	{
		super.resetTask();
	}
	
	@Override
	public void tick()
	{
		if (golem.getHealth() < golem.getMaxHealth())
		{
			this.healGolem();
		}
	}
	
	public void healGolem()
	{
		if (villager.getDistance(golem) <= 2.0D)
		{
			villager.swingArm(Hand.MAIN_HAND);
			golem.heal(15.0F);
			villager.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_INGOT));	 
		}
	}
	
}

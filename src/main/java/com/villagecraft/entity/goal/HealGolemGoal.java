package com.villagecraft.entity.goal;

import java.util.EnumSet;
import java.util.List;

import com.villagecraft.init.ModVillagerProfessions;
import com.villagecraft.util.Reference;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.npc.VillagerEntity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.passive.GolemEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class HealGolemGoal extends VillagerGoalBase {
	
	public GolemEntity golem;

	public HealGolemGoal(VillagerEntity entity) {
		super(entity);
		this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean shouldExecute() {
		
		List<GolemEntity> list = this.villager.world.getEntitiesWithinAABB(GolemEntity.class, this.villager.getBoundingBox().grow((double)100.0D));
	    if (!list.isEmpty()) {
	       for(GolemEntity golem : list) {
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

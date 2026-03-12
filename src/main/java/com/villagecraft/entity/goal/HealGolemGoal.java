package com.villagecraft.entity.goal;

import java.util.List;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.InteractionHand;

public class HealGolemGoal extends VillagerGoalBase {
	
	public IronGolem golem;

	public HealGolemGoal(Villager entity) {
		super(entity);
		// TODO: setMutexFlags API changed
		// this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		// TODO: Reimplement golem detection for 1.20.2
		return false;
	}
	
	@Override
	public void stop()
	{
		super.stop();
	}
	
	@Override
	public void tick()
	{
		// TODO: Reimplement healing logic
	}
	
	public void healGolem()
	{
		// TODO: Reimplement healing for 1.20.2
		// - swing() instead of swingArm()
		// - distanceTo() instead of getDistance()
		// - setItemInHand() instead of setItemStackToSlot()
	}
	
}
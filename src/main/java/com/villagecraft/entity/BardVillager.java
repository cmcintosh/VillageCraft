package com.villagecraft.entity;

import javax.annotation.Nullable;

import com.villagecraft.entity.professions.BardProfession;
import com.villagecraft.init.ModVillagerProfessions;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerData;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.world.World;

public class BardVillager extends VillagerEntity {

	
	
	public BardVillager(EntityType<? extends VillagerEntity> type, World worldIn) {
		super(type, worldIn);
		// TODO Auto-generated constructor stub
		this.setVillagerData(new VillagerData(null, ModVillagerProfessions.BARD.get(), 1));
	}
	

}

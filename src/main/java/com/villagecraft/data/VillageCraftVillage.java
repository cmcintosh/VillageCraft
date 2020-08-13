package com.villagecraft.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Stream;

import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.storage.WorldSavedData;

public class VillageCraftVillage extends WorldSavedData implements Serializable {
	
	public VillageCraftVillage(String name) {
		super(name + "_village");
	}
	

	protected String name;
	protected ArrayList<VillagerEntity> villagers; 
		
	/**
	 * Gets the villages name
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the village name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the villagers
	 */
	public Stream<VillagerEntity> getVillagers() {
		return this.villagers.stream();
	}
	
	/**
	 * Sets the villagers.
	 */
	public void setVillagers(ArrayList<VillagerEntity> villagers) {
		this.villagers = villagers;
	}
	
	/**
	 * Adds a villager
	 */
	public void addVillager(VillagerEntity entity) {
		this.villagers.add(entity);
	}
	
	/**
	 * Removes a villager
	 */
	public void removeVillager(VillagerEntity entity) {
		this.villagers.remove(entity);
	}

	@Override
	public void read(CompoundNBT nbt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

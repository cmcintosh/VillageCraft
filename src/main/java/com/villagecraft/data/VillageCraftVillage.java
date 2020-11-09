package com.villagecraft.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Stream;

import com.villagecraft.data.VillageData.HistoryEvent;

import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.storage.WorldSavedData;

public class VillageCraftVillage extends WorldSavedData implements Serializable {
	
	protected UUID id;
	protected String name;
	protected ArrayList<VillagerEntity> villagers; 
	protected VillageCraftNation nation;
	protected ArrayList<HistoryEvent> history;
	protected BlockPos center;
	
	public VillageCraftVillage(String name) {
		super(name + "_village");
		this.id = UUID.randomUUID();
	}
		
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
	 * Returns the UUID of the village
	 * @return
	 */
	public UUID getUUID() {
		return this.id;
	}
	
	/**
	 * Returns current number of villagers in the village.
	 */
	public int population() {
		return this.villagers.size();
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

	/**
	 * Return the calculated number of the village
	 * @return
	 */
	public int getHonor() { 
		return 0;
	}
	
	/**
	 * Return the calculated wealth of the village.
	 */
	public int getWealth() { 
		return 0;
	}
	
	/**
	 * Return the calculated strength of the village.
	 */
	public int getStrength() { 
		return 0;
	}
	
	/**
	 * Returns the calculated border based on level.
	 */
	public int getBorder() {
		return this.getLevel() * 10 + 100;
	}
	
	/**
	 * Returns the interaction zone.
	 */
	public int getInteractionZone() {
		return (this.getBorder() * this.getStrength()) + 100;
	}
	
	/**
	 * Returns the current level.
	 */
	public int getLevel() {
		return 0;
	}
	
	/**
	 * Create Historical Event.
	 */
	public void saveEvent(String title, int value) {
		this.history.add(new HistoryEvent(title, value));
	}
	
	
	/**
	 * @Section World data
	 */	
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

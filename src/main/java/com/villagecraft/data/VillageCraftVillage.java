package com.villagecraft.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Stream;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.saveddata.SavedData;

public class VillageCraftVillage extends SavedData implements Serializable {
	
	public VillageCraftVillage(String name) {
		super();
		this.name = name;
	}

	protected String name;
	protected ArrayList<Villager> villagers;
	
	/**
	 * Gets the village name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the village name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the villagers
	 */
	public Stream<Villager> getVillagers() {
		return this.villagers.stream();
	}
	
	/**
	 * Sets the villagers.
	 */
	public void setVillagers(ArrayList<Villager> villagers) {
		this.villagers = villagers;
	}
	
	/**
	 * Adds a villager
	 */
	public void addVillager(Villager entity) {
		this.villagers.add(entity);
	}
	
	/**
	 * Removes a villager
	 */
	public void removeVillager(Villager entity) {
		this.villagers.remove(entity);
	}

	@Override
	public CompoundTag save(CompoundTag compound) {
		// TODO: Reimplement for 1.20.2
		return compound;
	}
	
	// TODO: Add static load() factory for 1.20.2
	public static VillageCraftVillage load(CompoundTag tag) {
		VillageCraftVillage village = new VillageCraftVillage("<unnamed>");
		return village;
	}
}
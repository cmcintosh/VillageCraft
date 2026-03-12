package com.villagecraft.data;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;

/**
 * VillageCraft World Data Storage
 * Manages all villages and nations in the world.
 * Updated for NeoForge 1.20.2
 */
public class VillageCraftData {

	public static final String DATA_NAME = "VillageCraftData";
	
	public boolean initialized = false;
	public int nextVillageId = 1;
	
	protected HashMap<Integer, VillageCraftVillage> villagesById;
	protected HashMap<String, VillageCraftNation> nationsByName;
	
	public VillageCraftData() {
		this.villagesById = new HashMap<>();
		this.nationsByName = new HashMap<>();
	}
	
	public String getName() {
		return DATA_NAME;
	}
	
	public void initialize() {
		if (!this.initialized) {
			this.initialized = true;
		}
	}
	
	/**
	 * Gets all villages in the world
	 */
	public Iterable<VillageCraftVillage> getAllVillages() {
		return this.villagesById.values();
	}
	
	/**
	 * Gets village by ID
	 */
	public VillageCraftVillage getVillage(int id) {
		return this.villagesById.get(id);
	}
	
	/**
	 * Adds a new village
	 */
	public void addVillage(VillageCraftVillage village) {
		this.villagesById.put(this.nextVillageId++, village);
	}
	
	/**
	 * Generates and returns the next village id.
	 */
	public int getNextVillageId() {
		int id = this.nextVillageId;
		this.nextVillageId++;
		return id;
	}
	
	/**
	 * Save to NBT
	 */
	public CompoundTag save(CompoundTag compound) {
		compound.putBoolean("initialized", this.initialized);
		compound.putInt("nextVillageId", this.nextVillageId);
		
		// Save villages
		CompoundTag villagesTag = new CompoundTag();
		for (Map.Entry<Integer, VillageCraftVillage> entry : this.villagesById.entrySet()) {
			villagesTag.put(String.valueOf(entry.getKey()), entry.getValue().save(new CompoundTag()));
		}
		compound.put("villages", villagesTag);
		
		return compound;
	}
	
	/**
	 * Load from NBT - used during deserialization
	 * For now, use static instance pattern to handle server world data
	 */
	public static VillageCraftData loadFromNBT(CompoundTag tag) {
		VillageCraftData data = new VillageCraftData();
		data.initialized = tag.getBoolean("initialized");
		data.nextVillageId = tag.getInt("nextVillageId");
		
		// Load villages
		if (tag.contains("villages")) {
			CompoundTag villagesTag = tag.getCompound("villages");
			for (String key : villagesTag.getAllKeys()) {
				int id = Integer.parseInt(key);
				VillageCraftVillage village = VillageCraftVillage.load(villagesTag.getCompound(key));
				data.villagesById.put(id, village);
			}
		}
		
		return data;
	}
	
	// Nation methods
	
	public VillageCraftNation getNation(String name) {
		return this.nationsByName.get(name);
	}
	
	public void addNation(VillageCraftNation nation) {
		this.nationsByName.put(nation.getName(), nation);
	}
	
}
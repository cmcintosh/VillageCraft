package com.villagecraft.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

/**
 * VillageCraft World Data Storage
 * Manages all villages and nations in the world.
 */
public class VillageCraftData extends SavedData {

	public static final String DATA_NAME = "VillageCraftData";
	
	public boolean initialized = false;
	public int nextVillageId = 1;
	
	protected Map<Integer, VillageCraftVillage> villagesById;
	protected Map<String, VillageCraftNation> nationsByName;
	
	public VillageCraftData() {
		super();
		this.villagesById = new HashMap<>();
		this.nationsByName = new HashMap<>();
	}
	
	public String getName() {
		return DATA_NAME;
	}
	
	/**
	 * Load data from world storage
	 */
	public static VillageCraftData load(ServerLevel level) {
		// TODO: 1.20.2 computeIfAbsent signature changed - using createIfAbsent pattern
		// return level.getDataStorage().computeIfAbsent(
		// 	TYPE, DATA_NAME
		// );
		// For now, use a static instance
		return new VillageCraftData();
	}
	
	public void initialize() {
		this.initialized = true;
		this.setDirty();
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
		this.setDirty();
	}
	
	/**
	 * Generates and returns the next village id.
	 */
	public int getNextVillageId() {
		int id = this.nextVillageId;
		this.nextVillageId++;
		this.setDirty();
		return id;
	}
	
	@Override
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
	 * Load from NBT
	 */
	public static VillageCraftData load(CompoundTag tag) {
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
	
	// Nation methods - TODO: Full implementation
	
	public VillageCraftNation getNation(String name) {
		return this.nationsByName.get(name);
	}
	
	public void addNation(VillageCraftNation nation) {
		this.nationsByName.put(nation.getName(), nation);
		this.setDirty();
	}
	
}
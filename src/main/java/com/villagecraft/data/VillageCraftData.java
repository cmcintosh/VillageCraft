package com.villagecraft.data;

import java.util.ArrayList;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

/**
 * VillageCraft World Data Storage
 * TODO: Update for 1.20.2 SavedData API changes
 */
public class VillageCraftData extends SavedData {

	public static final String DATA_NAME = "VillageCraftData";
	
	public boolean initialized = false;
	
	public CompoundTag data = new CompoundTag();
	
	protected ArrayList<VillageCraftNation> nations;
	protected ArrayList<VillageCraftVillage> villages;
	
	protected ServerLevel world = null;
	
	public VillageCraftData() {
		// No-arg constructor required in 1.20.2
		super();
	}
	
	public void setWorld(ServerLevel world) {
		if (this.world == null) {
			this.world = world;
		}
	}
	
	public void initialize() {
		this.initialized = true;
		this.setDirty();
	}
	
	public String getName() {
		return DATA_NAME;
	}
	
	public VillageCraftNation getNation(String nation) {
		// TODO: Filter implementation
		return null;
	}
	
	protected VillageCraftNation loadNation(String nation) {
		// TODO: Implement with new SavedData API
		return new VillageCraftNation(nation);
	}
	
	public VillageCraftVillage getVillage(String village) {
		// TODO: Filter implementation
		return null;
	}
	
	protected VillageCraftVillage loadVillage(String village) {
		// TODO: Implement with new SavedData API
		return new VillageCraftVillage(village);
	}
	
	/**
	 * Generates and returns the next village id.
	 */
	public int getNextVillageId() { 
		VillageCraftVillage village = new VillageCraftVillage("<no name>");
		this.villages.add(village);
		this.setDirty();
		return this.villages.size();
	}
	
	@Override
	public CompoundTag save(CompoundTag compound) {
		// 1.20.2: save() instead of write()
		compound.putString("initialized_test", "true");
		return compound;
	}
	
	// TODO: Implement static load() factory method for 1.20.2
	public static VillageCraftData load(CompoundTag tag) {
		VillageCraftData data = new VillageCraftData();
		// Read nation data from tag
		return data;
	}
	
}
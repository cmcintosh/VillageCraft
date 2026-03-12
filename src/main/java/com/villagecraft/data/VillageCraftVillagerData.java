package com.villagecraft.data;

import java.util.ArrayList;
import java.util.EnumSet;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;

public class VillageCraftVillagerData extends SavedData {
	
	protected ArrayList<Skill> skills;
	protected VillageCraftNation nation;
	protected VillageCraftVillage village;
	

	public VillageCraftVillagerData() {
		super();
	}
	
	// TODO: Implement load factory for 1.20.2
	public static VillageCraftVillagerData load(CompoundTag tag) {
		VillageCraftVillagerData data = new VillageCraftVillagerData();
		return data;
	}
	
	@Override
	public CompoundTag save(CompoundTag compound) {
		// 1.20.2: save() method
		// TODO: Reimplement skill saving
		return compound;
	}

}
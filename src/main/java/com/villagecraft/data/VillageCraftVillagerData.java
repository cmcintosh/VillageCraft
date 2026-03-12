package com.villagecraft.data;

import java.util.ArrayList;
import java.util.EnumSet;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.server.level.ServerLevel;

import com.villagecraft.VillageCraft;

/**
 * VillageCraft Villager Data Storage
 * Stores persistent data for individual villagers including skills.
 * Updated for NeoForge 1.20.2
 */
public class VillageCraftVillagerData extends SavedData {
	
	protected ArrayList<Skill> skills;
	protected VillageCraftNation nation;
	protected VillageCraftVillage village;
	
	// Data keys for NBT
	private static final String TAG_SKILLS = "skills";
	private static final String TAG_NATION = "nation";
	private static final String TAG_VILLAGE = "village";

	public VillageCraftVillagerData() {
		super();
		this.skills = new ArrayList<>();
	}
	
	/**
	 * Load data from NBT tag
	 * NeoForge 1.20.2: Static factory method for deserialization
	 */
	public static VillageCraftVillagerData load(CompoundTag tag) {
		VillageCraftVillagerData data = new VillageCraftVillagerData();
		
		// Load skills
		if (tag.contains(TAG_SKILLS)) {
			// Future: Deserialize skill list
			// For now, start with empty skills
			data.skills = new ArrayList<>();
		}
		
		// Nation and village are loaded by reference from world data
		// These are set via setter methods after loading
		
		return data;
	}
	
	/**
	 * Save data to NBT tag
	 */
	@Override
	public CompoundTag save(CompoundTag compound) {
		// Save skills
		CompoundTag skillsTag = new CompoundTag();
		for (int i = 0; i < skills.size(); i++) {
			// Future: Serialize each skill
			// skillsTag.put("skill_" + i, skill.save());
		}
		compound.put(TAG_SKILLS, skillsTag);
		
		// Nation and village references are saved via VillageCraftData
		// This class only stores villager-specific data
		
		return compound;
	}
	
	// Getters and Setters
	
	public ArrayList<Skill> getSkills() {
		return skills;
	}
	
	public void setSkills(ArrayList<Skill> skills) {
		this.skills = skills;
		setDirty();
	}
	
	public void addSkill(Skill skill) {
		this.skills.add(skill);
		setDirty();
	}
	
	public VillageCraftNation getNation() {
		return nation;
	}
	
	public void setNation(VillageCraftNation nation) {
		this.nation = nation;
		setDirty();
	}
	
	public VillageCraftVillage getVillage() {
		return village;
	}
	
	public void setVillage(VillageCraftVillage village) {
		this.village = village;
		setDirty();
	}
}

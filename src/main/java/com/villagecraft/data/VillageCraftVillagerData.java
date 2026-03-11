package com.villagecraft.data;

import java.util.ArrayList;
import java.util.EnumSet;

import com.villagecraft.VillageCraft;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;

public class VillageCraftVillagerData extends SavedData {
	
	protected ArrayList<Skill> skills;
	protected VillageCraftNation nation;
	protected VillageCraftVillage village;
	

	public VillageCraftVillagerData() {
		super("VillagerCraftVillagerData");
	}

	@Override
	public void read(CompoundTag nbt) {
		// load the skills
		this.skills = new ArrayList<Skill>();
		EnumSet.allOf(SkillType.class).forEach(t -> {
			Skill s = new Skill(t, nbt.getInt(t + "_skill"));
			this.skills.add(s);
		});
		
		// Load the village name
		this.nation = VillageCraft.data.getNation(nbt.getString("nation"));
		this.village = this.nation.getVillage(nbt.getString("village"));
		
	}

	@Override
	public CompoundTag write(CompoundTag compound) {
		// TODO Auto-generated method stub
		
		// Save skills
		this.skills.forEach(s -> {
			compound.putInt(s.getType() + "_skill", s.getExp());
		});
		
		// Save Nation name
		compound.putString("nation", this.nation.getName());
		
		// Save Village name.
		compound.putString("village", this.village.getName());
		
		return compound;
	}

}

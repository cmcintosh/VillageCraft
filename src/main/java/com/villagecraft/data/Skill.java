package com.villagecraft.data;

import java.util.ArrayList;

/**
 * Used to track a villagers skill in a profession.
 * This is in turn used to unlock VillageCraft villager levels.
 * 

 */
public class Skill {

	protected SkillType type;
	protected int experience;
	
	public Skill(SkillType t, int experience) { 
		this.type = t;
		this.experience = experience;
	}
	
	public String getType() {
		return this.type.toString();
	}
	
	public int getExp() {
		return this.experience;
	}
	
	public void addExp(int point) {
		this.experience += point;
	}
	
	
}


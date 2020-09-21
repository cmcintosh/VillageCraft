package com.villagecraft.capabilities;

import com.villagecraft.capabilities.IVillagerHunger;;

public class VillagerHungerAttribute implements IVillagerHunger {
	
	private String name = "hunger";
	private String label = "Hunger";
	private int attributeValue = 20;
	
	@Override
	public String getName() { return this.name; }

	@Override
	public String getLabel() { return this.label; }

	@Override
	public int getValue() { return this.attributeValue;	}

	@Override
	public void setValue(int value) { this.attributeValue = value; }

}

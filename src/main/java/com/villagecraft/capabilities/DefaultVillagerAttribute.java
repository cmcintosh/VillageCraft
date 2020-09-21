package com.villagecraft.capabilities;

public class DefaultVillagerAttribute implements IVillagerAttribute {
	
	protected String name = "default";
	protected String label = "Default";
	protected int attributeValue = 0;
	
	@Override
	public String getName() { return this.name; }

	@Override
	public String getLabel() { return this.label; }

	@Override
	public int getValue() { return this.attributeValue;	}

	@Override
	public void setValue(int value) { this.attributeValue = value; }

}

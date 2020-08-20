package com.villagecraft.data;

public class VillageDataType { 
	
	protected String type;
	protected int id = 0;
	
	public VillageDataType(String type) {
		this.type = type;
	}
	
	public int getNextId() { 
		this.id += 1;
		return this.id;
	}
	
	public String getType() {
		return this.type;
	}
	
}

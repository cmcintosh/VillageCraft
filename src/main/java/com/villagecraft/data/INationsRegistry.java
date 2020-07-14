package com.villagecraft.data;

/**
 * Interface for class that is responsible for tracking 
 * World nation data.
 * 
 * @author chris
 * 
 */
public interface INationsRegistry {
	
	/**
	 * Returns the nation object for the provided id.
	 * @param id
	 * @return INation
	 */
	public INation getNation(int id);
	
	/**
	 * Stores the Nation object to the NationsRegistry
	 * @param nation
	 */
	public void saveNation(INation nation);
	
	/**
	 * Returns the next available ID for nations.
	 */
	public int nextNationId();
	
	/**
	 * Returns the next available ID for villages.
	 * @return
	 */
	public int nextVillageId();
	
	/**
	 * returns the next available ID for villagers.
	 */
	public int nextVillagerId();
}

package com.villagecraft.data;

import java.util.ArrayList;

import net.minecraft.entity.player.PlayerEntity;

/**
 * Defines the interface for interacting with the Nation object
 * @author chris
 *
 */
public interface INation {
	
	/**
	 * Returns the nation's name.
	 * @return String
	 */
	public String getName();
	
	/**
	 * Sets the nation's name.
	 */
	public void setName(String name);
	
	/**
	 * Returns the list of member players
	 * @return ArrayList<PlayerEntity>
	 */
	public ArrayList<PlayerEntity> getPlayers();
	
	/**
	 * Returns if the provided player is a member of the nation.
	 * @return boolean
	 */
	public boolean isMember(PlayerEntity player);
	
	/**
	 * Adds a player to a nation.
	 */
	public void addMember(PlayerEntity player);
	
	/**
	 * Returns the karma of a player for a nation.
	 * @return int
	 */
	public int getKarma(PlayerEntity player);
	
	/**
	 * Adds karma points for the player.
	 * @var PlayerEntity
	 * @var int
	 */
	public void addKarma(PlayerEntity player, int karma);
	
	/**
	 * Removes karma points for the player
	 * @var PlayerEntity
	 * @var int
	 */
	public void removeKarma(PlayerEntity player, int karma);
	
	
	/**
	 * Returns Villages that are a part of this nation.
	 * @return ArrayList<IVillage>
	 */
	public ArrayList<IVillage> getVillages();
	 
	/**
	 * Adds a new village to the nation.
	 */
	public void addVillage(IVillage village);
	
	/**
	 * Removes a village from the nation
	 */
	public void removeVillage(IVillage village);
	
	/**
	 * Returns the nation's capital
	 */
	public IVillage getCapital();
	
	/**
	 * Sets the nation's capital
	 */
	public void setCapital(IVillage village);
	
	/**
	 * Returns all villagers that are members of a nation.
	 */
	public IVillager getVillagers();
	
	/**
	 * Adds a villager to a nation.
	 */
	public void addVillager(IVillager villager);
	
	/**
	 * Removes a villager from a nation.
	 */
	public void removeVillager(IVillager villager, boolean kill);

}

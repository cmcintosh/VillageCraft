package com.villagecraft.data;

import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Stream;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.INBTType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;

public class VillageCraftNation extends WorldSavedData implements Serializable {

	protected String name;
	protected ArrayList<String> players; // player names.
	protected ArrayList<Karma> playerKarma; // list of players that are enemies
	protected ArrayList<VillageCraftVillage> villages; // Villages that are members of the nation
	
	
	public VillageCraftNation(String name) {
		super(name + "_nation");
		
	}
	
	/**
	 * @Section Player citizens
	 */
	
		/**
		 * Add a new player to the nation.
		 * @param name
		 */
		public void addPlayer(String name) { 
			players.add(name);
			this.setDirty(true);
		}
		
		/**
		 * Sets the players for the nation.
		 * @param players
		 */
		public void setPlayers(ArrayList<String> players) { 
			this.players = players;
			this.setDirty(true);
		}
		
		/**
		 * Returns the players that are members of this nation.
		 * @return
		 */
		public ArrayList<String> getPlayers() { 
			return this.players;
		}
		
		/**
		 * Returns if the player is a member of the nation or not.
		 */
		public boolean isPlayerMember(String name) { 
			return this.players.contains(name);
		}
		
		/**
		 * Remove a player from the nation.
		 */
		public void removePlayer(String name) {
			this.players.remove(name);
			this.setDirty(true);
		}
	
	/**
	 * @Section Enemy players of the nation
	 */
	 
	 public Karma getPlayerKarma(String player) {
		 Karma karma = (Karma) this.playerKarma.stream().filter(p -> { 
			 return p.getPlayer() == player; 
	  	 });
		 return karma;
	 }
	 
	 /**
	  * Add Karma points for the karma.
	  * @param player
	  * @param point
	  */
	 public void addKarma(String player, int point) { 
		 Karma karma = this.getPlayerKarma(player);
		 karma.addKarma(point);
		 this.setDirty(true);
	 }
	 
	 /**
	  * Remove karma points to the karma.
	  * @param player
	  * @param point
	  */
	 public void removeKarma(String player, int point) {
		 Karma karma = this.getPlayerKarma(player);
		 karma.removeKarma(point);
		 this.setDirty(true);
	 }
	 
	 /**
	  * Get karma points for a player.
	  */
	 public int getKarmag(String player) {
		 Karma karma = this.getPlayerKarma(player);
		 return karma.getKarma();
	 }
	 
	 /**
	  * Return the village
	  */
	 public VillageCraftVillage getVillage(String village) { 
		 VillageCraftVillage v = (VillageCraftVillage) this.villages.stream().filter(va -> { 
			 return (va.getName() == village);
		 });
		 return v;
	 }
	 
	 /**
	  * Returns a stream of the villages.
	  * @return
	  */
	 public Stream<VillageCraftVillage> getVillages() {
		 return this.villages.stream();
	 }
	 
	 /**
	  * Adds a village to our nation.
	  * @param village
	  */
	 public void addVillage(VillageCraftVillage village) { 
		 this.villages.add(village);
		 this.setDirty(true);
	 }
	 
	 /**
	  * Removes a village from our nation.
	  */
	 public void removeVillage(String village) { 
		 VillageCraftVillage v = this.getVillage(village);
		 this.villages.remove(v);
		 this.setDirty(true);
	 }
	 
	 /**
	  * Removes a village from our nation.
	  * @param village
	  */
	 public void removeVillage(VillageCraftVillage village) {
		 this.villages.remove(village);
		 this.setDirty(true);
	 }

	@Override
	public void read(CompoundNBT nbt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		// TODO Auto-generated method stub
		return null;
	}
}

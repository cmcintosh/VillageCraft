package com.villagecraft.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Stream;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;

public class VillageCraftNation extends SavedData implements Serializable {

	protected String name;
	protected ArrayList<String> players;
	protected ArrayList<Karma> playerKarma;
	protected ArrayList<VillageCraftVillage> villages;
	
	
	public VillageCraftNation(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void addPlayer(String name) { 
		players.add(name);
		this.setDirty();
	}
	
	public void setPlayers(ArrayList<String> players) { 
		this.players = players;
		this.setDirty();
	}
	
	public ArrayList<String> getPlayers() { 
		return this.players;
	}
	
	public boolean isPlayerMember(String name) { 
		return this.players.contains(name);
	}
	
	public void removePlayer(String name) {
		this.players.remove(name);
		this.setDirty();
	}
	
	public Karma getPlayerKarma(String player) {
		// TODO: Filter implementation
		return null;
	}
	
	public void addKarma(String player, int point) { 
		Karma karma = this.getPlayerKarma(player);
		if (karma != null) {
			karma.addKarma(point);
			this.setDirty();
		}
	}
	
	public void removeKarma(String player, int point) {
		Karma karma = this.getPlayerKarma(player);
		if (karma != null) {
			karma.removeKarma(point);
			this.setDirty();
		}
	}
	
	public int getKarmag(String player) {
		Karma karma = this.getPlayerKarma(player);
		return karma != null ? karma.getKarma() : 0;
	}
	
	public VillageCraftVillage getVillage(String village) { 
		// TODO: Filter implementation
		return null;
	}
	
	public Stream<VillageCraftVillage> getVillages() {
		return this.villages.stream();
	}
	
	public void addVillage(VillageCraftVillage village) { 
		this.villages.add(village);
		this.setDirty();
	}
	
	public void removeVillage(String village) { 
		VillageCraftVillage v = this.getVillage(village);
		if (v != null) {
			this.villages.remove(v);
			this.setDirty();
		}
	}
	
	public void removeVillage(VillageCraftVillage village) {
		this.villages.remove(village);
		this.setDirty();
	}

	@Override
	public CompoundTag save(CompoundTag compound) {
		// TODO: Reimplement for 1.20.2
		return compound;
	}
	
	// TODO: Add static load() factory for 1.20.2
	public static VillageCraftNation load(CompoundTag tag) {
		VillageCraftNation nation = new VillageCraftNation("<unnamed>");
		return nation;
	}
}
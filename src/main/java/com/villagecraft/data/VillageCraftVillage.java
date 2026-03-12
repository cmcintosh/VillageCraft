package com.villagecraft.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Stream;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;
import net.minecraft.core.UUIDUtil;
import java.util.UUID;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.saveddata.SavedData;

public class VillageCraftVillage extends SavedData implements Serializable {
	
	public VillageCraftVillage(String name) {
		super();
		this.name = name;
		this.villagers = new ArrayList<>();
		this.center = null;
		this.ownerId = null;
	}

	protected String name;
	protected ArrayList<Villager> villagers;
	
	/**
	 * Gets the village name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the village name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the villagers
	 */
	public Stream<Villager> getVillagers() {
		return this.villagers.stream();
	}
	
	/**
	 * Sets the villagers.
	 */
	public void setVillagers(ArrayList<Villager> villagers) {
		this.villagers = villagers;
	}
	
	/**
	 * Adds a villager
	 */
	public void addVillager(Villager entity) {
		this.villagers.add(entity);
	}
	
	/**
	 * Removes a villager
	 */
	public void removeVillager(Villager entity) {
		this.villagers.remove(entity);
	}
	
	// Village center (Town Hall location)
	protected BlockPos center;
	protected UUID ownerId;
	
	/**
	 * Gets the village center (Town Hall location)
	 */
	public BlockPos getCenter() {
		return this.center;
	}
	
	/**
	 * Sets the village center
	 */
	public void setCenter(BlockPos center) {
		this.center = center;
	}
	
	/**
	 * Gets the village owner
	 */
	public UUID getOwner() {
		return this.ownerId;
	}
	
	/**
	 * Sets the village owner
	 */
	public void setOwner(UUID ownerId) {
		this.ownerId = ownerId;
	}

	@Override
	public CompoundTag save(CompoundTag compound) {
		compound.putString("name", this.name);
		
		if (this.center != null) {
			compound.putInt("centerX", this.center.getX());
			compound.putInt("centerY", this.center.getY());
			compound.putInt("centerZ", this.center.getZ());
		}
		
		if (this.ownerId != null) {
			compound.putUUID("owner", this.ownerId);
		}
		
		return compound;
	}
	
	public static VillageCraftVillage load(CompoundTag tag) {
		VillageCraftVillage village = new VillageCraftVillage(tag.getString("name"));
		
		if (tag.contains("centerX")) {
			int x = tag.getInt("centerX");
			int y = tag.getInt("centerY");
			int z = tag.getInt("centerZ");
			village.setCenter(new BlockPos(x, y, z));
		}
		
		if (tag.contains("owner")) {
			village.setOwner(tag.getUUID("owner"));
		}
		
		return village;
	}
}
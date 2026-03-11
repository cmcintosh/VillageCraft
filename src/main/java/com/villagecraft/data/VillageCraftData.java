package com.villagecraft.data;

import java.util.ArrayList;
import java.util.stream.Stream;

import com.villagecraft.VillageCraft;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

/**

 * @author chris
 *
 */
public class VillageCraftData extends SavedData {

	public static final String DATA_NAME = "VillageCraftData";
	
	public boolean initialized = false;
	
	public CompoundTag data = new CompoundTag();
	
	protected ArrayList<VillageCraftNation> nations;
	protected ArrayList<VillageCraftVillage> villages;
	
	protected ServerLevel world = null;
	
	
	public VillageCraftData() {
		super(VillageCraftData.DATA_NAME);
	}
	
	public void setWorld(ServerLevel world) {
		if (this.world == null) {
			this.world = world;
		}
	}
	
	public void initialize() {
		
		this.initialized = true;
	}
	
	public VillageCraftNation getNation(String nation) {
		VillageCraftNation nations;
		return (VillageCraftNation) this.nations.stream().filter(a -> { return a.getName() == nation; });
	}
	
	protected VillageCraftNation loadNation(String nation) { 
		return world.getSavedData().getOrCreate(() -> { return new VillageCraftNation(nation); }, nation +"_nation");
	}
	
	public VillageCraftVillage getVillage(String village) {
		VillageCraftVillage villages;
		return (VillageCraftVillage) this.villages.stream().filter(a -> { return a.getName() == village; });
	}
	
	protected VillageCraftVillage loadVillage(String village) { 
		return world.getSavedData().getOrCreate(() -> { return new VillageCraftVillage(village); }, village +"_village");
	}
	
	/**
	 * Generates an returns the next village id.
	 * This is allows the tile / inventoyr to be generated as well.
	 */
	public int getNextVillageId() { 
		VillageCraftVillage village = new VillageCraftVillage("<no name>");
		this.villages.add(village);
		this.markDirty();
		return this.villages.size();
	}
	
	@Override
	public void read(CompoundTag nbt) {
		
		// Read nation data.
		String nations = nbt.getString("nations");
		this.nations = new ArrayList<VillageCraftNation>();
		if (nations != null) {
			String[] array = nations.split("\\|", -1);
			for (String nation : array) { 
				VillageCraftNation nationData = this.loadNation(nation);
				this.nations.add(nationData);
			}			
		}
		
		String villages = nbt.getString("villages");
		this.villages = new ArrayList<VillageCraftVillage>();
		if (villages != null) { 
			String[] array = villages.split("\\|", -1);
			for (String village : array) { 
				VillageCraftVillage villageData = this.loadVillage(village);
				this.villages.add(villageData);
			}
		}
		
	}

	@Override
	public CompoundTag write(CompoundTag compound) {
		// TODO Auto-generated method stub
		VillageCraft.LOGGER.debug("Writing VillagerCraftData!!!!!!!!!!!!!!!!!!!!!!!!!");
		compound.putString("initialized_test", "true");
		return compound;
	}
	

}

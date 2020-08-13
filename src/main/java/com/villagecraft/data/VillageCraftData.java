package com.villagecraft.data;

import java.util.ArrayList;
import java.util.stream.Stream;

import com.villagecraft.VillageCraft;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;

public class VillageCraftData extends WorldSavedData {

	public static final String DATA_NAME = "VillageCraftData";
	
	public boolean initialized = false;
	
	public CompoundNBT data = new CompoundNBT();
	
	protected ArrayList<VillageCraftNation> nations;
	
	protected ServerWorld world = null;
	
	
	public VillageCraftData() {
		super(VillageCraftData.DATA_NAME);
	}
	
	public void setWorld(ServerWorld world) {
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
	
	@Override
	public void read(CompoundNBT nbt) {
		
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
		
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		// TODO Auto-generated method stub
		VillageCraft.LOGGER.debug("Writing VillagerCraftData!!!!!!!!!!!!!!!!!!!!!!!!!");
		compound.putString("initialized_test", "true");
		return compound;
	}
	

}

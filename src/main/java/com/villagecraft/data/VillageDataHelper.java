package com.villagecraft.data;

import java.util.ArrayList;

import com.villagecraft.VillageCraft;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;

/**
 * Data helper for storing and retrieving 
 * various VillageCraft data.
 * 
 * @author chris
 *
 */

public class VillageDataHelper extends WorldSavedData {
	public static String DATA_NAME = "VillageCraftData_";
	public static ArrayList<VillageDataType> data_types = new ArrayList<VillageDataType>(); 
	protected int id;
	protected String type;
	protected String message;
	
	public VillageDataHelper(int id, String type) { 
		super(VillageDataHelper.DATA_NAME + type + id);
		this.id = id;
		this.type = type;
	}
	
	public static VillageDataHelper tempHelper = null;
	public static VillageDataHelper nextDataEntity(String type) {
		VillageDataHelper.tempHelper = null;
		VillageDataHelper newHelper;
		VillageDataHelper.data_types.forEach((e) -> {
			if (e.getType() == type ) {
				VillageDataHelper.tempHelper = new VillageDataHelper(e.getNextId(), type);
			}
		});
		
		if (tempHelper == null) {
			VillageDataType dataType = new VillageDataType(type);
			VillageDataHelper.data_types.add(dataType);
			VillageDataHelper.tempHelper = new VillageDataHelper(0, type);
		}
		
		return tempHelper;
	}
	
	protected String getDataId() { 
		return "villagecraft_" + this.type + "_" + this.id;
	}
	
	/**
	 * Stores data
	 * @param compound
	 */
	public CompoundNBT write(CompoundNBT compound) {
		return compound; 
	}
	
	/**
	 * 
	 * @param compound
	 */
	public void read(CompoundNBT compound) { }
	
	public void displayInfo(PlayerEntity player) { 
		player.sendMessage(new StringTextComponent("\n\u00A75------------- INFO ------------\n"), player.getUniqueID());
		player.sendMessage(new StringTextComponent(this.message), player.getUniqueID());
	}

	
	
}

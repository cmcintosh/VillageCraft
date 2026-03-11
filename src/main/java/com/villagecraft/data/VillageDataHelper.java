package com.villagecraft.data;

import java.util.ArrayList;

import com.villagecraft.VillageCraft;

import net.minecraft.world.entity.player.Player;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

/**
 * Data helper for storing and retrieving 
 * various VillageCraft data.
 * 
 * @author chris
 *
 */

public class VillageDataHelper extends SavedData {
	public static String DATA_NAME = "VillageCraftData_";
	public static ArrayList<VillageDataType> data_types = new ArrayList<VillageDataType>(); 
	protected int id;
	protected String type;
	protected String message;
	
	public VillageDataHelper(int id, String type) { 
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
	public CompoundTag write(CompoundTag compound) {
		return compound; 
	}
	
	/**
	 * 
	 * @param compound
	 */
	public void read(CompoundTag compound) { }
	
	public void displayInfo(Player player) { 
		player.sendSystemMessage(Component.literal("\n§5------------- INFO ------------\n"));
		player.sendSystemMessage(Component.literal(this.message));
	}

	@Override
	public CompoundTag save(CompoundTag compound) {
		return write(compound);
	}
}

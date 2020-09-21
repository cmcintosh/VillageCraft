package com.villagecraft.capabilities;

import javax.annotation.ParametersAreNonnullByDefault;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public interface IVillagerAttribute {
	
	/**
	 * Get the machine name for the Attribute
	 * @return
	 */
	String getName();
	
	/**
	 * Get the label for the attribute.
	 */
	String getLabel();

	/**
	 * Returns the value of this VillagerAttribute
	 */
	int getValue();
	
	/**
	 * Sets the value of this VillagerAttribute
	 * 
	 * @param VillagerEntity to set the attribute value for
	 * @param value - the value to set the attribute value to
	 */
	void setValue(int value);
	
	/**
	 * Change the Attribute by X value
	 */
	default int increaseValue(int amount) { 
		return amount;
	};
	
	default int decreaseValue(int amount) { 
		return -amount;
	}

	
}



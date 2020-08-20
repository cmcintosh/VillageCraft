package com.villagecraft.init;

import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class ModMaterials {
	
	/**
	 * Colors
	 */
	
	public static final Material BEER = (new Material.Builder(MaterialColor.GOLD)).doesNotBlockMovement().notSolid().replaceable().liquid().build();
}

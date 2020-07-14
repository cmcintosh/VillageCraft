package com.villagecraft.datagen;

import com.villagecraft.util.Reference;

import net.minecraft.data.BlockStateProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.common.model.Models;

public class BlockStates extends BlockStateProvider {

	public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen);
	}
	
	@Override
	public void act(DirectoryCache cache) { 
		super.act(cache);
		// Register states for our Chair
		registerChairBlock(cache);
	}
	
	/**
	 * Registers the Chair block states
	 * @param cache
	 */
	private void registerChairBlock(DirectoryCache cache) { 
		
		ResourceLocation txt = new ResourceLocation(Reference.MODID, "block/chair");
		
	}

}

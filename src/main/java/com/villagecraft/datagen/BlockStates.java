package com.villagecraft.datagen;

import com.villagecraft.util.Reference;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BlockStates extends BlockStateProvider {

	public BlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
		super(output, Reference.MODID, exFileHelper);
	}
	
	@Override
	protected void registerStatesAndModels() { 
		// TODO: Register block states
	}
}
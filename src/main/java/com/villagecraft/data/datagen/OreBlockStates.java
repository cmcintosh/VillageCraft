package com.villagecraft.data.datagen;


import com.google.common.base.Function;
import com.villagecraft.block.BlockOre;
import com.villagecraft.init.ModOres;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

public class OreBlockStates extends BlockStateProvider {

	
	
    public OreBlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, "vcm", exFileHelper);
		// TODO Auto-generated constructor stub
	}
    
    @Override
	protected void registerStatesAndModels() {
    	for (RegistryObject<Block> entry : ModOres.ORES.getEntries()) {
			this.registerFirstBlock(entry);
	}
    }

    
    private void registerFirstBlock(RegistryObject<Block> entry ) {
    	
    	BlockOre oreBlock = (BlockOre) entry.get();
    	String oreName = oreBlock.getOreName();
    	ResourceLocation txt = new ResourceLocation("vcm:block/ore/" + oreName);
    	
        BlockModelBuilder model = models().cube(oreName, txt, txt, txt, txt, txt, txt);
        
        orientedBlock(entry.get(), state -> {
            return model;
        });
    }
    
    private void orientedBlock(Block block, Function<BlockState, ModelFile> modelFunc) {
        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction dir = state.get(BlockStateProperties.FACING);
                    return ConfiguredModel.builder()
                            .modelFile(modelFunc.apply(state))
                            .rotationX(dir.getAxis() == Direction.Axis.Y ?  dir.getAxisDirection().getOffset() * -90 : 0)
                            .rotationY(dir.getAxis() != Direction.Axis.Y ? ((dir.getHorizontalIndex() + 2) % 4) * 90 : 0)
                            .build();
                });
    }
}

package com.villagecraft.fluid;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.core.BlockPos;
import net.minecraft.world.IBlockDisplayReader;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidAttributes.Water;

public class FluidBeer extends Water {

	protected FluidBeer(Builder builder, Fluid fluid) {
		super(builder, fluid);
		
		
	}
	
	public boolean doesVaporize(IBlockDisplayReader reader, BlockPos pos, FluidStack fluidStack)
    {
     return false;
    }

}

package com.villagecraft.fluid;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.core.BlockPos;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidAttributes.Water;

public class FluidBeer extends Water {

	protected FluidBeer(Builder builder, Fluid fluid) {
		super(builder, fluid);
		
		
	}
	
	public boolean doesVaporize(IBlockDisplayReader reader, BlockPos pos, FluidStack fluidStack)
    {
     return false;
    }

}

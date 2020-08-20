package com.villagecraft.fluid;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.math.BlockPos;
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

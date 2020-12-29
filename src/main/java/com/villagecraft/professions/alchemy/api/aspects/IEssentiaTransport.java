package com.villagecraft.professions.alchemy.api.aspects;

import net.minecraft.util.Direction;

public interface IEssentiaTransport {
	  boolean isConnectable(Direction paramEnumFacing);
	  
	  boolean canInputFrom(Direction paramEnumFacing);
	  
	  boolean canOutputTo(Direction paramEnumFacing);
	  
	  void setSuction(Aspect paramAspect, int paramInt);
	  
	  Aspect getSuctionType(Direction paramEnumFacing);
	  
	  int getSuctionAmount(Direction paramEnumFacing);
	  
	  int takeEssentia(Aspect paramAspect, int paramInt, Direction paramEnumFacing);
	  
	  int addEssentia(Aspect paramAspect, int paramInt, Direction paramEnumFacing);
	  
	  Aspect getEssentiaType(Direction paramEnumFacing);
	  
	  int getEssentiaAmount(Direction paramEnumFacing);
	  
	  int getMinimumSuction();
}

package com.villagecraft.professions.alchemy.api.aspects;

public interface IAspectContainer {
	AspectList getAspects();
	
	void setAspects(AspectList asepects);
	
	int addToContainer(Aspect aspect, int amount);
	
	boolean takeFromContainer(Aspect aspect, int amount);
	
	boolean containerHasAmount(Aspect aspect, int amount);
	
	int containerContainsAspect(Aspect aspect);
	
}

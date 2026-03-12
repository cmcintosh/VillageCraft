package com.villagecraft.capabilities;

import net.minecraft.nbt.CompoundTag;

// TODO: Reimplement for NeoForge 1.20.2 - Capability API changed
public class HonorProvider {
	
	private VillagerHonorAttribute attribute = new VillagerHonorAttribute(); 

	public void invalidate() {
		// TODO: LazyOptional removed - use new API
	}
	
	// TODO: Reimplement getCapability with new API
	public <T> Object getCapability(Object cap, Object side) {
		return null;
	}

	public CompoundTag serializeNBT() {
		// TODO: writeNBT method changed
		return new CompoundTag();
	}

	public void deserializeNBT(CompoundTag nbt) {
		// TODO: readNBT method changed
	}
}
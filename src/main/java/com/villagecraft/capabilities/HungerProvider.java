package com.villagecraft.capabilities;

import net.minecraft.nbt.CompoundTag;

// TODO: Reimplement for NeoForge 1.20.2 - Capability API changed
public class HungerProvider {
	
	private VillagerHungerAttribute attribute = new VillagerHungerAttribute();

	public void invalidate() {
		// TODO: LazyOptional removed - use new API
	}
	
	public <T> Object getCapability(Object cap, Object side) {
		return null;
	}

	public CompoundTag serializeNBT() {
		return new CompoundTag();
	}

	public void deserializeNBT(CompoundTag nbt) {
		// TODO: readNBT method changed
	}
}
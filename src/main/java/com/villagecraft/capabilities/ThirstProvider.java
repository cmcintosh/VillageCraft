package com.villagecraft.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.capabilities.Capability;
import net.neoforged.neoforge.common.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Thirst capability provider for NeoForge 1.20.2
 */
public class ThirstProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
	
	private final VillagerThirstAttribute attribute = new VillagerThirstAttribute();
	private final LazyOptional<IVillagerAttribute> optional = LazyOptional.of(() -> attribute);

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		if (cap == CapabilityVillagerAttribute.VILLAGER_THIRST) {
			return optional.cast();
		}
		return LazyOptional.empty();
	}

	public void invalidate() {
		optional.invalidate();
	}
	
	public IVillagerAttribute getAttribute() {
		return attribute;
	}

	@Override
	public CompoundTag serializeNBT() {
		CompoundTag tag = new CompoundTag();
		tag.putInt("thirst", attribute.getValue());
		return tag;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		if (nbt.contains("thirst")) {
			attribute.setValue(nbt.getInt("thirst"));
		}
	}
}
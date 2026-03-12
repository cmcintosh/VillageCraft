package com.villagecraft.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.capabilities.Capability;
import net.neoforged.neoforge.common.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.common.util.LazyOptional;

import com.villagecraft.util.Reference;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Hunger capability provider for NeoForge 1.20.2
 */
public class HungerProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
	
	private final VillagerHungerAttribute attribute = new VillagerHungerAttribute();
	private final LazyOptional<IVillagerHunger> optional = LazyOptional.of(() -> attribute);

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		if (cap == CapabilityVillagerAttribute.VILLAGER_HUNGER) {
			return optional.cast();
		}
		return LazyOptional.empty();
	}

	public void invalidate() {
		optional.invalidate();
	}
	
	public IVillagerHunger getAttribute() {
		return attribute;
	}

	@Override
	public CompoundTag serializeNBT() {
		CompoundTag tag = new CompoundTag();
		tag.putInt("hunger", attribute.getValue());
		return tag;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		if (nbt.contains("hunger")) {
			attribute.setValue(nbt.getInt("hunger"));
		}
	}
}
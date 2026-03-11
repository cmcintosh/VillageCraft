package com.villagecraft.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.neoforged.neoforge.common.capabilities.Capability;
import net.neoforged.neoforge.common.capabilities.ICapabilitySerializable;
import net.neoforged.neoforge.common.util.LazyOptional;

public class HungerProvider implements ICapabilitySerializable<CompoundTag> {
	
	private VillagerHungerAttribute attribute = new VillagerHungerAttribute();
	private final LazyOptional<IVillagerHunger> attributeOptional = LazyOptional.of( () -> attribute );

	public void invalidate() {
		attributeOptional.invalidate(); 
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return attributeOptional.cast();
	}

	@Override
	public CompoundTag serializeNBT() {
		if (CapabilityVillagerAttribute.VILLAGER_HUNGER == null) {
            return new CompoundTag();
        } else {
            return (CompoundTag) CapabilityVillagerAttribute.VILLAGER_HUNGER.writeNBT(attribute, null);
        }
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		if (CapabilityVillagerAttribute.VILLAGER_HUNGER != null) {
			CapabilityVillagerAttribute.VILLAGER_HUNGER.readNBT(this.attribute, null, nbt);
        }
	}
	

}

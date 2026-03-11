package com.villagecraft.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.neoforged.neoforge.common.capabilities.Capability;
import net.neoforged.neoforge.common.capabilities.ICapabilitySerializable;
import net.neoforged.neoforge.common.util.LazyOptional;

public class HonorProvider implements ICapabilitySerializable<CompoundTag> {
	
	private VillagerHonorAttribute attribute = new VillagerHonorAttribute(); 
	private final LazyOptional<IVillagerHonor> attributeOptional = LazyOptional.of( () -> attribute ); 

	public void invalidate() {
		attributeOptional.invalidate(); 
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return attributeOptional.cast();
	}

	@Override
	public CompoundTag serializeNBT() {
		if (CapabilityVillagerAttribute.VILLAGER_HONOR == null) {
            return new CompoundTag();
        } else {
            return (CompoundTag) CapabilityVillagerAttribute.VILLAGER_HONOR.writeNBT(attribute, null);
        }
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		if (CapabilityVillagerAttribute.VILLAGER_HONOR != null) {
			CapabilityVillagerAttribute.VILLAGER_HONOR.readNBT(this.attribute, null, nbt);
        }
	}
	

}

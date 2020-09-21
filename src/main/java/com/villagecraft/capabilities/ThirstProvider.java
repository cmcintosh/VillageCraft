package com.villagecraft.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class ThirstProvider implements ICapabilitySerializable<CompoundNBT> {
	
	private DefaultVillagerAttribute attribute = new DefaultVillagerAttribute();
	private final LazyOptional<IVillagerAttribute> attributeOptional = LazyOptional.of( () -> attribute );

	public void invalidate() {
		attributeOptional.invalidate(); 
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return attributeOptional.cast();
	}

	@Override
	public CompoundNBT serializeNBT() {
		if (CapabilityVillagerAttribute.VILLAGER_THIRST == null) {
            return new CompoundNBT();
        } else {
            return (CompoundNBT) CapabilityVillagerAttribute.VILLAGER_THIRST.writeNBT(attribute, null);
        }
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		if (CapabilityVillagerAttribute.VILLAGER_THIRST != null) {
			CapabilityVillagerAttribute.VILLAGER_THIRST.readNBT(this.attribute, null, nbt);
        }
	}
	

}

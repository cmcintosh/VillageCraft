package com.villagecraft.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class HungerProvider implements ICapabilitySerializable<CompoundNBT> {
	
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
	public CompoundNBT serializeNBT() {
		if (CapabilityVillagerAttribute.VILLAGER_HUNGER == null) {
            return new CompoundNBT();
        } else {
            return (CompoundNBT) CapabilityVillagerAttribute.VILLAGER_HUNGER.writeNBT(attribute, null);
        }
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		if (CapabilityVillagerAttribute.VILLAGER_HUNGER != null) {
			CapabilityVillagerAttribute.VILLAGER_HUNGER.readNBT(this.attribute, null, nbt);
        }
	}
	

}

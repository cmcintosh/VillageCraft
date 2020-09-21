package com.villagecraft.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class HonorProvider implements ICapabilitySerializable<CompoundNBT> {
	
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
	public CompoundNBT serializeNBT() {
		if (CapabilityVillagerAttribute.VILLAGER_HONOR == null) {
            return new CompoundNBT();
        } else {
            return (CompoundNBT) CapabilityVillagerAttribute.VILLAGER_HONOR.writeNBT(attribute, null);
        }
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		if (CapabilityVillagerAttribute.VILLAGER_HONOR != null) {
			CapabilityVillagerAttribute.VILLAGER_HONOR.readNBT(this.attribute, null, nbt);
        }
	}
	

}

package com.villagecraft.capabilities;



import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.capabilities.AutoRegisterCapability;
import net.neoforged.neoforge.common.util.INBTSerializable;

// TODO: Reimplement Capabilities for NeoForge 1.20.2
// Capabilities API changed significantly - now use DeferredRegister pattern
@AutoRegisterCapability
public class CapabilityVillagerAttribute {
	
	// TODO: Reimplement Capability registration
	// public static Capability<IVillagerHonor> VILLAGER_HONOR;
	// public static Capability<IVillagerHunger> VILLAGER_HUNGER;
	// public static Capability<IVillagerAttribute> VILLAGER_THIRST;
	// public static Capability<IVillagerAttribute> VILLAGER_DESIRE;
	
	
	public static void register() {
		// TODO: Register capabilities using new 1.20.2 API
	}

	
		public static class Storage implements INBTSerializable<CompoundTag> {
			private IVillagerAttribute instance;
			
			public Storage(IVillagerAttribute instance) {
				this.instance = instance;
			}
			
			@Override
			public CompoundTag serializeNBT() {
				CompoundTag tag = new CompoundTag();
				tag.putInt(instance.getName(), instance.getValue());
				return tag;
			}

			@Override
			public void deserializeNBT(CompoundTag nbt) {
				if (nbt.contains(instance.getName())) {
					instance.setValue(nbt.getInt(instance.getName()));
				}
			} 
		}
		
		public static class HungerStorage implements INBTSerializable<CompoundTag> {
			private IVillagerHunger instance;
			
			public HungerStorage(IVillagerHunger instance) {
				this.instance = instance;
			}
			
			@Override
			public CompoundTag serializeNBT() {
				CompoundTag tag = new CompoundTag();
				tag.putInt(instance.getName(), instance.getValue());
				return tag;
			}

			@Override
			public void deserializeNBT(CompoundTag nbt) {
				if (nbt.contains(instance.getName())) {
					instance.setValue(nbt.getInt(instance.getName()));
				}
			} 
		}
		
		public static class HonorStorage implements INBTSerializable<CompoundTag> {
			private IVillagerHonor instance;
			
			public HonorStorage(IVillagerHonor instance) {
				this.instance = instance;
			}
			
			@Override
			public CompoundTag serializeNBT() {
				CompoundTag tag = new CompoundTag();
				tag.putInt(instance.getName(), instance.getValue());
				return tag;
			}

			@Override
			public void deserializeNBT(CompoundTag nbt) {
				if (nbt.contains(instance.getName())) {
					instance.setValue(nbt.getInt(instance.getName()));
				}
			} 
		}
}
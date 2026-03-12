package com.villagecraft.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.capabilities.Capability;
import net.neoforged.neoforge.common.capabilities.CapabilityManager;
import net.neoforged.neoforge.common.capabilities.CapabilityToken;
import net.neoforged.neoforge.common.util.INBTSerializable;

/**
 * Capability registration for VillageCraft attributes.
 * Updated for NeoForge 1.20.2
 */
public class CapabilityVillagerAttribute {
	
	public static final Capability<IVillagerHunger> VILLAGER_HUNGER = CapabilityManager.get(new CapabilityToken<>(){});
	public static final Capability<IVillagerHonor> VILLAGER_HONOR = CapabilityManager.get(new CapabilityToken<>(){});
	public static final Capability<IVillagerAttribute> VILLAGER_THIRST = CapabilityManager.get(new CapabilityToken<>(){});
	
	public static void register() {
		// Capabilities are auto-registered via @AutoRegisterCapability on interfaces
	}
	
	/**
	 * Generic storage implementation for capability persistence
	 */
	public static class Storage implements INBTSerializable<CompoundTag> {
		private final IVillagerAttribute instance;
		
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
		private final IVillagerHunger instance;
		
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
		private final IVillagerHonor instance;
		
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
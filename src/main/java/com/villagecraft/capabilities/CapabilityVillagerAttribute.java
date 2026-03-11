package com.villagecraft.capabilities;



import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.core.Direction;
import net.neoforged.neoforge.common.capabilities.Capability;
import net.neoforged.neoforge.common.capabilities.CapabilityManager;
import net.neoforged.neoforge.common.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class CapabilityVillagerAttribute {
	
	public static Capability<IVillagerHonor> VILLAGER_HONOR = CapabilityManager.get(new Capability.Token<>());
	public static Capability<IVillagerHunger> VILLAGER_HUNGER = CapabilityManager.get(new Capability.Token<>());
	public static Capability<IVillagerAttribute> VILLAGER_THIRST = CapabilityManager.get(new Capability.Token<>());
	public static Capability<IVillagerAttribute> VILLAGER_DESIRE = CapabilityManager.get(new Capability.Token<>());
	
	
	public static void register() {
		// Capabilities are auto-registered in 1.20.2 with @AutoRegisterCapability
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
		
		/**
		 * Creates storage to track villager hunger
		 * @author chris
		 *
		 */
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

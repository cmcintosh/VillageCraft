package com.villagecraft.capabilities;



import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.INBT;
import net.minecraft.core.Direction;
import net.neoforged.neoforge.common.capabilities.Capability;
import net.neoforged.neoforge.common.capabilities.CapabilityInject;
import net.neoforged.neoforge.common.capabilities.CapabilityManager;

public class CapabilityVillagerAttribute {
	
	@CapabilityInject(IVillagerHonor.class)
	public static Capability<IVillagerHonor> VILLAGER_HONOR = null;
	
	@CapabilityInject(IVillagerHunger.class)
	public static Capability<IVillagerHunger> VILLAGER_HUNGER = null;
	
	@CapabilityInject(IVillagerAttribute.class)
	public static Capability<IVillagerAttribute> VILLAGER_THIRST = null;
	
	@CapabilityInject(IVillagerAttribute.class)
	public static Capability<IVillagerAttribute> VILLAGER_DESIRE = null;
	
	
	public static void register() {
		CapabilityManager.INSTANCE.register(IVillagerAttribute.class, new Storage(), DefaultVillagerAttribute::new);
		CapabilityManager.INSTANCE.register(IVillagerHunger.class, new HungerStorage(), VillagerHungerAttribute::new);
		CapabilityManager.INSTANCE.register(IVillagerHonor.class, new HonorStorage(), VillagerHonorAttribute::new);
		
	}

	
		public static class Storage implements Capability.IStorage<IVillagerAttribute> {
			@Override
			public INBT writeNBT(Capability<IVillagerAttribute> capability, IVillagerAttribute instance, Direction side) {
				CompoundTag tag = new CompoundTag();
				tag.putInt(instance.getName(), instance.getValue());
				return tag;
			}

			@Override
			public void readNBT(Capability<IVillagerAttribute> capability, IVillagerAttribute instance, Direction side,
					INBT nbt) {
				int value = ((CompoundTag) nbt).getInt(instance.getName());
				instance.setValue(value);
				
			} 
		}
		
		/**
		 * Creates storage to track villager hunger
		 * @author chris
		 *
		 */
		public static class HungerStorage implements Capability.IStorage<IVillagerHunger> {
			@Override
			public INBT writeNBT(Capability<IVillagerHunger> capability, IVillagerHunger instance, Direction side) {
				CompoundTag tag = new CompoundTag();
				tag.putInt(instance.getName(), instance.getValue());
				return tag;
			}

			@Override
			public void readNBT(Capability<IVillagerHunger> capability, IVillagerHunger instance, Direction side,
					INBT nbt) {
				int value = ((CompoundTag) nbt).getInt(instance.getName());
				instance.setValue(value);
				
			} 
		}
		
		public static class HonorStorage implements Capability.IStorage<IVillagerHonor> {
			@Override
			public INBT writeNBT(Capability<IVillagerHonor> capability, IVillagerHonor instance, Direction side) {
				CompoundTag tag = new CompoundTag();
				tag.putInt(instance.getName(), instance.getValue());
				return tag;
			}

			@Override
			public void readNBT(Capability<IVillagerHonor> capability, IVillagerHonor instance, Direction side,
					INBT nbt) {
				int value = ((CompoundTag) nbt).getInt(instance.getName());
				instance.setValue(value);
				
			} 
		}
}

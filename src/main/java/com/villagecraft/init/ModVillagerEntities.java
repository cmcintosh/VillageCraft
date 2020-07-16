package com.villagecraft.init;

import com.villagecraft.entity.BardVillager;
import com.villagecraft.util.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModVillagerEntities {
	
	public static final DeferredRegister<EntityType<?>> VILLAGERS = DeferredRegister.create(ForgeRegistries.ENTITIES, Reference.MODID);
	
	// Villager Spawn Eggs
	public static final RegistryObject<EntityType<BardVillager>> BARD = VILLAGERS.register("bard", 
			() -> EntityType.Builder.create(BardVillager::new, EntityClassification.CREATURE)
			.size(.5F, .5f)
			.setShouldReceiveVelocityUpdates(true)
			.build("bard")
			
	);
	
}

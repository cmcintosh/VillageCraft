package com.villagecraft.init;

import com.villagecraft.entity.vanilla.VillageIronGolem;
import com.villagecraft.util.Reference;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;

public class ModEntity {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Reference.MODID);

	public static final DeferredHolder<EntityType<?>, EntityType<VillageIronGolem>> GOLEM = ENTITY_TYPES
			.register("iron_golem",
					() -> EntityType.Builder.<VillageIronGolem>of(VillageIronGolem::new, MobCategory.MISC)
					.sized(1.4F, 2.7F)
					.clientTrackingRange(10)
					.build(new ResourceLocation(Reference.MODID, "iron_golem").toString()));
}

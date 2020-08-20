package com.villagecraft.init;

import com.villagecraft.entity.vanilla.IronGolem;
import com.villagecraft.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntity {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Reference.MODID);

	public static final RegistryObject<EntityType<IronGolem>> IRON_GOLEM = ENTITY_TYPES
			.register("iron_golem",
					() -> EntityType.Builder.<IronGolem>create(IronGolem::new, EntityClassification.CREATURE)
					.size(0.9f, 1.3f)
					.build(new ResourceLocation("minecraft:entity/iron_golem").toString()));
}

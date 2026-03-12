package com.villagecraft.init;

import com.villagecraft.util.Reference;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFoods {
	
	//The ITEMS deferred register in which you can register items.
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(net.minecraft.core.registries.Registries.ITEM, Reference.MODID);
		

	@SuppressWarnings("deprecation")
	public static final DeferredHolder<Item, Item> BEER = ITEMS.register("beer",
			() -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1)
					.saturationMod(1.2f).effect(new MobEffectInstance(MobEffects.CONFUSION, 3000, 5), 0.7f).build())));
	
}
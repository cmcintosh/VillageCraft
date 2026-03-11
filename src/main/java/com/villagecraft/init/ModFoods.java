package com.villagecraft.init;

import com.villagecraft.util.Reference;

import net.minecraft.world.item.Food;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemGroup;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.Registries;

public class ModFoods {
	
	//The ITEMS deferred register in which you can register items.
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(net.minecraft.core.registries.Registries.ITEM, Reference.MODID);
		

	@SuppressWarnings("deprecation")
	public static final DeferredHolder<Item, Item> BEER = ITEMS.register("beer",
			() -> new Item(new Item.Properties().group(ItemGroup.BREWING).food(new Food.Builder().hunger(1)
					.saturation(1.2f).effect(new EffectInstance(Effects.NAUSEA, 3000, 5), 0.7f).build())));
	
}

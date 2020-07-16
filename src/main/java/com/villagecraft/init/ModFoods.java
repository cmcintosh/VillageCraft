package com.villagecraft.init;

import com.villagecraft.util.Reference;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFoods {
	
	//The ITEMS deferred register in which you can register items.
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);
		

	@SuppressWarnings("deprecation")
	public static final RegistryObject<Item> BEER = ITEMS.register("beer",
			() -> new Item(new Item.Properties().group(ItemGroup.BREWING).food(new Food.Builder().hunger(1)
					.saturation(1.2f).effect(new EffectInstance(Effects.NAUSEA, 3000, 5), 0.7f).build())));
	
}

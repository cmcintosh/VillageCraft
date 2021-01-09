package com.villagecraft.data.datagen;

import java.util.function.Consumer;

import com.villagecraft.init.ModOres;

import net.minecraft.block.Blocks;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

public class OreSmeltingRecipe extends RecipeProvider {

	public OreSmeltingRecipe(DataGenerator generatorIn) {
		super(generatorIn);
	}
	
	@Override 
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
		
//		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ModOres.ALUMINUM.get()), Items.IRON_INGOT, 0.7F, 200)
//			.addCriterion("has_iron_ore", hasItem(Blocks.IRON_ORE.asItem()))
//			.build(consumer);
	}

}

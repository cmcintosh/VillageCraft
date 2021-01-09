package com.villagecraft.item.recipe;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class AlchemyRecipe implements IAlchemyRecipe {
	@Nonnull private final Ingredient input;
    @Nonnull private final Ingredient ingredient;
    @Nonnull private final ItemStack output;
    private final ResourceLocation recipeId;
    
    public AlchemyRecipe(ResourceLocation recipeId, Ingredient base, Ingredient addition, ItemStack result) {
        this.recipeId = recipeId;
        this.input = base;
        this.ingredient = addition;
        this.output = result;
     }
    
    public Ingredient getInput()
    {
        return input;
    }
    
    public Ingredient getIngredient()
    {
        return ingredient;
    }

	@Override
	public boolean isInput(ItemStack input) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isIngredient(ItemStack ingredient) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean matches(IInventory inv, World worldIn) {
		// TODO Auto-generated method stub
		return this.input.test(inv.getStackInSlot(0)) && this.ingredient.test(inv.getStackInSlot(1));
	}

	@Override
	public ItemStack getCraftingResult(IInventory inv) {
	  ItemStack itemstack = this.output.copy();
      CompoundNBT compoundnbt = inv.getStackInSlot(0).getTag();
      if (compoundnbt != null) {
         itemstack.setTag(compoundnbt.copy());
      }

      return itemstack;
	}

	@Override
	public boolean canFit(int width, int height) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public ItemStack getRecipeOutput() {
		// TODO Auto-generated method stub
		return this.output;
	}

	@Override
	public ResourceLocation getId() {
		// TODO Auto-generated method stub
		return this.recipeId;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		// TODO Auto-generated method stub
		return new AlchemyRecipe.Serializer();
	}

	@Override
	public IRecipeType<AlchemyRecipe> getType() {
		// TODO Auto-generated method stub
		return null;
	}

	public static class Serializer extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<AlchemyRecipe> {
	      public AlchemyRecipe read(ResourceLocation recipeId, JsonObject json) {
	         Ingredient ingredient = Ingredient.deserialize(JSONUtils.getJsonObject(json, "base"));
	         Ingredient ingredient1 = Ingredient.deserialize(JSONUtils.getJsonObject(json, "addition"));
	         ItemStack itemstack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
	         return new AlchemyRecipe(recipeId, ingredient, ingredient1, itemstack);
	      }

	      public AlchemyRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
	         Ingredient ingredient = Ingredient.read(buffer);
	         Ingredient ingredient1 = Ingredient.read(buffer);
	         ItemStack itemstack = buffer.readItemStack();
	         return new AlchemyRecipe(recipeId, ingredient, ingredient1, itemstack);
	      }

	      public void write(PacketBuffer buffer, AlchemyRecipe recipe) {
	         recipe.input.write(buffer);
	         recipe.ingredient.write(buffer);
	         buffer.writeItemStack(recipe.output);
	      }

	   }
}

package com.villagecraft.professions.alchemy.api.crafting;

import java.util.ArrayList;
import java.util.List;

import com.villagecraft.professions.alchemy.api.AlchemyApiHelper;
import com.villagecraft.professions.alchemy.api.aspects.Aspect;
import com.villagecraft.professions.alchemy.api.aspects.AspectList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CrucibleRecipe {
	
	private ItemStack output;
	public Object catalyst;
	public AspectList aspects;
	public String[] research;
	public int hash;
	
	public CrucibleRecipe(String[] researchKey, ItemStack result, Object cat, AspectList tags) {
		this.output = result;
		this.aspects = tags;
		this.research = researchKey;
		this.catalyst = cat;
		
		String hc = "";
		for (String ss : this.research) hc = hc + ss; 
		hc = hc + result.toString();
		for (Aspect tag : tags.getAspects()) {
			hc = hc + tag.getTag() + tags.getAmount(tag);
		}
		if (this.catalyst instanceof ArrayList<?>) {
			for (ItemStack is : (ArrayList<ItemStack>) this.catalyst) {
				hc = hc + is.toString();
			}	
		}
		
		     
		this.hash = hc.hashCode();
	}
		
	public boolean matches(AspectList itags, ItemStack cat) {
		
		if (this.catalyst instanceof ItemStack) {
			if ( !(((ItemStack) this.catalyst).getItem() == cat.getItem()) ) {
				return false;
			}
		}
		if ( !AlchemyApiHelper.containsMatch((ArrayList<ItemStack>) this.catalyst, cat)) {
			return false;
		}
		
		if (itags == null) { return false; }
		
		for (Aspect tag : this.aspects.getAspects()) {
			if (itags.getAmount(tag) < this.aspects.getAmount(tag)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean catalystMatches(ItemStack cat) { 
		if (this.catalyst instanceof ItemStack) {
			if ( ((ItemStack)this.catalyst).getItem() == cat.getItem() ) {
				return true;
			}
		}
		return AlchemyApiHelper.containsMatch(((ArrayList<ItemStack>)this.catalyst), cat);
	}
	
}

package com.villagecraft.professions.alchemy.api.aspects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import com.villagecraft.professions.alchemy.api.AlchemyApiHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class AspectHelper {
	
	public static AspectList cullTags(AspectList temp) {
		AspectList culled = new AspectList();
		for (Aspect tag : temp.getAspects()) {
			if (tag != null)
				culled.add(tag, temp.getAmount(tag));
		}
		while (culled != null && culled.size() > 6) {
			Aspect lowest = null;
			float low = 32767.0F;
			for (Aspect tag : culled.getAspects()) {
				if (tag != null) {
					float ta = culled.getAmount(tag);
					if (tag.isPrimal()) {
						ta *= 0.9F;
					} else {
						if (!tag.getComponents()[0].isPrimal()) {
							ta *= 1.1F;
							if (!tag.getComponents()[0].getComponents()[0].isPrimal()) {
								ta *= 1.05F;
							}
							if (!tag.getComponents()[0].getComponents()[1].isPrimal()) {
								ta *= 1.05F;
							}
						} 
						if (!tag.getComponents()[1].isPrimal()) {
							ta *= 1.1F;
							if (!tag.getComponents()[1].getComponents()[0].isPrimal()) {
								ta *= 1.05F;
							}
							if (!tag.getComponents()[1].getComponents()[1].isPrimal()) {
								ta *= 1.05F;
							}
						} 
					} 
				           
					if (ta < low) {
						low = ta;
						lowest = tag;
					} 
				} 
			}  culled.aspects.remove(lowest);
		}
		
		return culled;
	}
	
	public static AspectList getObjectAspects(ItemStack is) {
		return AlchemyAPI.internalMethods.getObjectAspects(is);
	}
	
	public static AspectList generateTags(Item item, int meta) {
		return AlchemyAPI.internalMethods.generateTags(item, meta);
	}
	
	public static AspectList getEntityAspects(Entity entity) {
		AspectList tags = null;
		if (entity instanceof PlayerEntity) {
			tags = new AspectList();
			tags.add(Aspect.MAN, 4);
			Random rand = new Random(((PlayerEntity)entity).hashCode() );
			Aspect[] posa = (Aspect[])Aspect.aspects.values().toArray((Object[])new Aspect[0]);
			tags.add(posa[rand.nextInt(posa.length)], 4);
			tags.add(posa[rand.nextInt(posa.length)], 4);
			tags.add(posa[rand.nextInt(posa.length)], 4);
		} else {
			label28: for (AlchemyAPI.EntityTags et : AlchemyAPI.scanEntities) {
				if (!et.entityName.equals(EntityList.func_75621_b(entity)))
					continue;  
				if (et.nbts == null || et.nbts.length == 0) {
					tags = et.aspects; continue;
				} 
				CompoundNBT tc = new CompoundNBT();
				entity.func_70109_d(tc); 
				AlchemyAPI.EntityTagsNBT[] arr$; 
				int len$, i$;
				for (arr$ = et.nbts, len$ = arr$.length, i$ = 0; i$ < len$; ) { 
					AlchemyAPI.EntityTagsNBT nbt = arr$[i$];
					if (tc.func_74764_b(nbt.name)) {
						if ( ! AlchemyApiHelper
								.getNBTDataFromId(tc, tc.func_150299_b(nbt.name), nbt.name)
								.equals(nbt.value))
							continue label28;  i$++;
					} 
					continue label28; 
				}         
				tags = et.aspects;
			} 
		} 
		return tags;
	}
		
		public static Aspect getCombinationResult(Aspect aspect1, Aspect aspect2) {
			Collection<Aspect> aspects = Aspect.aspects.values();
			for (Aspect aspect : aspects) {
				if (aspect.getComponents() != null && ((aspect.getComponents()[0] == aspect1 && aspect.getComponents()[1] == aspect2) || (aspect.getComponents()[0] == aspect2 && aspect.getComponents()[1] == aspect1))) {
					return aspect;
				}
			} 
			return null;
		}

		public static Aspect getRandomPrimal(Random rand, Aspect aspect) {
			ArrayList<Aspect> list = new ArrayList<Aspect>();
			if (aspect != null) {
				AspectList temp = new AspectList();
				temp.add(aspect, 1);
				AspectList temp2 = reduceToPrimals(temp);
				for (Aspect a : temp2.getAspects()) {
					for (int b = 0; b < temp2.getAmount(a); b++) {
						list.add(a);
					}
				} 
			} 
		     
			return (list.size() > 0) ? list.get(rand.nextInt(list.size())) : null;
		}
		   
		public static AspectList reduceToPrimals(AspectList in) {
			AspectList out = new AspectList();
			for (Aspect aspect : in.getAspects()) {
				if (aspect != null) {
					if (aspect.isPrimal()) {
						out.add(aspect, in.getAmount(aspect));
					} else {
						AspectList temp = new AspectList();
						temp.add(aspect.getComponents()[0], in.getAmount(aspect));
						temp.add(aspect.getComponents()[1], in.getAmount(aspect));
						AspectList temp2 = reduceToPrimals(temp);
    
						for (Aspect a : temp2.getAspects()) {
							out.add(a, temp2.getAmount(a));
						}
					} 
				}
			} 
			return out;
		}
		   
		public static AspectList getPrimalAspects(AspectList in) {
			AspectList t = new AspectList();
			t.add(Aspect.AIR, in.getAmount(Aspect.AIR));
			t.add(Aspect.FIRE, in.getAmount(Aspect.FIRE));
			t.add(Aspect.WATER, in.getAmount(Aspect.WATER));
			t.add(Aspect.EARTH, in.getAmount(Aspect.EARTH));
			t.add(Aspect.ORDER, in.getAmount(Aspect.ORDER));
			t.add(Aspect.ENTROPY, in.getAmount(Aspect.ENTROPY));
			return t;
		}
		   
		public static AspectList getAuraAspects(AspectList in) {
			AspectList t = new AspectList();
			t.add(Aspect.AIR, in.getAmount(Aspect.AIR));
			t.add(Aspect.FIRE, in.getAmount(Aspect.FIRE));
			t.add(Aspect.WATER, in.getAmount(Aspect.WATER));
			t.add(Aspect.EARTH, in.getAmount(Aspect.EARTH));
			t.add(Aspect.ORDER, in.getAmount(Aspect.ORDER));
			t.add(Aspect.ENTROPY, in.getAmount(Aspect.ENTROPY));
			t.add(Aspect.FLUX, in.getAmount(Aspect.FLUX));
			return t;
		}

}

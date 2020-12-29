package com.villagecraft.professions.alchemy.api.aspects;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.villagecraft.VillageCraft;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

public class AspectList implements Serializable {
	public LinkedHashMap<Aspect, Integer> aspects = new LinkedHashMap<Aspect, Integer>();
	
	public AspectList(ItemStack stack) {
		try {
			AspectList tempList = AspectHelper.getObjectAspects(stack);
			if (tempList != null) {
				for (Aspect tag : tempList.getAspects()) {
					add(tag, tempList.getAmount(tag));
				}
			}
		} catch (Exception e) {
			VillageCraft.LOGGER.error(e);
		}
	}
	
	public AspectList() {}
	
	public AspectList copy() {
		AspectList out = new AspectList();
		for (Aspect a : getAspects()) {
			out.add(a, getAmount(a));
		}
		return out;
	}
	
	public int size() {
		return this.aspects.size();
	}
	
	public int visSize() {
		int q = 0;
		
		for(Aspect as : this.aspects.keySet()) {
			q += getAmount(as);
		}
		return q;
	}
	
	public Aspect[] getAspects() {
		return (Aspect[])this.aspects.keySet().toArray((Object[])new Aspect[0]);
	}
	
	public Aspect[] getAspectsSortedByName() {
		try {
			Aspect[] out = (Aspect[])this.aspects.keySet().toArray((Object[])new Aspect[0]);
			boolean change = false;
			while (true) {
				change = false;
				for (int a = 0; a < out.length; a++) {
					Aspect e1 = out[a];
					Aspect e2 = out[a+1];
					change = true;
					break;
				}
				if (change != true) {
					return out;
				}
			}
		} catch (Exception e) {
			return getAspects();
		}
	}
	
	public Aspect[] getAspectsSortedByAmount() {
		try {
			Aspect[] out = (Aspect[])this.aspects.keySet().toArray((Object[])new Aspect[0]);
			boolean change = false;
			while (true){ 
				change = false;
				for (int a = 0; a < out.length - 1; a++) {
					int e1 = getAmount(out[a]);
					int e2 = getAmount(out[a + 1]);
					if (e1 > 0 && e2 > 0 && e2 > e1) {
						Aspect ea = out[a];
						Aspect eb = out[a + 1];
						out[a] = eb;
						out[a + 1] = ea;
						change = true;
						break;
					} 
				} 
				if (change != true)
					return out;  } 
		} catch (Exception e) {
			return getAspects();
		} 
	}
	
	public int getAmount(Aspect key) {
		return (this.aspects.get(key) == null) ? 0 : ((Integer)this.aspects.get(key)).intValue();
	}
	
	public boolean reduce(Aspect key, int amount) {
		if (getAmount(key) >= amount) {
			int am = getAmount(key) - amount;
			this.aspects.put(key, Integer.valueOf(am));
			return true;
		}
		return false;
	}
	
	public AspectList remove(Aspect key, int amount) {
		int am = getAmount(key) - amount;
		if (am <= 0) { this.aspects.remove(key); }
		else { this.aspects.put(key, Integer.valueOf(am)); }
		return this;
	}
	
	public AspectList remove(Aspect key) {
		this.aspects.remove(key);
		return this;
	}
	
	public AspectList add(Aspect aspect, int amount) {
		if (this.aspects.containsKey(aspect)) {
			int oldamount = ((Integer)this.aspects.get(aspect)).intValue();
			amount += oldamount;
		} 
		this.aspects.put(aspect, Integer.valueOf(amount));
		return this;
	}
	
	public AspectList merge(Aspect aspect, int amount) {
		if (this.aspects.containsKey(aspect)) {
			int oldamount = ((Integer)this.aspects.get(aspect)).intValue();
			if (amount < oldamount) amount = oldamount;
		} 
		this.aspects.put(aspect, Integer.valueOf(amount));
		return this;
	}
	
	public AspectList add(AspectList in) {
		for (Aspect a : in.getAspects())
			add(a, in.getAmount(a)); 
		return this;
	}
	
	public AspectList merge(AspectList in) {
		for (Aspect a : in.getAspects())
			merge(a, in.getAmount(a)); 
		return this;
	}
	
	public void write(CompoundNBT nbt) {
		ListNBT tlist = new ListNBT();
		nbt.put("Aspects", tlist);
		for (Aspect aspect : getAspects()) {
			if (aspect != null) {
				CompoundNBT f = new CompoundNBT();
				f.putString("key", aspect.getTag());
				f.putInt("amount", getAmount(aspect));
				tlist.add(f);
			}
		}
	}
	
	public void write(CompoundNBT nbt, String label) {
		ListNBT tlist = new ListNBT();
		nbt.put(label, tlist);
		for (Aspect aspect : getAspects()) {
			if (aspect != null) {
				CompoundNBT f = new CompoundNBT();
				f.putString("key", aspect.getTag());
				f.putInt("amount", getAmount(aspect));
				tlist.add(f);
			}
		}
	}
	
	public void readFromNBT(CompoundNBT nbt) {
		this.aspects.clear();
		ListNBT tlist = nbt.getList("Aspects", 10);
		for (int j = 0; j < tlist.size(); j++) {
			CompoundNBT t = (CompoundNBT) tlist.get(j);
			add(Aspect.getAspect(t.getString("key")), t.getInt("amount"));
		}
	}
	
}

package com.villagecraft.professions.alchemy.api.aspects;

import java.lang.reflect.Method;

import com.villagecraft.VillageCraft;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;

public class AspectSourceHelper {
	static Method drainEssentia;
	static Method findEssentia;
	
	public static boolean drainEssentia(TileEntity tile, Aspect aspect, Direction direction, int range) {
		try {
			if (drainEssentia == null) {
				Class<?> fake = Class.forName("villagecraft.common.lib.events.EssentiaHandler");
				drainEssentia = fake.getMethod("drainEssentia", new Class[] { TileEntity.class, Aspect.class, Direction.class, int.class});
			}
			return ((Boolean)drainEssentia.invoke(null, new Object[] { tile, aspect, direction, Integer.valueOf(range) })).booleanValue();
		}
		catch (Exception e) {
			VillageCraft.LOGGER.debug("[VillageCraft Alchemy API] Could not invoke villagecraft.common.lib.events.EssentiaHandler method drainEssentia", new Object[0]);
			return false;
		}
	}

}

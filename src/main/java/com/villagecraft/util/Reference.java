package com.villagecraft.util;

import net.minecraft.tileentity.TileEntity;

public class Reference {
	public static final String MODID = "vcm";
	public static final String NAME = "VillageCraft";
	public static final String VERSION= "0.1";
	public static final String ACCEPTED_VERSIONS = "[1.16.1]";
	public static final String CLIENT_PROXY_CLASS = "com.VillageCraft.proxy.ClientProxy";
	public static final String COMMON_PROXY_CLASS = "com.VillageCraft.proxy.CommonProxy";
	public static final String GUIFACTORY = "vcm.ui.GuiFactory";
	
	/**
	 * GUID ids
	 */
	public static final int NATION_CHARTER_GUID = 1;
	
	protected static TileEntity referenceTe;
	
	public static TileEntity getRefrencedTE() {
        return referenceTe;
    }
	
	public static void setRefrencedTE(TileEntity tileEntity) {
		referenceTe = tileEntity;
	}
}

/**
 * 
 */
package com.villagecraft.config;

/**
 * @author chris
 *
 */
public final class ConfigHelper {
	
	public static void bakeClient(final ModConfig config) { 
		VillageCraftConfig.client = ConfigHolder.CLIENT.client.get();

	}
	
	public static void bakeServer(final ModConfig config) { 
		VillageCraftConfig.server = ConfigHolder.SERVER.server.get();

	}


}

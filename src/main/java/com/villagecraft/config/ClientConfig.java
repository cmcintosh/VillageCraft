package com.villagecraft.config;

import java.util.ArrayList;

import com.villagecraft.util.Reference;

import net.minecraftforge.common.ForgeConfigSpec;

final class ClientConfig {

	final ForgeConfigSpec.BooleanValue client;
	final ForgeConfigSpec.ConfigValue<ArrayList<String>> clientList;
	
	
//	final ForgeConfigSpec.BooleanValue modelTranslucency;
//	final ForgeConfigSpec.DoubleValue modelScale;
	
	ClientConfig(final ForgeConfigSpec.Builder builder) {
		builder.push("general");
		client = builder
				.comment("Template for boolean in client config")
				.translation(Reference.MODID + ".config.client")
				.define("client", true);
		clientList = builder
				.comment("An example list of Strings in the client config")
				.translation(Reference.MODID + ".config.clientStringList")
				.define("clientList", new ArrayList<>());
		
	}
}

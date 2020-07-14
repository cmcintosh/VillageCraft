package com.villagecraft.config;

import java.util.ArrayList;

import com.villagecraft.util.Reference;

import net.minecraftforge.common.ForgeConfigSpec;

final class ServerConfig {
	final ForgeConfigSpec.BooleanValue server;
	final ForgeConfigSpec.ConfigValue<ArrayList<String>> serverList;

	ServerConfig(final ForgeConfigSpec.Builder builder) {
		builder.push("general");
		server = builder
				.comment("An example boolean in the server config")
				.translation(Reference.MODID + ".config.serverBoolean")
				.define("serverBoolean", true);
		serverList = builder
				.comment("An example list of Strings in the server config")
				.translation(Reference.MODID + ".config.serverStringList")
				.define("serverStringList", new ArrayList<>());

		builder.pop();
	}
}

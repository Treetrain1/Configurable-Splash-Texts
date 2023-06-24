package com.treetrain1.configurablesplashtexts;

import com.treetrain1.configurablesplashtexts.config.ConfigurableSplashTextsConfig;
import com.treetrain1.configurablesplashtexts.log.LogType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.frozenblock.lib.menu.api.SplashTextAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurableSplashTexts implements ClientModInitializer {

	public static final String MOD_ID = "configurablesplashtexts";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static boolean DEV_LOGGING = false;
	public static boolean UNSTABLE_LOGGING = FabricLoader.getInstance().isDevelopmentEnvironment();

	public static boolean areConfigsInit;

	@Override
	public void onInitializeClient() {
		var added = ConfigurableSplashTextsConfig.get().general.addedSplashes;
		var removed = ConfigurableSplashTextsConfig.get().general.removedSplashes;

		if (UNSTABLE_LOGGING) {
			for (var string : added) {
				log("Added '" + string + "' to splash texts.", LogType.INFO, true);
			}
			for (var string : removed) {
				log("Removed '" + string + "' from splash texts.", LogType.INFO, true);
			}
		}

		for (var string : added) {
			SplashTextAPI.add(string);
		}

		for (var string : removed) {
			SplashTextAPI.remove(string);
		}
	}

	// LOGGING
	public static void log(String string, LogType type, boolean shouldLog) {
		if (shouldLog) {
			switch (type) {
				case DEBUG -> LOGGER.debug(string);
				case INFO -> LOGGER.info(string);
				case WARN -> LOGGER.warn(string);
				case ERROR -> LOGGER.error(string);
			}
		}
	}
}

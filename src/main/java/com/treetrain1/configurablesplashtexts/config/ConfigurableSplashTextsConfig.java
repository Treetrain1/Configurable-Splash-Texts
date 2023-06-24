package com.treetrain1.configurablesplashtexts.config;

import com.treetrain1.configurablesplashtexts.ConfigurableSplashTexts;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

@Config(name = ConfigurableSplashTexts.MOD_ID)
public class ConfigurableSplashTextsConfig extends PartitioningSerializer.GlobalData {

	@ConfigEntry.Category("general")
	@ConfigEntry.Gui.TransitiveObject
	public final GeneralConfig general = new GeneralConfig();

	public static ConfigurableSplashTextsConfig get() {
		if (!ConfigurableSplashTexts.areConfigsInit) {
			AutoConfig.register(ConfigurableSplashTextsConfig.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
			ConfigurableSplashTexts.areConfigsInit = true;
		}
		return AutoConfig.getConfigHolder(ConfigurableSplashTextsConfig.class).getConfig();
	}

	public static Component text(String key) {
		return Component.translatable("option." + ConfigurableSplashTexts.MOD_ID + "." + key);
	}

	public static Component tooltip(String key) {
		return Component.translatable("tooltip." + ConfigurableSplashTexts.MOD_ID + "." + key);
	}

	@Environment(EnvType.CLIENT)
	public static Screen buildScreen(Screen parent) {
		var configBuilder = ConfigBuilder.create().setParentScreen(parent).setTitle(text("component.title"));
		configBuilder.setSavingRunnable(() -> AutoConfig.getConfigHolder(ConfigurableSplashTextsConfig.class).save());
		var general = configBuilder.getOrCreateCategory(text("general"));
		ConfigEntryBuilder entryBuilder = configBuilder.entryBuilder();
		GeneralConfig.setupEntries(general, entryBuilder);
		return configBuilder.build();
	}
}

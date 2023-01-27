package com.treetrain1.configurablesplashtexts.config;

import com.treetrain1.configurablesplashtexts.config.defaultconfig.DefaultGeneralConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.util.List;
import static com.treetrain1.configurablesplashtexts.config.ConfigurableSplashTextsConfig.text;
import static com.treetrain1.configurablesplashtexts.config.ConfigurableSplashTextsConfig.tooltip;

@Config(name = "general")
public class GeneralConfig implements ConfigData {

	public List<String> addedSplashes = DefaultGeneralConfig.ADDED_SPLASHES;

	public List<String> removedSplashes = DefaultGeneralConfig.REMOVED_SPLASHES;
	public int splashColor = DefaultGeneralConfig.SPLASH_COLOR;

	public boolean removeVanilla = DefaultGeneralConfig.REMOVE_VANILLA;

	@Environment(EnvType.CLIENT)
	static void setupEntries(ConfigCategory category, ConfigEntryBuilder entryBuilder) {
		var config = ConfigurableSplashTextsConfig.get().general;

		var add = entryBuilder.startStrList(text("added_splashes"), config.addedSplashes)
				.setDefaultValue(DefaultGeneralConfig.ADDED_SPLASHES)
				.setSaveConsumer(newValue -> config.addedSplashes = newValue)
				.setTooltip(tooltip("added_splashes"))
				.requireRestart()
				.build();

		var remove = entryBuilder.startStrList(text("removed_splashes"), config.removedSplashes)
				.setDefaultValue(DefaultGeneralConfig.REMOVED_SPLASHES)
				.setSaveConsumer(newValue -> config.removedSplashes = newValue)
				.setTooltip(tooltip("removed_splashes"))
				.requireRestart()
				.build();

		var splashColor = entryBuilder.startColorField(text("splash_color"), config.splashColor)
				.setDefaultValue(DefaultGeneralConfig.SPLASH_COLOR)
				.setSaveConsumer(newValue -> config.splashColor = newValue)
				.setTooltip(tooltip("splash_color"))
				.requireRestart()
				.build();

		var removeVanilla = entryBuilder.startBooleanToggle(text("remove_vanilla"), config.removeVanilla)
				.setDefaultValue(DefaultGeneralConfig.REMOVE_VANILLA)
				.setSaveConsumer(newValue -> config.removeVanilla = newValue)
				.setTooltip(tooltip("remove_vanilla"))
				.requireRestart()
				.build();

		category.addEntry(add);
		category.addEntry(remove);
		category.addEntry(splashColor);
		category.addEntry(removeVanilla);
	}
}

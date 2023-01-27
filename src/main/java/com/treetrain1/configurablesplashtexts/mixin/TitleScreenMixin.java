package com.treetrain1.configurablesplashtexts.mixin;

import com.treetrain1.configurablesplashtexts.config.ConfigurableSplashTextsConfig;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {

    @ModifyConstant(method = "render", constant = @Constant(intValue = 16776960))
    private int changeColor(int constant) {
        return ConfigurableSplashTextsConfig.get().general.splashColor;
    }
}

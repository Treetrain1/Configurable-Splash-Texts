package com.treetrain1.configurablesplashtexts.mixin;

import com.treetrain1.configurablesplashtexts.config.ConfigurableSplashTextsConfig;
import net.minecraft.client.gui.components.SplashRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(SplashRenderer.class)
public class SplashRendererMixin {

    @ModifyConstant(method = "render", constant = @Constant(intValue = 16776960))
    private int changeColor(int constant) {
        return ConfigurableSplashTextsConfig.get().general.splashColor;
    }
}

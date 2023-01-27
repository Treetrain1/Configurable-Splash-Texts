package com.treetrain1.configurablesplashtexts.mixin;

import com.treetrain1.configurablesplashtexts.ConfigurableSplashTexts;
import com.treetrain1.configurablesplashtexts.config.ConfigurableSplashTextsConfig;
import net.minecraft.client.resources.SplashManager;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = SplashManager.class, priority = 1) // mixin must run first to prevent adding modded splashes
public class SplashManagerMixin {

    @Inject(method = "apply(Ljava/util/List;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V", at = @At("HEAD"))
    private void apply(List<String> object, ResourceManager resourceManager, ProfilerFiller profiler, CallbackInfo ci) {
        if (ConfigurableSplashTextsConfig.get().general.removeVanilla) {
            object.clear();
        }
    }
}

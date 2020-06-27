package com.github.vini2003.tippy.mixin;

import com.github.vini2003.tippy.registry.ResourceRegistry;
import com.github.vini2003.tippy.util.Tip;
import net.minecraft.client.MinecraftClient;

import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import net.minecraft.client.gui.screen.ProgressScreen;
import net.minecraft.client.gui.screen.SaveLevelScreen;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.LevelLoadingScreen;
import net.minecraft.client.gui.screen.Screen;
import spinnery.client.render.BaseRenderer;


@Mixin(value = {LevelLoadingScreen.class, ProgressScreen.class, SaveLevelScreen.class, DownloadingTerrainScreen.class, ConnectScreen.class})
public class MixinScreens {
    Tip tip = ResourceRegistry.get();

    @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V", at = @At("RETURN"))
    protected void render(MatrixStack matrices, int x, int y, float u, CallbackInfo info) {
        Screen screen = (Screen)(Object)this;
        TextRenderer textRenderer = BaseRenderer.getTextRenderer();
        textRenderer.drawTrimmed(tip.getText(), 12, screen.height - 32, screen.width - 12, 0x000000);
    }
} 
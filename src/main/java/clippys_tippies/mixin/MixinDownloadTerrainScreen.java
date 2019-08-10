package clippys_tippies.mixin;

import clippys_tippies.TipHandler;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.NarratorManager;

@Mixin(DownloadingTerrainScreen.class)
public abstract class MixinDownloadTerrainScreen extends Screen {
    TextRenderer textRenderer;
    TipHandler tipHandler = new TipHandler();
    String randomTip = tipHandler.getRandomTip();
    MixinDownloadTerrainScreen() {
        super(NarratorManager.EMPTY);
    }
    @Inject(method = "render(IIF)V", at = @At("RETURN"))
    protected void render(int a, int b, float c, CallbackInfo info) {
        textRenderer = this.font;
        textRenderer.drawStringBounded("TIP:", this.width - (this.width - 16), this.height - 32, 32, 0x7FAEFF);
        textRenderer.drawStringBounded(randomTip, this.width - (this.width - 48), this.height - 32, this.width - 64, 0xFFFFFF);
    }
} 
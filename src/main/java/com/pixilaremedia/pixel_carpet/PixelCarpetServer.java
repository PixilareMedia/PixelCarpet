package com.pixilaremedia.pixel_carpet;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.pixilaremedia.pixel_carpet.utils.PixelCarpetTranslations;
import net.fabricmc.api.ModInitializer;

import java.util.Map;

public class PixelCarpetServer implements CarpetExtension, ModInitializer {
    public String version() {
        return "pixel-carpet";
    }

    public static void loadExtension() {
        CarpetServer.manageExtension(new PixelCarpetServer());
    }

    @Override
    public void onInitialize() {
        loadExtension();
    }

    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(PixelCarpetSettings.class);
    }

    public Map<String, String> canHasTranslations(String lang) {
        return PixelCarpetTranslations.getTranslationFromResourcePath(lang);
    }
}

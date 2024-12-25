package com.pixilaremedia.pixel_carpet;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.pixilaremedia.pixel_carpet.commands.PvpCommand;
import com.pixilaremedia.pixel_carpet.utils.PixelCarpetTranslations;
import com.pixilaremedia.pixel_carpet.utils.PvpWhitelist;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import java.io.File;
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
        PvpWhitelist.create(new File("pvp_whitelist.json"));
        CommandRegistrationCallback.EVENT.register(PvpCommand::register);
    }

    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(PixelCarpetSettings.class);
    }

    public Map<String, String> canHasTranslations(String lang) {
        return PixelCarpetTranslations.getTranslationFromResourcePath(lang);
    }
}

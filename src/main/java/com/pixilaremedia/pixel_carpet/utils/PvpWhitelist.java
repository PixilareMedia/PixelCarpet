package com.pixilaremedia.pixel_carpet.utils;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.Whitelist;
import net.minecraft.server.WhitelistEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class PvpWhitelist {
    private static final Logger LOGGER = LogManager.getLogger();
    private static Whitelist pvpWhitelist;

    public static void create(File file) {
        pvpWhitelist = new Whitelist(file);
        load();
    }

    public static void load() {
        try {
            pvpWhitelist.load();
        } catch (Exception error) {
            LOGGER.error("Failed to load pvp whitelist: ", error);
        }
    }

    public static boolean contains(GameProfile player) {
        return pvpWhitelist.isAllowed(player);
    }

    public static void addPlayer(GameProfile player) {
        pvpWhitelist.add(new WhitelistEntry(player));
    }

    public static void removePlayer(GameProfile player) {
        pvpWhitelist.remove(player);
    }

    public static String[] getPlayers() {
        return pvpWhitelist.getNames();
    }
}

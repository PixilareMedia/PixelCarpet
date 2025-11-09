package com.pixilaremedia.pixel_carpet.utils;

import net.minecraft.server.PlayerConfigEntry;
import net.minecraft.server.Whitelist;
import net.minecraft.server.WhitelistEntry;
import net.minecraft.server.dedicated.management.listener.CompositeManagementListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class PvpWhitelist {
    private static final Logger LOGGER = LogManager.getLogger();
    private static Whitelist pvpWhitelist;

    public static void create(File file) {
        pvpWhitelist = new Whitelist(file, new CompositeManagementListener());
        load();
    }

    public static void load() {
        try {
            pvpWhitelist.load();
        } catch (Exception error) {
            LOGGER.error("Failed to load pvp whitelist: ", error);
        }
    }

    public static boolean contains(PlayerConfigEntry player) {
        return pvpWhitelist.isAllowed(player);
    }

    public static void addPlayer(PlayerConfigEntry player) {
        pvpWhitelist.add(new WhitelistEntry(player));
    }

    public static void removePlayer(PlayerConfigEntry player) {
        pvpWhitelist.remove(player);
    }

    public static String[] getPlayers() {
        return pvpWhitelist.getNames();
    }
}

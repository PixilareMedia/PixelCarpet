package com.pixilaremedia.pixel_carpet.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.pixilaremedia.pixel_carpet.PixelCarpetSettings;
import com.pixilaremedia.pixel_carpet.utils.PvpWhitelist;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.PlayerConfigEntry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class PvpCommand {
    public static LiteralCommandNode<ServerCommandSource> register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
        return dispatcher.register(
                CommandManager.literal("pvp")
                        .then(
                                CommandManager.literal("on")
                                        .executes(PvpCommand::enablePvp)
                        )
                        .then(
                                CommandManager.literal("off")
                                        .executes(PvpCommand::disablePvp)
                        )
                        .then(
                                CommandManager.literal("list")
                                        .executes(PvpCommand::listPlayers)
                        )
                        .executes(PvpCommand::pvpStatus)
        );
    }

    private static int enablePvp(CommandContext<ServerCommandSource> ctx) {
        ServerCommandSource source = ctx.getSource();

        if (PixelCarpetSettings.pvpToggle) {
            PlayerConfigEntry player = source.getPlayer().getPlayerConfigEntry();

            if(!PvpWhitelist.contains(player)) {
                PvpWhitelist.addPlayer(player);
                source.sendFeedback(() -> Text.translatableWithFallback("pixel_carpet.pvp_toggle.on", "PvP for you is now on."), false);
                return 1;
            }

            source.sendFeedback(() -> Text.translatableWithFallback("pixel_carpet.pvp_toggle.on.existing", "You already have PvP on!"), false);
            return 0;
        }

        source.sendFeedback(() -> Text.translatableWithFallback("pixel_carpet.pvp_toggle.disabled", "PvP Toggling is disabled by default in Carpet."), false);
        return 0;
    }

    private static int disablePvp(CommandContext<ServerCommandSource> ctx) {
        ServerCommandSource source = ctx.getSource();

        if (PixelCarpetSettings.pvpToggle) {
            PlayerConfigEntry player = source.getPlayer().getPlayerConfigEntry();

            if(PvpWhitelist.contains(player)) {
                PvpWhitelist.removePlayer(player);
                source.sendFeedback(() -> Text.translatableWithFallback("pixel_carpet.pvp_toggle.off", "PvP for you is now off."), false);
                return 1;
            }

            source.sendFeedback(() -> Text.translatableWithFallback("pixel_carpet.pvp_toggle.off.existing", "You already have PvP off!"), false);
            return 0;
        }

        source.sendFeedback(() -> Text.translatableWithFallback("pixel_carpet.pvp_toggle.disabled", "PvP Toggling is disabled by default in Carpet."), false);
        return 0;
    }

    private static int pvpStatus(CommandContext<ServerCommandSource> ctx) {
        ServerCommandSource source = ctx.getSource();

        if (PixelCarpetSettings.pvpToggle) {
            PlayerConfigEntry player = source.getPlayer().getPlayerConfigEntry();

            source.sendFeedback(() -> Text.translatableWithFallback("pixel_carpet.pvp_toggle.status" + (PvpWhitelist.contains(player) ? "pixel_carpet.pvp_toggle.status.on" : "pixel_carpet.pvp_toggle.status.off"), "PvP for you is " + (PvpWhitelist.contains(player) ? "on" : "off")), false);
            return 1;
        }

        source.sendFeedback(() -> Text.translatableWithFallback("pixel_carpet.pvp_toggle.disabled", "PvP Toggling is disabled by default in Carpet."), false);
        return 0;
    }

    private static int listPlayers(CommandContext<ServerCommandSource> ctx) {
        ServerCommandSource source = ctx.getSource();
        if (PixelCarpetSettings.pvpToggle) {
            source.sendFeedback(() -> Text.translatableWithFallback("pixel_carpet.pvp_toggle.list" + String.join(", ", PvpWhitelist.getPlayers()), "Players with PvP on: " + String.join(", ", PvpWhitelist.getPlayers())), false);
            return 1;
        }

        source.sendFeedback(() -> Text.translatableWithFallback("pixel_carpet.pvp_toggle.disabled", "PvP Toggling is disabled by default in Carpet."), false);
        return 0;
    }
}

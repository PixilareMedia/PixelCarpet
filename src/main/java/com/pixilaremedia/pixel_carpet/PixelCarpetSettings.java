package com.pixilaremedia.pixel_carpet;

import carpet.api.settings.Rule;

import static carpet.api.settings.RuleCategory.*;

public class PixelCarpetSettings {
    public static final String PIXEL = "pixel";
    public static final String PROGRESSION = "progression";

    @Rule(categories = {PIXEL, FEATURE})
    public static boolean renewableGravel = false;

    @Rule(categories = {PIXEL, PROGRESSION, FEATURE})
    public static boolean disabledNetherPortals = false;

    @Rule(categories = {PIXEL, PROGRESSION, FEATURE})
    public static boolean disabledEndPortals = false;

    @Rule(categories = {PIXEL, FEATURE})
    public static boolean moreMobsOnLeads = false;

    @Rule(categories = {PIXEL, FEATURE})
    public static boolean preventSnowMelting = false;

    @Rule(categories = {PIXEL, FEATURE})
    public static boolean preventIceMelting = false;

    @Rule(categories = {PIXEL, FEATURE})
    public static boolean pvpToggle = false;
}

package com.topasphaltstudios.tabsimpleeconomybridge;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ENABLE_BRIDGE = BUILDER
            .comment("Enable or disable the TAB-SimpleEconomy bridge.")
            .define("enableBridge", true);

    public static final ModConfigSpec.ConfigValue<String> PLACEHOLDER = BUILDER
            .comment("Placeholder registered in TAB.")
            .define("placeholder", "%credits%");

    public static final ModConfigSpec.BooleanValue SHOW_DECIMALS = BUILDER
            .comment("Show decimals instead of rounded economy format.")
            .define("showDecimals", false);

    public static final ModConfigSpec.ConfigValue<String> FALLBACK_VALUE = BUILDER
            .comment("Value shown if the player's balance cannot be read.")
            .define("fallbackValue", "0");

    public static final ModConfigSpec.BooleanValue DEBUG_LOGGING = BUILDER
            .comment("Enable debug logging in console.")
            .define("debugLogging", false);

    public static final ModConfigSpec SPEC = BUILDER.build();
}
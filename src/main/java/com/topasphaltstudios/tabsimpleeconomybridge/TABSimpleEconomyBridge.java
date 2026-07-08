package com.topasphaltstudios.tabsimpleeconomybridge;

import com.mojang.logging.LogUtils;
import com.simpleeconomy.economy.EconomyManager;
import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.placeholder.PlaceholderManager;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import org.slf4j.Logger;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Mod(TABSimpleEconomyBridge.MODID)
public class TABSimpleEconomyBridge {
    public static final String MODID = "tabsimpleeconomybridge";
    public static final Logger LOGGER = LogUtils.getLogger();

    private static boolean registered = false;
    private final Map<UUID, Double> lastLoggedBalances = new ConcurrentHashMap<>();

    public TABSimpleEconomyBridge(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        NeoForge.EVENT_BUS.addListener(this::onServerStarted);
    }

    private void onServerStarted(ServerStartedEvent event) {
        if (registered) {
            return;
        }

        if (!Config.ENABLE_BRIDGE.get()) {
            LOGGER.info("TABSimpleEconomyBridge is disabled in config.");
            return;
        }

        try {
            String placeholder = Config.PLACEHOLDER.get();

            PlaceholderManager manager = TabAPI.getInstance().getPlaceholderManager();
            manager.registerPlayerPlaceholder(placeholder, 1000, tabPlayer -> {
                ServerPlayer player = event.getServer().getPlayerList().getPlayer(tabPlayer.getUniqueId());
                if (player == null) {
                    return Config.FALLBACK_VALUE.get();
                }

                try {
                    double balance = EconomyManager.getBalance(player);

                    if (Config.DEBUG_LOGGING.get()) {
                        UUID uuid = player.getUUID();
                        Double previous = lastLoggedBalances.put(uuid, balance);

                        if (previous == null || Double.compare(previous, balance) != 0) {
                            LOGGER.info("Balance for {} changed: {} -> {}", player.getName().getString(), previous, balance);
                        }
                    }

                    if (Config.SHOW_DECIMALS.get()) {
                        return String.format(java.util.Locale.US, "%.2f", balance);
                    }

                    return EconomyManager.formatBalance(balance);
                } catch (Throwable t) {
                    if (Config.DEBUG_LOGGING.get()) {
                        LOGGER.error("Failed to read balance for {}", player.getName().getString(), t);
                    }
                    return Config.FALLBACK_VALUE.get();
                }
            });

            registered = true;
            LOGGER.info("Placeholder {} registered successfully.", placeholder);
        } catch (Throwable t) {
            LOGGER.error("Failed to register configured placeholder.", t);
        }
    }
}
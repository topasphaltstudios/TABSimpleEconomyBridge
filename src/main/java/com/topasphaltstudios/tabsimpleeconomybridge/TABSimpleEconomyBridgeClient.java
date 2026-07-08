package com.topasphaltstudios.tabsimpleeconomybridge;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = TABSimpleEconomyBridge.MODID, dist = Dist.CLIENT)
public class TABSimpleEconomyBridgeClient {
    public TABSimpleEconomyBridgeClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}
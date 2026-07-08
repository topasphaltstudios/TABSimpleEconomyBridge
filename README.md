TABSimpleEconomyBridge

A NeoForge mod for Minecraft 1.21.1 that connects SimpleEconomy with TAB using a configurable placeholder.

## Compatibility

- Minecraft: 1.21.1
- NeoForge: 21.1.211
- TAB: 5.2.1 for Minecraft 1.20.5 - 1.21.1
- SimpleEconomy: 1.0.0

## What it does

TABSimpleEconomyBridge registers a TAB placeholder that reads a player's balance from SimpleEconomy and displays it in TAB.

## Features

- Configurable TAB placeholder
- Support for rounded or decimal balance display
- Fallback value if the balance cannot be read
- Optional debug logging

## Configuration

The common config file includes these options:

- `enableBridge`
- `placeholder`
- `showDecimals`
- `fallbackValue`
- `debugLogging`

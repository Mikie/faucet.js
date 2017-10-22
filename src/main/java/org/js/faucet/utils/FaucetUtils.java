package org.js.faucet.utils;

import org.bukkit.Bukkit;

public class FaucetUtils {

    public static String getServerVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().substring(Bukkit.getServer().getClass().getPackage().getName().lastIndexOf('.') + 1);
    }
}

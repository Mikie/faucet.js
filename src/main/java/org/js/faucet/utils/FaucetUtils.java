package org.js.faucet.utils;

import org.bukkit.Bukkit;
import org.js.faucet.exception.UtilException;

public final class FaucetUtils {
    private FaucetUtils() {
        throw new UtilException();
    }

    public static String getServerVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().substring(Bukkit.getServer().getClass().getPackage().getName().lastIndexOf('.') + 1);
    }
}

package org.js.faucet.utils;

import org.bukkit.Bukkit;
import org.js.faucet.exception.UtilException;

import java.io.File;

public final class FaucetUtils {
    private FaucetUtils() {
        throw new UtilException();
    }

    public static String getServerVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().substring(Bukkit.getServer().getClass().getPackage().getName().lastIndexOf('.') + 1);
    }

    public static String getFileExtension(File file) {
        return file.getName().split(".")[1];
    }
}

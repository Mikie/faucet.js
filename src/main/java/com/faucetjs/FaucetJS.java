package com.faucetjs;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;

public class FaucetJS extends JavaPlugin {
    private String scriptDirectory = null;

    @Override
    public void onEnable() {
        //saveConfig();
        scriptDirectory = getConfig().getString("settings.scripts-folder");
        File directory = new File(getDataFolder(), scriptDirectory == null ? "scripts" : scriptDirectory);

        if (directory.mkdirs()) {
            getLogger().log(Level.INFO, "Successfully created the \'" + directory.getName() + "\' directory!");
        }
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    public String getScriptDirectory() {
        return scriptDirectory;
    }
}

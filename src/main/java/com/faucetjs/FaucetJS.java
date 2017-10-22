package com.faucetjs;

import com.faucetjs.commands.Faucet;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class FaucetJS extends JavaPlugin {
    @Getter
    private File scriptDirectory = new File(getDataFolder(), getConfig().getString("settings.scripts-folder") == null ? "scripts" : getConfig().getString("settings.scripts-folder"));

    @Override
    public void onEnable() {
        this.getServer().getPluginCommand("faucet").setExecutor(new Faucet());

        saveConfig();

        try {
            if (!scriptDirectory.getName().equals(getConfig().getString("settings.scripts-folder"))) {
                File newDirectory = new File(getDataFolder(), getConfig().getString("settings.scripts-folder"));
                scriptDirectory.mkdirs();
                FileUtils.copyDirectory(scriptDirectory, newDirectory);
                getLogger().log(Level.INFO, "The scripts directory has been renamed to " + newDirectory.getName());
            }

            if (scriptDirectory.mkdirs()) {
                getLogger().log(Level.INFO, "Successfully created the \'" + scriptDirectory.getName() + "\' directory!");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        saveConfig();
    }
}

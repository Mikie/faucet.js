package com.faucetjs;

import com.faucetjs.commands.Faucet;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;

public class FaucetJS extends JavaPlugin {
    @Getter
    private String scriptDirectory = getConfig().getString("settings.scripts-folder") == null ? "scripts" : getConfig().getString("settings.scripts-folder");

    @Override
    public void onEnable() {
        this.getServer().getPluginCommand("faucet").setExecutor(new Faucet());
        
        //saveConfig();
        File directory = new File(getDataFolder(), scriptDirectory);

        if (directory.mkdirs())
            getLogger().log(Level.INFO, "Successfully created the \'" + directory.getName() + "\' directory!");


        if(!directory.getName().equalsIgnoreCase(scriptDirectory))
            directory.renameTo(new File(scriptDirectory));
    }

    @Override
    public void onDisable() {
        saveConfig();
    }
}

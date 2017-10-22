package com.faucetjs;

import com.faucetjs.commands.Faucet;
import com.google.common.collect.Maps;
import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.WatchKey;
import java.util.Map;
import java.util.logging.Level;

public class FaucetJS extends JavaPlugin {
    @Getter
    private File scriptDirectory = new File(getDataFolder(), "scripts");
    @Getter
    private ScriptEngine scriptEngine;

    @Override
    public void onEnable() {
        scriptEngine = new ScriptEngineManager().getEngineByName("JavaScript");

        if (scriptDirectory.mkdirs()) {
            getLogger().log(Level.INFO, "Successfully created the \'" + scriptDirectory.getName() + "\' directory!");
        }
        getCommand("faucet").setExecutor(new Faucet());
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        getServer().getScheduler().cancelTasks(this);
        saveConfig();

        if(scriptEngine != null) {
            scriptEngine = null;
        }
    }
}

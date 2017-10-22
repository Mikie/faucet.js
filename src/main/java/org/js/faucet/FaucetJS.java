package org.js.faucet;

import org.bstats.bukkit.Metrics;
import org.js.faucet.commands.Faucet;
import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import org.js.faucet.utils.FaucetUtils;
import org.js.faucet.commands.wapper.CommandManager;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.util.logging.Level;

public class FaucetJS extends JavaPlugin {
    @Getter
    private File scriptDirectory = new File(getDataFolder(), "scripts");
    @Getter
    private ScriptEngine scriptEngine;
    private static FaucetJS instance;
    @Getter
    private CommandManager commandManager;

    @Override
    public void onLoad() {
        instance = this;
        this.commandManager = new CommandManager();
    }

    @Override
    public void onEnable() {
        Metrics metrics = new Metrics(this);
        metrics.addCustomChart(new Metrics.SimplePie("server_version", () -> FaucetUtils.getServerVersion()));

        scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");

        if (scriptDirectory.mkdirs()) {
            getLogger().log(Level.INFO, "Successfully created the \'" + scriptDirectory.getName() + "\' directory!");
        }
        getCommand("faucet").setExecutor(new Faucet());
        System.out.println(FaucetUtils.getServerVersion());
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        getServer().getScheduler().cancelTasks(this);
        saveConfig();

        if (scriptEngine != null) {
            scriptEngine = null;
        }
    }

    public static FaucetJS get() {
        return instance;
    }
}

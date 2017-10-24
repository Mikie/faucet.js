package org.js.faucet;

import lombok.Getter;
import org.bstats.bukkit.Metrics;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.js.faucet.command.MainCommand;
import org.js.faucet.command.wrapper.CommandManager;
import org.js.faucet.exception.TryUtils;
import org.js.faucet.locale.Locale;
import org.js.faucet.script.ScriptLoader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Arrays;
import java.util.logging.Level;

public class FaucetJS extends JavaPlugin {
    @Getter
    private ScriptLoader loader;

    @Getter
    private ScriptEngine scriptEngine;

    private static FaucetJS instance;

    @Getter
    private CommandManager commandManager;

    @Getter
    private Locale<FaucetJS> locale;

    @Getter
    private Metrics metrics;

    @Override
    public void onLoad() {
        instance = this;
        this.commandManager = new CommandManager();
        this.locale = new Locale<>(this, "messages.yml", this.getDataFolder());
    }

    @Override
    public void onEnable() {
        this.metrics = new Metrics(this); // Still need to check if this is working or not. If not I'll find my own way.
        this.scriptEngine = new ScriptEngineManager().getEngineByName("nashorn"); // Can use 'nashorn' or 'JavaScript'; either work.
        this.loader = new ScriptLoader();
        this.loader.run();
        this.getCommandManager().register(new MainCommand(this));
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        getServer().getScheduler().cancelTasks(this);
        saveConfig();

        if (scriptEngine != null) {
            scriptEngine = null;
        }

        TryUtils.run(this.loader::close);
    }

    public void log(Level lvl, String... messages) {
        Arrays.stream(messages).forEachOrdered(msg -> this.getLogger().log(lvl, "[FaucetJS] " + msg));
    }

    public static FaucetJS get() {
        return instance;
    }
}

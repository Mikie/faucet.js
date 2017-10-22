package org.js.faucet.commands.wapper;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.SimplePluginManager;
import org.js.faucet.utils.GenericUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.concurrent.Executors;

public class CommandManager {
    protected ListeningExecutorService service;
    private SimpleCommandMap commandMap;

    @SneakyThrows
    public CommandManager() {
        service = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

        SimplePluginManager manager = (SimplePluginManager) Bukkit.getServer().getPluginManager();
        Field field = manager.getClass().getField("commandMap");
        field.setAccessible(true);
        this.commandMap = GenericUtils.cast(field.get(manager));
    }

    public void register(FaucetCommand... commands) {
        Arrays.stream(commands).forEach(cmd -> this.commandMap.register("faucet", cmd));
    }
}

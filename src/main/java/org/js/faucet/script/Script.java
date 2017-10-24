package org.js.faucet.script;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.Charsets;
import org.bukkit.Bukkit;
import org.js.faucet.FaucetJS;
import org.js.faucet.exception.TryUtils;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.SimpleScriptContext;
import java.io.*;

//TODO more
@ParametersAreNonnullByDefault
@RequiredArgsConstructor
@Getter
public class Script implements Runnable, AutoCloseable {
    private final ScriptLoader loader;
    private final File script;
    private ScriptContext context;
    private String name;
    private double version;
    private String[] authors;
    private String[] dependencies;
    private String[] softDependencies;
    private String description;

    @Override
    public void run() {
        Reader reader = TryUtils.supply(() -> new BufferedReader(new InputStreamReader(new FileInputStream(this.script), Charsets.UTF_8)), BufferedReader.class);

        ScriptEngine engine = FaucetJS.get().getScriptEngine();
        Bindings bindings = engine.createBindings();
        bindings.put("server", Bukkit.getServer());
        bindings.put("loader", this.loader);
        bindings.put("faucet", FaucetJS.get());
        this.context = new SimpleScriptContext();
        this.context.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
        TryUtils.run(reader::close);
    }

    @Override
    public void close() {

    }
}

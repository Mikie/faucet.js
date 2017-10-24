package org.js.faucet.script;

import org.js.faucet.FaucetJS;
import org.js.faucet.exception.DeveloperException;
import org.js.faucet.exception.TryUtils;
import org.js.faucet.utils.FaucetUtils;
import org.js.faucet.utils.TimeUtil;

import java.io.File;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * Loads scripts in FaucetJS/scripts.
 */
public class ScriptLoader implements Runnable, AutoCloseable {
    private File directory;
    private final HashSet<Script> scripts = new HashSet<>();
    private FaucetJS instance = FaucetJS.get();

    /**
     * Loads all scripts in FaucetJS/scripts.
     */
    @Override
    public void run() {
        long start = System.currentTimeMillis();
        this.directory = new File(this.instance.getDataFolder(), "scripts");

        if (!this.directory.exists()) {
            if (this.directory.mkdir()) {
                this.instance.log(Level.INFO, "Successfully created file \'" + this.directory.getName() + "\'.");
            } else {
                throw new DeveloperException("Couldn't make directory " + this.directory.getName());
            }
        }

        this.instance.log(Level.INFO, "Loading scripts.");

        if (this.directory.listFiles() != null) {
            for (File fileScript : this.directory.listFiles()) {
                if (!FaucetUtils.getFileExtension(fileScript).equals(".js")) {
                    continue;
                }

                TryUtils.run(() -> this.load(new Script(this, new File(this.directory, fileScript.toPath().toString()))));
            }
        }

        this.instance.log(Level.INFO, "Loaded " + String.valueOf(this.scripts.size()) + " scripts in " + TimeUtil.millisecondsToTimeUnits(TimeUnit.SECONDS, (start - System.currentTimeMillis()), false) + ".");
    }

    /**
     * Loads a script.
     * @param path - script path.
     */
    public void load(String path) {
        TryUtils.run(() -> this.load(new Script(this, new File(directory, path))));
    }

    /**
     * Loads a script.
     * @param script - script instance.
     * @throws Exception
     */
    public void load(Script script) throws Exception {
        this.instance.log(Level.INFO, "Loading script " + script.getName() + "-v" + String.valueOf(script.getVersion()) + ".");
        this.scripts.add(script);
        script.run();
    }

    /**
     * Unloads a script.
     * @param script - script instance
     * @throws Exception
     */
    public void unload(Script script) throws Exception {
        this.instance.log(Level.INFO, "Unloading script " + script.getName() + "-v" + String.valueOf(script.getVersion()) + ".");
        script.close();
    }

    /**
     * Closes the loader and unloads all scripts.
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        int amount = this.scripts.size();
        this.instance.log(Level.INFO, "Unloading " + String.valueOf(amount) + " scripts.");

        long start = System.currentTimeMillis();

        for (Script s : this.scripts) {
            TryUtils.run(() -> this.unload(s));
        }

        this.scripts.clear();
        this.instance.log(Level.INFO, "Unloaded " + String.valueOf(amount) + " scripts in " + TimeUtil.millisecondsToTimeUnits(TimeUnit.SECONDS, (start - System.currentTimeMillis()), false) + ".");
    }

    /**
     * Gets the instance of the script corresponding to the name.
     * @param name - name of the script.
     * @return the script if found, else null.
     */
    public Script get(String name) {
        for (Script s : this.scripts) {
            if (!s.getName().equalsIgnoreCase(name)) {
                continue;
            }

            return s;
        }

        return null;
    }
}

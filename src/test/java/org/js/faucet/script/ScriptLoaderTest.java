package org.js.faucet.script;

import org.js.faucet.exception.TryUtils;
import org.js.faucet.utils.GenericUtils;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ScriptLoaderTest {
    private ScriptLoader loader;
    private Script script;

    @BeforeAll
    public void init() {
        this.loader = new ScriptLoader();
        assertNotNull(this.loader);
        TryUtils.run(() -> this.loader.load(new Script(this.loader, new File(System.getProperty("user.dir"), "FaucetJS"))));
        this.script = this.loader.get("testScript");
        assertNotNull(this.script);
    }

    @Test
    public void invokeMethodTest() {
        int expected = 10;
        int actual = GenericUtils.cast(this.script.execute("testFunc", 5, 5));
        assertEquals(expected, actual);
    }

    @BeforeAll
    public void shutdown() {
        TryUtils.run(() -> this.loader.unload(this.script));
    }
}

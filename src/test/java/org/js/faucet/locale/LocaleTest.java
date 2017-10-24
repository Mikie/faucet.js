package org.js.faucet.locale;

import org.bukkit.plugin.java.JavaPlugin;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LocaleTest {
    private Locale locale;

    @BeforeAll
    public void init() {
        this.locale = new Locale<>(Mockito.mock(JavaPlugin.class), "messages.yml", new File(System.getProperty("user.dir"), "FaucetJS"));
        assertNotNull(this.locale);
        this.locale.load();
    }

    @Test
    public void getTest() {
        String test = this.locale.getMessage("Test");
        assertEquals("test", test);
    }

    @Test
    public void populateTest() {
        LocaleTestTemplate template = new LocaleTestTemplate();
        locale.populate(template.getClass());

        assertEquals("field1", template.getField1());
        assertEquals(10, template.getField2());
    }

    @AfterAll
    public void shutdown() {
        this.locale = null;
    }
}

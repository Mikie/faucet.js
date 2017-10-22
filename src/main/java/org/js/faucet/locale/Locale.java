package org.js.faucet.locale;


import com.google.common.collect.Lists;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.js.faucet.exception.DeveloperException;
import org.js.faucet.exception.TryUtils;
import org.js.faucet.utils.Format;
import org.js.faucet.utils.GenericUtils;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * The concept behind this class is for easy management of the locale
 * in any given plugin, given that it adds the benefit of reducing the
 * amount of 'mess' it has when managing a locale consistency within
 * the plugin, let alone the server.
 * @param <P> - the plugin instance.
 */
@ParametersAreNonnullByDefault
public class Locale<P extends JavaPlugin>
{
    /**
     * Prefix of the plugin.
     */
    public String PLUGIN_PREFIX;

    /**
     * The plugins message format when sending single sentence messages.
     */
    private String PLUGIN_MESSAGE_FORMAT;

    /**
     * The header for an array of messages being sent to the player.
     */
    private String LIST_HEADER_FORMAT;

    /**
     * The footer for an array of messages being sent to the player.
     */
    private String LIST_FOOTER_FORMAT;

    /**
     * The page index for each page that is made, often used in when
     * a book of pages, aka: lists, is made.
     */
    private String PAGE_INDEX_FORMAT;

    private P instance;
    private String name;
    private File parent;
    private YamlConfiguration configuration;

    public Locale(P instance, String name, File parent)
    {
        this.instance = instance;
        this.name = name;
        this.parent = new File(parent, name);
    }

    public synchronized void load()
    {
        if (this.instance.getResource(this.name) != null) {
            TryUtils.run(() -> Files.copy(this.instance.getResource(this.name), this.parent.toPath(), StandardCopyOption.ATOMIC_MOVE));
        }

        if (this.configuration == null) {
            this.configuration = TryUtils.supply(() -> YamlConfiguration.loadConfiguration(this.parent), YamlConfiguration.class);
        }

        this.PLUGIN_PREFIX = this.getMessage("Prefix");
        this.PLUGIN_MESSAGE_FORMAT = this.getMessage("PluginMessageFormat");
        this.LIST_HEADER_FORMAT = this.getMessage("ListHeaderFormat");
        this.LIST_FOOTER_FORMAT = this.getMessage("ListFooterFormat");
        this.PAGE_INDEX_FORMAT = this.getMessage("PageIndexFormat");
    }

    /**
     * Same as BaseFile#populate but allows for formatting to be automatically done.
     * @param clazz - the class.
     */
    public void populate(Class<?> clazz)
    {
        for (Field f : clazz.getFields()) {
            if (!f.isAnnotationPresent(ConfigPopulate.class)) {
                continue;
            }

            ConfigPopulate annotation = f.getAnnotation(ConfigPopulate.class);

            Object value = this.configuration.get(annotation.value(), null);

            if (value == null) {
                throw new DeveloperException("Key " + annotation.value() + ". Was not found in file " + this.name + ".");
            }

            if (!GenericUtils.castable(value, f.getType())) {
                throw new DeveloperException("Value corresponding to key " + annotation.value() + " could not be assigned to field " + f.getName() + " as it's type, " + f.getType().getName() + " could not be casted to the value " + value.toString() + ".");
            }

            f.setAccessible(true);

            if (f.getType().equals(String.class) && annotation.format()) {
                TryUtils.run(() -> f.set(clazz, Format.color(this.PLUGIN_MESSAGE_FORMAT.replace("{prefix}", this.PLUGIN_PREFIX).replace("{message}", ((String) value)))));
                continue;
            } else if (f.getType().equals(String.class) && annotation.color()) {
                TryUtils.run(() -> f.set(clazz, Format.color((String) value)));
                continue;
            }

            TryUtils.run(() -> f.set(clazz, value));
        }
    }

    /**
     * Gets the message corresponding to the path.
     * @param path - the node.
     * @return the message.
     */
    public String getMessage(String path)
    {
        return this.configuration.getString(path);
    }

    public LinkedList<String> getHeader()
    {
        return Lists.newLinkedList(Arrays.asList(this.LIST_HEADER_FORMAT.split("\n")));
    }

    public LinkedList<String> getFooter()
    {
        return Lists.newLinkedList(Arrays.asList(this.LIST_FOOTER_FORMAT.split("\n")));
    }
}

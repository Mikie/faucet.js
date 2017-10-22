package org.js.faucet.utils;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.command.CommandSender;
import org.js.faucet.exception.UtilException;

import java.util.List;

public final class Sender {
    public Sender() {
        throw new UtilException();
    }

    public static void send(CommandSender sender, String message) {
        sender.sendMessage(message);
    }

    public static void send(CommandSender sender, List<String> content)
    {
        content.forEach(sender::sendMessage);
    }

    public static void sendComponents(CommandSender sender, List<BaseComponent> content)
    {
        content.forEach(line -> sender.spigot().sendMessage(line));
    }
}

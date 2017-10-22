package com.faucetjs.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Faucet implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                TextComponent faucetHelpTitle = new TextComponent(ChatColor.RED + "Faucet.js Help" + "\n");
                TextComponent faucetHelpReload = new TextComponent(ChatColor.RED + "/faucet reload <script>" + "\n" + ChatColor.GRAY + "> Reload a script or the config." + "\n");
                TextComponent faucetHelpList = new TextComponent(ChatColor.RED + "/faucet list" + "\n" + ChatColor.GRAY + "> List all the scripts on the server." + "\n");
                TextComponent faucetHelpAbout = new TextComponent(ChatColor.RED + "/faucet about <script>" + "\n" + ChatColor.GRAY + "> Get information about a specific script.");

                faucetHelpReload.setClickEvent(new ClickEvent(Action.SUGGEST_COMMAND, "/faucet reload "));

                faucetHelpReload.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("Click for auto complete.")).create()));
                faucetHelpList.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/faucet list"));
                faucetHelpList.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("Click for auto complete.")).create()));
                faucetHelpAbout.setClickEvent(new ClickEvent(Action.SUGGEST_COMMAND, "/faucet about"));
                faucetHelpAbout.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("Click for auto complete.")).create()));

                faucetHelpList.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/faucet list"));
                faucetHelpAbout.setClickEvent(new ClickEvent(Action.SUGGEST_COMMAND, "/faucet about "));


                player.spigot().sendMessage(faucetHelpTitle, faucetHelpReload, faucetHelpList, faucetHelpAbout);
            } else if (args[0].toUpperCase().equals("RELOAD")) {
                player.sendMessage("Not implemented yet.");
            } else if (args[0].toUpperCase().equals("LIST")) {
                player.sendMessage("Not implemented yet.");
            } else if (args[0].toUpperCase().equals("ABOUT")) {
                player.sendMessage("Not implemented yet.");
            }
        }
        return true;
    }
}

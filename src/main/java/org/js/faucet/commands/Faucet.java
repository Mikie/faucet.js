package org.js.faucet.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class giFaucet implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;

            if (args.length == 0) {
                TextComponent faucetHelpTitle = new TextComponent("§c§lFaucet.js Help\n");
                TextComponent faucetHelpReload = new TextComponent("§c/faucet reload <script>\n§7> Reload a script or the config.\n");
                TextComponent faucetHelpList = new TextComponent("§c/faucet list\n§7> List all the scripts on the server.\n");
                TextComponent faucetHelpAbout = new TextComponent("§c/faucet about <script>\n§7> Get information about a specific script.");
                
                faucetHelpReload.setClickEvent(new ClickEvent(Action.SUGGEST_COMMAND, "/faucet reload "));
                faucetHelpList.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/faucet list"));
                faucetHelpAbout.setClickEvent(new ClickEvent(Action.SUGGEST_COMMAND, "/faucet about "));
                        
                player.spigot().sendMessage(faucetHelpTitle, faucetHelpReload, faucetHelpList, faucetHelpAbout);
            } else if (args[0].equalsIgnoreCase("reload")) {
                player.sendMessage("Not implemented yet.");
            } else if (args[0].equalsIgnoreCase("list")) {
                player.sendMessage("Not implemented yet.");
            } else if (args[0].equalsIgnoreCase("about")) {
                player.sendMessage("Not implemented yet.");
            }
        }
        return true;
    }
}

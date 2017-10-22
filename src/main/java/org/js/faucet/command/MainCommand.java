package org.js.faucet.command;

import com.google.common.collect.Lists;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.js.faucet.FaucetJS;
import org.js.faucet.command.wrapper.CommandResponse;
import org.js.faucet.command.wrapper.FaucetCommand;
import org.js.faucet.utils.Sender;

import java.util.LinkedList;
import java.util.Map;

public class MainCommand extends FaucetCommand<FaucetJS> {
    public MainCommand(FaucetJS instance) {
        super(instance, instance.getLocale(), "faucet", "Parent command for faucet.", true, 0);
    }

    @Override
    public CommandResponse execute(CommandSender sender, LinkedList<String> parameters) {
        return new CommandResponse(CommandResponse.Type.SUCCESS, sender);
    }

    @Override
    public void onFailure(CommandSender sender, Map<String, Object> data, LinkedList<String> parameters) {
    }

    @Override
    public void onSuccess(CommandSender sender, Map<String, Object> data, LinkedList<String> parameters)
    {
        if (parameters.size() == 0) {
            TextComponent faucetHelpTitle = new TextComponent("§c§lFaucet.js Help\n");
            TextComponent faucetHelpReload = new TextComponent("§c/faucet reload <script>\n§7> Reload a script or the config.\n");
            TextComponent faucetHelpList = new TextComponent("§c/faucet list\n§7> List all the scripts on the server.\n");
            TextComponent faucetHelpAbout = new TextComponent("§c/faucet about <script>\n§7> Get information about a specific script.");
            faucetHelpReload.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/faucet reload "));
            faucetHelpList.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/faucet list"));
            faucetHelpAbout.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/faucet about "));
            Sender.sendComponents(sender, Lists.newArrayList(faucetHelpTitle, faucetHelpReload, faucetHelpList, faucetHelpAbout));
        } else {
            Sender.send(sender, "What?");
        }
    }

    public class ReloadCommand extends FaucetCommand<FaucetJS> {
        public ReloadCommand(FaucetJS instance) {
            super(instance, instance.getLocale(), "reload", "Reload faucet.", false, 0);
        }

        @Override
        public CommandResponse execute(CommandSender sender, LinkedList<String> parameters) {
            return new CommandResponse(CommandResponse.Type.FALIURE, sender);
        }

        @Override
        public void onFailure(CommandSender sender, Map<String, Object> data, LinkedList<String> parameters) {
            Sender.send(sender, "Command not written yet.");
        }

        @Override
        public void onSuccess(CommandSender sender, Map<String, Object> data, LinkedList<String> parameters) {
        }
    }

    public class ListCommand extends FaucetCommand<FaucetJS>
    {
        public ListCommand(FaucetJS instance) {
            super(instance, instance.getLocale(), "list", "Lists the currently loaded scripts", false, 0);
        }

        @Override
        public CommandResponse execute(CommandSender sender, LinkedList<String> parameters) {
            return new CommandResponse(CommandResponse.Type.FALIURE, sender);
        }

        @Override
        public void onFailure(CommandSender sender, Map<String, Object> data, LinkedList<String> parameters) {
            Sender.send(sender, "Command not written yet.");
        }

        @Override
        public void onSuccess(CommandSender sender, Map<String, Object> data, LinkedList<String> parameters) {
        }
    }

    public class AboutCommand extends FaucetCommand<FaucetJS>
    {
        public AboutCommand(FaucetJS instance) {
            super(instance, instance.getLocale(), "abput", "For information regarding the plugin", false, 0);
        }

        @Override
        public CommandResponse execute(CommandSender sender, LinkedList<String> parameters) {
            return new CommandResponse(CommandResponse.Type.FALIURE, sender);
        }

        @Override
        public void onFailure(CommandSender sender, Map<String, Object> data, LinkedList<String> parameters) {
            Sender.send(sender, "Command not written yet.");
        }

        @Override
        public void onSuccess(CommandSender sender, Map<String, Object> data, LinkedList<String> parameters) {
        }
    }
}

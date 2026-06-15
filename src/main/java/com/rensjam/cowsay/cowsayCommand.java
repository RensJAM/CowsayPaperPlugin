package com.rensjam.cowsay;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cowsayCommand implements CommandExecutor {

    private final Cowsay plugin;

    public cowsayCommand(Cowsay plugin) {
        this.plugin = plugin;
    }

    private boolean isGlobalEnabled() {
        return plugin.getConfig().getBoolean("globalToggle", false);
    }

    private void setGlobalEnabled(boolean value) {
        plugin.getConfig().set("globalToggle", value);
        plugin.saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage("§cUsage: /cowsay <message>");
            sender.sendMessage("§cUsage: /cowsay globalToggle");
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("globalToggle")) {

            if (!sender.isOp() && !sender.hasPermission("cowsay.globaltoggle")) {
                sender.sendMessage("§cOnly operators can do this.");
                return true;
            }

            boolean newValue = !isGlobalEnabled();
            setGlobalEnabled(newValue);

            sender.sendMessage("§eGlobal cowsay mode is now "
                    + (newValue ? "§aenabled" : "§cdisabled"));

            return true;
        }

        String message = String.join(" ", args);

        String bubbleTop = " " + "_".repeat(message.length() + 2);
        String bubbleMiddle = "< " + message + " >";
        String bubbleBottom = " " + "-".repeat(message.length() + 2);

        String playerName = sender.getName();

        if (isGlobalEnabled()) {

            for (Player player : Bukkit.getOnlinePlayers()) {
                sendCow(player, playerName, true, bubbleTop, bubbleMiddle, bubbleBottom);
            }

        } else {

            sendCow(sender, playerName, false, bubbleTop, bubbleMiddle, bubbleBottom);
        }

        return true;
    }

    private void sendCow(CommandSender receiver,
                         String playerName,
                         boolean showName,
                         String bubbleTop,
                         String bubbleMiddle,
                         String bubbleBottom) {

        if (showName) {
            receiver.sendMessage("<" + playerName + ">");
        }

        receiver.sendMessage(bubbleTop);
        receiver.sendMessage(bubbleMiddle);
        receiver.sendMessage(bubbleBottom);

        receiver.sendMessage("       \\   ^__^");
        receiver.sendMessage("        \\  (oo)\\_____");
        receiver.sendMessage("           (__)\\       )\\/");
        receiver.sendMessage("               ||----w |");
        receiver.sendMessage("               ||        ||");
    }
}
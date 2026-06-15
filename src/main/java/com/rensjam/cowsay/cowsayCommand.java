package com.rensjam.cowsay;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cowsayCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage("§cUsage: /cowsay <message>");
            return true;
        }

        String message = String.join(" ", args);

        String bubbleTop = " " + "_".repeat(message.length() + 2);
        String bubbleMiddle = "< " + message + " >";
        String bubbleBottom = " " + "-".repeat(message.length() + 2);

        String playerName = sender.getName();

        for (Player allPlayers : Bukkit.getOnlinePlayers()) {
            allPlayers.sendMessage("<" + playerName + ">");

            allPlayers.sendMessage(bubbleTop);
            allPlayers.sendMessage(bubbleMiddle);
            allPlayers.sendMessage(bubbleBottom);

            allPlayers.sendMessage("       \\   ^__^");
            allPlayers.sendMessage("        \\  (oo)\\_____");
            allPlayers.sendMessage("           (__)\\       )\\/\\");
            allPlayers.sendMessage("               ||----w |");
            allPlayers.sendMessage("               ||        ||");
        }

        return true;
    }

}

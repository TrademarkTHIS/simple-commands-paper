package me.arcademadness.simplecommands.teleport;

import me.arcademadness.simplecommands.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandTPAccept implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return false;
        Player playerSender = (Player) sender;
        Player requester = Main.getTPAManager().getRequest(playerSender);
        if (requester != null) {
            new FancyTeleport(requester, playerSender.getLocation());
            playerSender.sendMessage("Teleport accepted.");
            requester.sendMessage("Teleport accepted.");
            return true;
        }
        playerSender.sendMessage("No active teleport requests.");
        return true;
    }
}

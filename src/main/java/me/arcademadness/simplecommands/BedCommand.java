package me.arcademadness.simplecommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BedCommand implements CommandExecutor  {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (p.getBedSpawnLocation() != null) {
                p.teleport(p.getBedSpawnLocation());
                return true;
            }
        }
        return false;
    }
}

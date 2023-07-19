package me.arcademadness.simplecommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.Instant;

public class BedCommand implements CommandExecutor  {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            Instant i = Main.getLastHit(p);
            if (p.getBedSpawnLocation() == null) {
                p.sendMessage("You've gotta find a bed bro.");
                return true;
            }
            if (i != null) {
                if (Duration.between(i, Instant.now()).toMillis() < 5000) {
                    p.sendMessage("You're injured! Wait a few seconds bro.");
                    return true;
                }
            }
            p.teleport(p.getBedSpawnLocation());
            return true;
        }
        return false;
    }
}

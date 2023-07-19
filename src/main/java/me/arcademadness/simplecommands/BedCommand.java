package me.arcademadness.simplecommands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
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
                p.sendMessage(ChatColor.GRAY.toString() + ChatColor.ITALIC + "You hear a voice in your head whisper" + ChatColor.RESET + " 'You've gotta find a bed bro.' " + ChatColor.GRAY.toString() + ChatColor.ITALIC + "\nPerhaps you should listen to it...");
                return true;
            }
            if (i != null) {
                if (Duration.between(i, Instant.now()).toMillis() < 5000) {
                    p.sendMessage(ChatColor.GRAY.toString() + ChatColor.ITALIC + "You hear a voice in your head whisper" + ChatColor.RESET + " 'You're injured! Wait a few seconds bro.'");
                    return true;
                }
            }
            Location bed = p.getBedSpawnLocation();
            Location newBed = new Location(bed.getWorld(), bed.x(), bed.y(), bed.z(), p.getLocation().getYaw(), p.getLocation().getPitch());
            p.teleport(newBed);
            return true;
        }
        return false;
    }
}

package me.arcademadness.simplecommands.commands;

import me.arcademadness.simplecommands.Main;
import me.arcademadness.simplecommands.teleport.FancyTeleport;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.Instant;

public class CommandBed implements CommandExecutor  {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            Instant i = Main.getLastHit(p);
            if (p.getBedSpawnLocation() == null) {
                p.sendActionBar(ChatColor.GRAY.toString() + ChatColor.ITALIC + "You hear a voice in your head whisper");
                Component bedmsg = Component.text("You've gotta find a bed bro.",TextColor.fromHexString("#B9E8EA"));
                p.sendMessage(bedmsg);
                return true;
            }
            if (i != null) {
                if (Duration.between(i, Instant.now()).toMillis() < 15000) {
                    p.sendActionBar(ChatColor.GRAY.toString() + ChatColor.ITALIC + "You hear a voice in your head whisper");
                    Component combatmsg = Component.text("You were just in combat, cool down bro.",TextColor.fromHexString("#B9E8EA"));
                    p.sendMessage(combatmsg);
                    return true;
                }
            }

            Location bed = p.getBedSpawnLocation();
            new FancyTeleport(p, bed);
            return true;
        }
        return false;
    }


}

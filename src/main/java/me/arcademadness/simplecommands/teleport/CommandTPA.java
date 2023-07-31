package me.arcademadness.simplecommands.teleport;

import me.arcademadness.simplecommands.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.Instant;

public class CommandTPA implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) return false;
        if (!(sender instanceof Player)) return false;
        Player r = (Player) sender;

        if (r == target) {
            r.sendMessage("Dumbshit.");
            return true;
        }

        Instant i = Main.getLastHit(r);
        if (i != null) {
            if (Duration.between(i, Instant.now()).toMillis() < 15000) {
                r.sendActionBar(ChatColor.GRAY.toString() + ChatColor.ITALIC + "You hear a voice in your head whisper");
                Component combatmsg = Component.text("You were just in combat, cool down bro.",TextColor.fromHexString("#B9E8EA"));
                r.sendMessage(combatmsg);
                return true;
            }
        }
        Main.getTPAManager().addRequest(target, r);
        Component cmdMsg = Component.text("You have requested to teleport to ")
                .append(Component.text(target.getName(), TextColor.fromHexString("#00AAAA")))
                .append(Component.text(".", TextColor.fromHexString("#FFFFFF")));
        r.sendMessage(cmdMsg);

        cmdMsg = Component.text(r.getName(), TextColor.fromHexString("#00AAAA"))
                .append(Component.text(" has requested to teleport to you. \nUse ",TextColor.fromHexString("#FFFFFF")))
                .append(Component.text("/tpaccept",TextColor.fromHexString("#9876AA")))
                .append(Component.text(" to teleport them.",TextColor.fromHexString("#FFFFFF")));
        target.sendMessage(cmdMsg);
        return true;
    }
}

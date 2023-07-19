package me.arcademadness.simplecommands;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.TextReplacementConfig;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatColors implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String newMsg = ChatColor.translateAlternateColorCodes('&', event.getMessage());
        event.setMessage(newMsg);
    }
}

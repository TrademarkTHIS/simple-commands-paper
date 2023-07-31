package me.arcademadness.simplecommands.utils;

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

package me.arcademadness.simplecommands;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Instant;
import java.util.HashMap;

public final class Main extends JavaPlugin {

    private static Plugin plugin;

    static HashMap<Player, Instant> lastHit = new HashMap<>();


    @Override
    public void onEnable() {
        plugin = this;

        this.getCommand("bed").setExecutor(new BedCommand());
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerDamageEvent(), this);
        pm.registerEvents(new ChatColors(), this);
        pm.registerEvents(new ReverseEnchant(), this);
    }

    @Override
    public void onDisable() {
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    static public void setLastHit(Player p, Instant i) {
        lastHit.put(p, i);
    }

    public static Instant getLastHit(Player p) {
        return lastHit.get(p);
    }
}

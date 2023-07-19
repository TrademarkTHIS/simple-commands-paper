package me.arcademadness.simplecommands;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Instant;
import java.util.HashMap;

public final class Main extends JavaPlugin {

    static HashMap<Player, Instant> lastHit = new HashMap<>();

    @Override
    public void onEnable() {
        this.getCommand("bed").setExecutor(new BedCommand());
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerDamageEvent(), this);
        pm.registerEvents(new ChatColors(), this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    static public void setLastHit(Player p, Instant i) {
        lastHit.put(p, i);
    }

    public static Instant getLastHit(Player p) {
        return lastHit.get(p);
    }
}

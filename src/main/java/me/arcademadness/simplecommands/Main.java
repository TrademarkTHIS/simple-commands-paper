package me.arcademadness.simplecommands;

import me.arcademadness.simplecommands.commands.CommandBed;
import me.arcademadness.simplecommands.crafting.RepairAnvil;
import me.arcademadness.simplecommands.crafting.ReverseEnchant;
import me.arcademadness.simplecommands.effects.EatAStack;
import me.arcademadness.simplecommands.teleport.CommandTPA;
import me.arcademadness.simplecommands.teleport.CommandTPAccept;
import me.arcademadness.simplecommands.teleport.TPAManager;
import me.arcademadness.simplecommands.utils.ChatColors;
import me.arcademadness.simplecommands.utils.PlayerDamageEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Instant;
import java.util.HashMap;

public final class Main extends JavaPlugin {

    private static Plugin plugin;

    static HashMap<Player, Instant> lastHit = new HashMap<>();

    static TPAManager tpamanager = new TPAManager();

    @Override
    public void onEnable() {
        plugin = this;

        this.getCommand("bed").setExecutor(new CommandBed());
        this.getCommand("tpa").setExecutor(new CommandTPA());
        this.getCommand("tpaccept").setExecutor(new CommandTPAccept());
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerDamageEvent(), this);
        pm.registerEvents(new ChatColors(), this);
        pm.registerEvents(new ReverseEnchant(), this);
        pm.registerEvents(new EatAStack(), this);
        pm.registerEvents(new RepairAnvil(), this);
    }

    @Override
    public void onDisable() {
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static TPAManager getTPAManager() {return tpamanager; }

    static public void setLastHit(Player p, Instant i) {
        lastHit.put(p, i);
    }

    public static Instant getLastHit(Player p) {
        return lastHit.get(p);
    }
}

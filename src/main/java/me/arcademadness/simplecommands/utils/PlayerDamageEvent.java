package me.arcademadness.simplecommands.utils;

import me.arcademadness.simplecommands.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.time.Instant;

public class PlayerDamageEvent implements Listener {

    @EventHandler
    public void onPlayerDamaged(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL) return;
            Player p = (Player) event.getEntity();
            Main.setLastHit(p, Instant.now());
        }
    }
    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player p = (Player) event.getDamager();
            Main.setLastHit(p, Instant.now());
        }
    }
}

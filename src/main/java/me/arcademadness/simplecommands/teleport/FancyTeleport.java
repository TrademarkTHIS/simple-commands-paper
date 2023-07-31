package me.arcademadness.simplecommands.teleport;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.entity.Player;

public class FancyTeleport {

    public FancyTeleport(Player p, Location l) {
        Location newLoc = new Location(l.getWorld(), l.x(), l.y(), l.z(), p.getLocation().getYaw(), p.getLocation().getPitch());
        Location oldLoc = p.getLocation();
        p.teleport(newLoc);

        Component tpMsg = Component.text("Teleported", TextColor.fromHexString("#9876AA")).decorate(TextDecoration.ITALIC);
        p.sendActionBar(tpMsg);
        oldLoc.add(0, 1, 0);
        l.add(0,1,0);
        for (Player players : Bukkit.getOnlinePlayers()) {
            players.playEffect(oldLoc, Effect.ENDER_SIGNAL, Effect.ENDER_SIGNAL.getData());
            players.playSound(oldLoc, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1f);
            players.playSound(l, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1f);
        }
    }
}

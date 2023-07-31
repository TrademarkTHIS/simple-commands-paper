package me.arcademadness.simplecommands.teleport;

import org.bukkit.entity.Player;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;

public class TPAManager {
    private HashMap<Player, RequestObject> tpaRequests = new HashMap<>();

    public void addRequest(Player requested, Player requester) {
        RequestObject newRequest = new RequestObject(requester, Instant.now());
        tpaRequests.put(requested, newRequest);
    }

    public Player getRequest(Player requested) {
        Player requester = null;
        if (tpaRequests.get(requested) != null) {
            if (Duration.between(tpaRequests.get(requested).getAge(), Instant.now()).toMillis() < 30000) {
                requester = tpaRequests.get(requested).getRequester();
            }
        }
        tpaRequests.remove(requested);
        return requester;
    }
}

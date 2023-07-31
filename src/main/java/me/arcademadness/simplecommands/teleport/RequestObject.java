package me.arcademadness.simplecommands.teleport;

import org.bukkit.entity.Player;

import java.time.Instant;

public class RequestObject {

    Instant age;
    Player requester;

    public RequestObject(Player r, Instant a) {
        this.age = a;
        this.requester = r;
    }

    public Instant getAge() {
        return this.age;
    }

    public Player getRequester() {
        return this.requester;
    }
}

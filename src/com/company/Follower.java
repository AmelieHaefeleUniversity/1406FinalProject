package com.company;

public class Follower extends NPC {

    /**
     * Same as NPC but it's easier when creating characters to designate them as a follower or enemy
     * Follower constructor
     */
    public Follower(String playStyle, String name, int health, int actionPoints) {
        super(playStyle, name, health, actionPoints);
    }

}

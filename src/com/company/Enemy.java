package com.company;

/**
 * The difference from NPC is you can set the Enemies level
 */
public class Enemy extends NPC {
    Enemy(String playStyle, String name, int health, int actionPoints, int level) {
        super(playStyle, name, health, actionPoints);
        this._level = level;
    }
}

package com.company;

import java.util.HashMap;

public class enemy extends NPC {
    enemy(String playStyle,String name, int health, int actionPoints, int level){
        super(playStyle,name,health,actionPoints);
        this._level = level;
    }
}

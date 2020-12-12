package com.company;
import java.util.ArrayList;

public interface turn {
    public action getAction(ArrayList<Character> teamArray, ArrayList<Character> enemyArray, Character currentNPC);
    public Character getTarget(ArrayList<Character> teamArray, ArrayList<Character> oppositionArray, action givenAction);
}

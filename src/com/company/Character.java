package com.company;

import java.util.ArrayList;

public class Character {
    protected String _name;
    protected int _health;
    protected int _actionPoints;
    protected int _numCharacter;
    private Character[] characterArray = new Character[_numCharacter];
    //public HashMap<String, action> _actionList = new HashMap<String, action>();

    public Character(String name, int health, int actionPoints){
        this._name = name;
        this._health = health;
        this._actionPoints = actionPoints;
    }


    public void takeTurn(){
    }
    public int getTarget(){
       return -1;
    }
    public int getHealth(){
        return _health;
    }
    public String getName(){return _name;}
    public int getActionPoints(){return _actionPoints;}

    public void effect(action chosenAction){
        if (chosenAction.getStatEffect().equals("health")){
            this._health = _health + chosenAction.getEffect();
        }
        if (chosenAction.getStatEffect().equals("actionPoints")){
            this._actionPoints = _actionPoints + chosenAction.getEffect();
        }

    }

    public action getAction(ArrayList<Character> heroArray, ArrayList<Character> enemyArray, Character obj) {
        return new action(5,"harmful",5,"health");
    }

    public boolean enoughActionPoint(Character currentCharacter, action currentAction){
        return currentCharacter.getActionPoints() >= currentAction.getApCost();
    }
}




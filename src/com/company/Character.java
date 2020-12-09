package com.company;
import java.util.Random;
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

    //TODO: create random int generator
    public int d20(){
        Random _random = new Random();
        return _random.nextInt(21);

    }

    public void takeTurn(){
    }
    public Character getTarget(ArrayList<Character> _heroArray){
       Character error = new Character("Error",-1,-2);
       return error;
    }
    public int getHealth(){
        return _health;
    }
    public String getName(){return _name;}

    public int getActionPoints(){return _actionPoints;}

    public void effect(action chosenAction, Character doingAction, Character target){
        int ranInt = d20();
        if(chosenAction.getActionType().equals("neutral")){
            System.out.println(doingAction.getName()+" rested and regained "+chosenAction.getEffect()+" "+chosenAction.getStatEffect());
            return;
        }

        if (ranInt >= 10) {
            if(chosenAction.getActionType().equals("harmful")){
                System.out.println(doingAction.getName()+" rolled a "+ranInt+" hitting "+target.getName()+" for "+chosenAction.getEffect()+" "+chosenAction.getStatEffect());
            }
            if(chosenAction.getActionType().equals("helpful")){
                System.out.println(doingAction.getName()+" rolled a "+ranInt+" healing "+target.getName()+" for "+chosenAction.getEffect()+" "+chosenAction.getStatEffect());
            }
            else {
                System.out.println("Error action type doesn't match");
            }
            return;
        }
        else{
            System.out.println(doingAction.getName()+" rolled a "+ranInt+" missing "+target.getName());
        }

    }

    public action getAction(ArrayList<Character> heroArray, ArrayList<Character> enemyArray, Character obj) {
        return new action(5,"harmful",5,"health");
    }

    public boolean enoughActionPoint(Character currentCharacter, action currentAction){
        return currentCharacter.getActionPoints() >= currentAction.getApCost();
    }
}




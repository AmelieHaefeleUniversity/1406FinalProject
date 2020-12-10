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

    public int d20(){
        Random _random = new Random();
        return _random.nextInt(21);
    }

    public Character getTarget(ArrayList<Character> teamArray, ArrayList<Character> oppositionArray, action givenAction){
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
        String currentActionType= chosenAction.getActionType();
        if(currentActionType.equals("neutral")){
            System.out.println(doingAction.getName()+" rested and regained "+chosenAction.getEffect()+" "+chosenAction.getStatEffect());

            return;
        }

        if (ranInt >= 10) {
            if(chosenAction.getActionType().equals("harmful")){
                System.out.println(doingAction.getName()+" rolled a "+ranInt+" hitting "+target.getName()+" for "+chosenAction.getEffect()+" "+chosenAction.getStatEffect());
                return;
            }
            if(chosenAction.getActionType().equals("helpful")){
                System.out.println(doingAction.getName()+" rolled a "+ranInt+" healing "+target.getName()+" for "+chosenAction.getEffect()+" "+chosenAction.getStatEffect());
                return;
            }
        }
        else{
            System.out.println(doingAction.getName()+" rolled a "+ranInt+" missing "+target.getName());
            return;
        }

    }

    public action getAction(ArrayList<Character> teamArray, ArrayList<Character> enemyArray, Character currentNPC) {
        return new action(5,"harmful",5,"health");
    }

    public boolean enoughActionPoint(Character currentCharacter, action currentAction){
        int characterActionPoints = currentCharacter.getActionPoints();
        int neededActionPoints = currentAction.getApCost();
        boolean toBeReturned = characterActionPoints >= neededActionPoints;
        return toBeReturned;
    }
}




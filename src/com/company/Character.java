package com.company;
import java.util.Random;
import java.util.ArrayList;

public class Character {
    protected String _name;
    protected int _health;
    protected int _actionPoints;
    protected int _numCharacter;
    private Character[] characterArray = new Character[_numCharacter];
    protected int _healthCap;
    protected int _actionPointCap;
    public int _level;
    //public HashMap<String, action> _actionList = new HashMap<String, action>();

    public Character(String name, int health, int actionPoints){
        this._name = name;
        this._health = health;
        this._actionPoints = actionPoints;
        this._healthCap = health;
        this._actionPointCap = actionPoints;
        this._level = 1;
    }

    public int d20(){
        //Random _random = new Random();
        //return _random.nextInt(21);
        return 10;
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
            effectCharacter(chosenAction);
            System.out.println(doingAction.getName()+" rested and regained "+chosenAction.getEffect()+" "+chosenAction.getStatEffect());
            return;
        }

        if (ranInt >= 10) {
            effectCharacter(chosenAction);
            if(currentActionType.equals("harmful")){
                System.out.println(doingAction.getName()+" rolled a "+ranInt+" hitting "+target.getName()+" for "+chosenAction.getEffect()+" "+chosenAction.getStatEffect());
                return;
            }
            if(currentActionType.equals("helpful")){
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
        return new action(0,"harmful",0,"health");
    }

    //removes action points here
    public boolean enoughActionPoint(Character currentCharacter, action currentAction){
        if(currentCharacter.getActionPoints() >= currentAction.getApCost()){
            this._actionPoints = _actionPoints - currentAction.getApCost();
            return true;
        }
        return currentCharacter.getActionPoints() >= currentAction.getApCost();

    }

    //removes stats caused by actions here
    public void effectCharacter(action chosenAction) {
        if(chosenAction.getStatEffect().equals("health")){
            this._health = _health + chosenAction.getEffect();
            if (_health > _healthCap){
                this._health = _healthCap;
            }
        }
        if(chosenAction.getStatEffect().equals("actionPoints")){
            this._actionPoints = _actionPoints + chosenAction.getEffect();
            if (_actionPoints > _actionPointCap){
                this._actionPoints = _actionPointCap;
            }
        }
    }

    public void levelUp(){

    }
}




package com.company;
import jdk.jshell.spi.ExecutionControl;

import java.util.Random;
import java.util.ArrayList;

public class Character {
    final static public String REST_ACTION_TYPE = "neutral";
    final static public String HARM_ACTION_TYPE = "harmful";
    final static public String HELP_ACTION_TYPE = "helpful";
    final static public String HEALTH_STAT_EFFECT = "health";
    final static public String ACTION_POINTS_STAT_EFFECT = "actionPoints";

    protected String _name;
    protected int _health;
    protected int _actionPoints;
    protected int _healthCap;
    protected int _actionPointCap;
    protected int _level;  // Not public

    public Character(String name, int health, int actionPoints){
        this._name = name;
        this._health = health;
        this._actionPoints = actionPoints;
        this._healthCap = health;
        this._actionPointCap = actionPoints;
        this._level = 1;
    }

    protected int d20(){
        Random _random = new Random();
       return _random.nextInt(20);
    }

    public Character getTarget(ArrayList<Character> teamArray, ArrayList<Character> oppositionArray, Action givenAction){
        return new Character("Error",-1,-2);
    }

    public int getHealth(){
        return _health;
    }
    public String getName() {
        return _name;
    }
    public int getActionPoints() {
        return _actionPoints;
    }

    public void effect(Action chosenAction, Character doingAction, Character target){
        int ranInt = d20();

        String currentActionType= chosenAction.getActionType();
        if(currentActionType.equals(REST_ACTION_TYPE)){
            effectCharacter(chosenAction);
            System.out.println(doingAction.getName()+" rested and regained "+chosenAction.getEffect()+" "+chosenAction.getStatEffect());
            return;
        }

        if (ranInt >= 10) {
            effectCharacter(chosenAction);
            if(currentActionType.equals(HARM_ACTION_TYPE)){
                System.out.println(doingAction.getName()+" rolled a "+ranInt+" hitting "+target.getName()+" for "+chosenAction.getEffect()+" "+chosenAction.getStatEffect());
                increaseExperiencePoints();
                return;
            }
            if(currentActionType.equals(HELP_ACTION_TYPE)){
                System.out.println(doingAction.getName()+" rolled a "+ranInt+" healing "+target.getName()+" for "+chosenAction.getEffect()+" "+chosenAction.getStatEffect());
                increaseExperiencePoints();
                return;
            }
        }
        else{
            System.out.println(doingAction.getName()+" rolled a "+ranInt+" missing "+target.getName());
            return;
        }

    }

    public Action getAction(ArrayList<Character> teamArray, ArrayList<Character> enemyArray, Character currentNPC) {
        return new Action(0,HARM_ACTION_TYPE,0,HEALTH_STAT_EFFECT);
    }

    //removes action points here
    protected boolean checkAndChangeActionPoints(Character currentCharacter, Action currentAction){
        if(currentCharacter.getActionPoints() < currentAction.getApCost()){
            return false;
        }
        this._actionPoints = _actionPoints - currentAction.getApCost();
        return true;
    }

    //removes stats caused by actions here
    protected void effectCharacter(Action chosenAction) {
        if(chosenAction.getStatEffect().equals(HEALTH_STAT_EFFECT)){
            this._health = _health + chosenAction.getEffect();
            if (_health > _healthCap){
                this._health = _healthCap;
            }
        }
        if(chosenAction.getStatEffect().equals(ACTION_POINTS_STAT_EFFECT)){
            this._actionPoints = _actionPoints + chosenAction.getEffect();
            if (_actionPoints > _actionPointCap){
                this._actionPoints = _actionPointCap;
            }
        }
    }
    public void levelUp() {
        throw new RuntimeException("Unimplemented method");
    }
    public void increaseExperiencePoints() {
        //only gets added for player
    }
    public void addHopeSword() {
        throw new RuntimeException("Unimplemented method");
    }
}




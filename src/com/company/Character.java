package com.company;

import java.util.ArrayList;
import java.util.Random;

/**
 * Base class of all people in the game, both player and non-player characters.
 *
 * Keeps track of all relevant statistics for each character e.g. Action Points, Health.
 */
abstract public class Character {
    final static public String REST_ACTION_TYPE = "neutral";
    final static public String HARM_ACTION_TYPE = "harmful";
    final static public String HELP_ACTION_TYPE = "helpful";
    final static public String HEALTH_STAT_EFFECT = "health";
    final static public String ACTION_POINTS_STAT_EFFECT = "actionPoints";

    private String _name;
    protected int _health;
    protected int _actionPoints;
    protected int _healthCap;
    protected int _actionPointCap;
    protected int _level;

    public Character(String name, int health, int actionPoints) {
        this._name = name;
        this._health = health;
        this._actionPoints = actionPoints;
        this._healthCap = health;
        this._actionPointCap = actionPoints;
        this._level = 1;
    }

    protected int d20() {
        Random _random = new Random();
        return _random.nextInt(20);
    }

    public abstract Character getTarget(ArrayList<Character> teamArray, ArrayList<Character> oppositionArray, Action givenAction);

    public int getHealth() {
        return _health;
    }

    public String getName() {
        return _name;
    }

    public int getActionPoints() {
        return _actionPoints;
    }

    /**
     * Key method. Rolls the dice and applies results of action. Called on the target of the action.
     *
     * Also will adjust the character doing the action to add experience points.
     *
     * Also prints out what occurs.
     *
     * @param characterDoingAction Character doing action
     * @param chosenAction What the character is going to do
     */
    public void effect(Character characterDoingAction, Action chosenAction) {
        int ranInt = d20();

        String currentActionType = chosenAction.getActionType();
        if (currentActionType.equals(REST_ACTION_TYPE)) {
            this.effectCharacter(chosenAction);
            System.out.println(characterDoingAction.getName() + " rested and regained " + chosenAction.getEffect() + " " + chosenAction.getStatEffect());
            return;
        }

        if (ranInt >= 10) {
            effectCharacter(chosenAction);
            if (currentActionType.equals(HARM_ACTION_TYPE)) {
                System.out.println(characterDoingAction.getName() + " rolled a " + ranInt + " hitting " + this.getName() + " for " + chosenAction.getEffect() + " " + chosenAction.getStatEffect());
                characterDoingAction.increaseExperiencePoints();
                return;
            }
            if (currentActionType.equals(HELP_ACTION_TYPE)) {
                System.out.println(characterDoingAction.getName() + " rolled a " + ranInt + " healing " + this.getName() + " for " + chosenAction.getEffect() + " " + chosenAction.getStatEffect());
                characterDoingAction.increaseExperiencePoints();
                return;
            }
        } else {
            System.out.println(characterDoingAction.getName() + " rolled a " + ranInt + " missing " + this.getName());
            return;
        }
    }

    abstract public Action getAction(ArrayList<Character> teamArray, ArrayList<Character> enemyArray, Character currentNPC);

    protected boolean checkAndChangeActionPoints(Character currentCharacter, Action currentAction) {
        if (currentCharacter.getActionPoints() < currentAction.getApCost()) {
            return false;
        }
        this._actionPoints = _actionPoints - currentAction.getApCost();
        return true;
    }

    private void effectCharacter(Action chosenAction) {
        if (chosenAction.getStatEffect().equals(HEALTH_STAT_EFFECT)) {
            this._health = _health + chosenAction.getEffect();
            if (_health > _healthCap) {
                this._health = _healthCap;
            }
        }
        if (chosenAction.getStatEffect().equals(ACTION_POINTS_STAT_EFFECT)) {
            this._actionPoints = _actionPoints + chosenAction.getEffect();
            if (_actionPoints > _actionPointCap) {
                this._actionPoints = _actionPointCap;
            }
        }
    }

    abstract public void levelUp();

    public void increaseExperiencePoints() {
        //only gets added for player
    }
}




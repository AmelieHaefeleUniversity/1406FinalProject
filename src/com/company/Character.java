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

    private final String _name;
    protected int _health;
    protected int _actionPoints;
    protected int _healthCap;
    protected int _actionPointCap;
    protected int _level;

    /**
     * Super constructor for the player and NPC classes
     * @param name the name of the Character
     * @param health the Characters health
     * @param actionPoints the Characters action points
     */

    public Character(String name, int health, int actionPoints) {
        this._name = name;
        this._health = health;
        this._actionPoints = actionPoints;
        /**
         * Adds a cap so leveling up means more
         */
        this._healthCap = health;
        this._actionPointCap = actionPoints;
        this._level = 1;
    }

    /**
     * rolls the dice and returns a random number between 0-20
     * @return returns the random number
     */
    protected int d20() {
        Random _random = new Random();
        return _random.nextInt(20);
    }

    /**
     * abstract methods
     * @param teamArray the array the current character is fighting with
     * @param oppositionArray the array the current character is fighting against
     * @param givenAction which action the Character chose
     * @return returns the chosen action or target
     */
    abstract public Character getTarget(ArrayList<Character> teamArray, ArrayList<Character> oppositionArray, Action givenAction);
    abstract public Action getAction(ArrayList<Character> teamArray, ArrayList<Character> enemyArray, Character currentNPC);

    /**
     * Getter methods
     * @return returns the variables
     */
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
        String characterDoingActionName = characterDoingAction.getName();
        String targetName = this.getName();
        characterDoingActionName = characterDoingActionName.substring(0, 1).toUpperCase() + characterDoingActionName.substring(1);
        targetName = targetName.substring(0, 1).toUpperCase() + targetName.substring(1);

        String currentActionType = chosenAction.getActionType();
        if (currentActionType.equals(REST_ACTION_TYPE)) {
            this.effectCharacter(chosenAction);
            System.out.println(characterDoingActionName + " "+chosenAction.getActionName()+"ed and regained " + chosenAction.getEffect() + " " + chosenAction.getStatEffect());
            return;
        }

        if (ranInt >= 10) {
            effectCharacter(chosenAction);
            if (currentActionType.equals(HARM_ACTION_TYPE)) {
                System.out.println(characterDoingActionName + " rolled a and " + ranInt + " "+chosenAction.getActionName()+"ed " + targetName + " for " + chosenAction.getEffect() + " " + chosenAction.getStatEffect());
                characterDoingAction.increaseExperiencePoints();
            }
            if (currentActionType.equals(HELP_ACTION_TYPE)) {
                System.out.println(characterDoingActionName + " rolled a and " + ranInt + " "+chosenAction.getActionName()+"ed " + targetName+ " for " + chosenAction.getEffect() + " " + chosenAction.getStatEffect());
                characterDoingAction.increaseExperiencePoints();
            }
        } else {
            System.out.println(characterDoingActionName + " rolled a " + ranInt + " missing " + targetName+" with a/an "+chosenAction.getActionName());
        }
    }

    /**
     * Checks if the character has enough action points to do the given action
     * if they have enough action points it subtracts the number of action points needed to do the action
     * @param currentCharacter the character preforming the action
     * @param currentAction the action said character is preforming
     * @return returns true or false if the character can preform their chosen action
     */
    protected boolean checkAndChangeActionPoints(Character currentCharacter, Action currentAction) {
        if (currentCharacter.getActionPoints() < currentAction.getApCost()) {
            return false;
        }
        this._actionPoints = _actionPoints - currentAction.getApCost();
        return true;
    }

    /**
     * since the effect method is called on the target this method does the chosen action on the target
     * @param chosenAction what the current action is
     */
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

    /**
     * abstract method
     */
    abstract public void levelUp();

    /**
     * has implementation in the player class, not abstract because it needs to be called for each successful action
     * added it here so it doesn't need to check if the chosen characterDoingAction is a player
     */
    public void increaseExperiencePoints() {
        //only gets added for player
    }
}




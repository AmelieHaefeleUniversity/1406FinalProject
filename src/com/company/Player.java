package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
Represents the human player all actions and targets are gotten through user input
 */
public class Player extends Character {
    final static public String ACTION_POINTS_STATS_EFFECT = "action points";
    private HashMap<String, Action> _playerActionList;
    private int _experiencePoints;

    /**
     * Player constructor different form character as it has an action list and experience points
     * @param name the player's name
     */
    public Player(String name) {
        super(name, 20, 15);
        _playerActionList = ActionList.getActionList();
        this._experiencePoints = 0;
    }

    /**
     * Prints out the list of possible targets based on whether the action is helpful and harmful
     * then asks the player to input the name
     * checks if their input is valid and will re prompt them if not
     * @param teamArray the array of players the player is fighting alongside
     * @param oppositionArray the array of characters the player is fighting against
     * @param givenAction what action the player previously chose
     * @return returns the player's chosen target
     */
    public Character getTarget(ArrayList<Character> teamArray, ArrayList<Character> oppositionArray, Action givenAction) {
        ArrayList<Character> targetArray = new ArrayList<Character>();
        if (givenAction.getActionType().equals(HELP_ACTION_TYPE)) {
            targetArray = teamArray;

        }
        if (givenAction.getActionType().equals(HARM_ACTION_TYPE)) {
            targetArray = oppositionArray;
        }
        if (targetArray.size() == 1){
            System.out.println("Targeting "+targetArray.get(0).getName() + " as they fight on alone...\n");
            return targetArray.get(0);
        }
        System.out.println("Target List:\n");
        for (Character value : targetArray) {
            String target = value.getName();
            target = target.substring(0, 1).toUpperCase() + target.substring(1);
            System.out.println(target);
        }
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\nWho would you like to target?\n");
            String targetName = input.nextLine().toLowerCase();
            for (Character character : targetArray) {
                String givenArrayName = character.getName();
                if (targetName.equals(givenArrayName)) {
                    return character;
                }
            }
            System.out.println("Please enter a valid Target\n");
        }

    }

    /**
     * prints out all actions the player can preform
     * asks for the players inputted action
     * if the action is not valid it will re prompt them to enter a valid one
     * @param heroArray the array of players the player is fighting alongside
     * @param enemyArray the array of characters the player is fighting against
     * @param playerCharacter the player
     * @return returns the players chosen action
     */
    public Action getAction(ArrayList<Character> heroArray, ArrayList<Character> enemyArray, Character playerCharacter) {
        System.out.println("\nAction List:\n");
        for (String actionName : _playerActionList.keySet()) {
            Action currentAction = _playerActionList.get(actionName);
            int currentActionPointCost = currentAction.getApCost();
            int currentEffect = currentAction.getEffect();
            String currentActionType = currentAction.getActionType();
            String currentStatEffected = currentAction.getStatEffect();
            actionName = actionName.substring(0, 1).toUpperCase() + actionName.substring(1);
            System.out.println(actionName + ": \tAction Point Cost:" + currentActionPointCost + " \tAction Type:" + currentActionType + " \tAction Effect:" + currentEffect + " \tStat Effected:" + currentStatEffected);
        }
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\nWhat action would you like to do?\n");
            String actionName = input.nextLine().toLowerCase();
            if (_playerActionList.get(actionName) != null) {
                if (checkAndChangeActionPoints(playerCharacter, _playerActionList.get(actionName))) {
                    return _playerActionList.get(actionName);
                } else {
                    System.out.println("Error not enough action points!");
                }
            }
            System.out.println("Please enter a valid action\n");
        }
    }

    /**
     * Will ask the player what they want to level up, health or action points
     * if they don't enter a valid option they will be re prompted until they enter a valid one
     * sets the players experience points back to zero
     */
    public void levelUp() {
        this._level = this._level + 1;
        this._experiencePoints = 0;
        levelLootSystem(_level);
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\n Congrats you leveled up! \n Would you like to increase you overall health or action points by 5?\n");
            String toIncrease = input.nextLine().toLowerCase();
            if (toIncrease.equals(HEALTH_STAT_EFFECT)) {
                this._healthCap = _healthCap + 5;
                this._health = _healthCap;
                this._actionPoints = _actionPointCap;
                return;
            }
            if (toIncrease.equals(ACTION_POINTS_STATS_EFFECT)) {
                this._actionPointCap = _actionPointCap + 5;
                this._actionPoints = _actionPointCap;
                this._health = _healthCap;
                return;
            }
            System.out.println("Please enter a valid stat to increase, for example input (health or action points)");

        }

    }

    /**
     * The player will get new actions based on their level
     * @param level the current player's level
     */
    public void levelLootSystem(int level) {
        if (level == 2) {
            Action firework = new Action(3, HARM_ACTION_TYPE, -6, HEALTH_STAT_EFFECT,"firework");
            _playerActionList.put("firework", firework);
            System.out.println("Congrats you found a new action\n Cause baby you're a fiiiiiiirewooooork\n");
            System.out.println("Firework" + "\nAction Point Cost:" + firework.getApCost() + " \tAction Type:" + firework.getActionType() + " \tAction Effect:" + firework.getEffect()+ " \tStat Effected:" + firework.getStatEffect() + "\n");
        }
        if (level == 3) {
            Action examsOver = new Action(0, REST_ACTION_TYPE, 5, ACTION_POINTS_STATS_EFFECT,"exam over rest");
            _playerActionList.put("exams over", examsOver);
            System.out.println("Congrats you found a new action\n I can't wait for this one\n");
            System.out.println("Exams Over" + "\nAction Point Cost:" + examsOver.getApCost() + " \tAction Type:" + examsOver.getActionType() + " \tAction Effect:" + examsOver.getEffect()+ " \tStat Effected:" + examsOver.getStatEffect() + "\n");
        }

    }

    /**
     * each successful action will increase the players action points by 50
     */
    public void increaseExperiencePoints() {
        this._experiencePoints = _experiencePoints + 50;
    }

    /**
     * Getter method for PlayGame
     * @return returns the variables from players
     */
    public int getExperiencePoints() {
        return _experiencePoints;
    }

    /**
     * Special method that adds a new action for the boss fight
     */
    public void addHopeSword() {
        Action hopeSword = new Action(2, HARM_ACTION_TYPE, -10, HEALTH_STAT_EFFECT,"hope sword slash");
        _playerActionList.put("hope sword", hopeSword);
        System.out.println("Firework" + "\nAction Point Cost:" + hopeSword.getApCost() + " \tAction Type:" + hopeSword.getActionType() + " \tAction Effect:" + hopeSword.getEffect()+ " \tStat Effected:" + hopeSword.getStatEffect() + "\n");
    }

}

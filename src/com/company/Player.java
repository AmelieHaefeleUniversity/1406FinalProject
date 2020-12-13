package com.company;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class Player extends Character {
    ActionList _objActionList = new ActionList();
    public HashMap<String, Action> _playerActionList;
    private ArrayList<Character> _givenArray;
    private int _experiencePoints;
    final static public String ACTION_POINTS_STATS_EFFECT = "actionPoints";

    public Player(String name){
        super(name, 20, 15);
        _playerActionList = _objActionList.getPlayerActions();
        this._experiencePoints = 0;
    }

    public Character getTarget(ArrayList<Character> teamArray, ArrayList<Character> oppositionArray, Action givenAction){
        if (givenAction.getActionType().equals(HELP_ACTION_TYPE)){
            this._givenArray = teamArray;

        }
        if (givenAction.getActionType().equals(HARM_ACTION_TYPE)) {
            this._givenArray = oppositionArray;
        }
        System.out.println("Target List:\n");
        for (int i = 0; i < _givenArray.size(); i++){
            String target = _givenArray.get(i).getName();
            target = target.substring(0, 1).toUpperCase() + target.substring(1);
            System.out.println(target);
        }
        Scanner input = new Scanner(System.in);
        while(true){
           System.out.println("\nWho would you like to target?\n");
            String targetName = input.nextLine().toLowerCase();
            for (Character character : _givenArray) {
                String givenArrayName = character.getName();
                if (targetName.equals(givenArrayName)) {
                    return character;
                }
            }
            System.out.println("Please enter a valid Target\n");
        }

    }

    public Action getAction(ArrayList<Character> heroArray, ArrayList<Character> enemyArray, Character obj){
        System.out.println("\nAction List:\n");
        for (String actionName: _playerActionList.keySet() ){
            Action currentAction = _playerActionList.get(actionName);
            int currentActionPointCost = currentAction.getApCost();
            int currentEffect = currentAction.getEffect();
            String currentActionType = currentAction.getActionType();
            String currentStatEffected = currentAction.getStatEffect();
            actionName  = actionName.substring(0, 1).toUpperCase() + actionName.substring(1);
            System.out.println(actionName +"\nAction Point Cost:"+currentActionPointCost+" \tAction Type:"+currentActionType+" \tAction Effect:"+currentEffect+" \tStat Effected:"+currentStatEffected+"\n");
        }
        Scanner input = new Scanner(System.in);

        while(true){
            System.out.println("\nWhat action would you like to do?\n");
            String actionName = input.nextLine().toLowerCase();
            if(_playerActionList.get(actionName) != null){
                if(checkAndChangeActionPoints(obj,_playerActionList.get(actionName))){
                    return _playerActionList.get(actionName);
                }
                else{
                    System.out.println("Error not enough action points!");
                }
            }
            System.out.println("Please enter a valid action\n");
        }
    }

    public void levelUp(){
        this._level = _level +1;
        this._experiencePoints = 0;
        levelLootSystem(_level);
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("Would you like to increase you overall health or action points by 5?\n");
            String toIncrease = input.nextLine().toLowerCase();
            if(toIncrease.equals(HEALTH_STAT_EFFECT)){
                this._healthCap = _healthCap + 5;
                this._health = _healthCap;
                this._actionPoints = _actionPointCap;
                return;
            }
            if(toIncrease.equals(ACTION_POINTS_STATS_EFFECT)){
                this._actionPointCap = _actionPointCap +5;
                this._actionPoints = _actionPointCap;
                this._health = _healthCap;
                return;
            }
            System.out.println("Please enter a valid stat to increase, for example input (health or action points)");

        }

    }

    public void levelLootSystem (int level){
        if (level == 2){
            Action firework = new Action(3,HARM_ACTION_TYPE,-6,HEALTH_STAT_EFFECT);
            _playerActionList.put("firework",firework);
        }
        if(level == 3) {
            Action examsOver = new Action(0,REST_ACTION_TYPE, 5, ACTION_POINTS_STATS_EFFECT);
            _playerActionList.put("exams over", examsOver);
        }

    }

    public void increaseExperiencePoints(){
        this._experiencePoints = _experiencePoints + 50;
    }

    public int getExperiencePoints(){
        return _experiencePoints;
    }

    public void addHopeSword(){
        Action hopeSword = new Action(2,HARM_ACTION_TYPE,-10,HEALTH_STAT_EFFECT);
        _playerActionList.put("Hope Sword",hopeSword);
    }

}

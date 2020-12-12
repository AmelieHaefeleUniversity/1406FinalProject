package com.company;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class player extends Character {
    actionList _objActionList = new actionList();
    public HashMap<String, action> _playerActionList;
    private ArrayList<Character> _givenArray;
    private int _experiencePoints;

    public player(String name){
        super(name, 20, 15);
        _playerActionList = _objActionList.getPlayerActions();
        this._experiencePoints = 0;
    }

    //TODO: finish getTarget
    public Character getTarget(ArrayList<Character> teamArray, ArrayList<Character> oppositionArray, action givenAction){
        if (givenAction.getActionType().equals("helpful")){
            this._givenArray = teamArray;

        }
        if (givenAction.getActionType().equals("harmful")) {
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

    public action getAction(ArrayList<Character> heroArray, ArrayList<Character> enemyArray, Character obj){
        System.out.println("\nAction List:\n");
        for (String actionName: _playerActionList.keySet() ){
            action currentAction = _playerActionList.get(actionName);
            int currentActionPointCost = currentAction.getApCost();
            int currentEffect = currentAction.getEffect();
            String currentActionType = currentAction.getActionType();
            String currentStatEffected = currentAction.getActionType();
            actionName  = actionName.substring(0, 1).toUpperCase() + actionName.substring(1);
            System.out.println(actionName +"\nAction Point Cost:"+currentActionPointCost+" \tAction Type:"+currentActionType+" \tAction Effect:"+currentEffect+" \tStat Effected:"+currentStatEffected+"\n");
        }
        Scanner input = new Scanner(System.in);

        while(true){
            System.out.println("\nWhat action would you like to do?\n");
            String actionName = input.nextLine().toLowerCase();
            if(_playerActionList.get(actionName) != null){
                if(enoughActionPoint(obj,_playerActionList.get(actionName))){
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
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("Would you like to increase you overall health or action points by 5?\n");
            String toIncrease = input.nextLine().toLowerCase();
            if(toIncrease.equals("health")){
                this._healthCap = _healthCap + 5;
                this._health = _healthCap;
                return;
            }
            if(toIncrease.equals("action points")){
                this._actionPointCap = _actionPointCap +5;
                this._actionPoints = _actionPointCap;
                return;
            }
            System.out.println("Please enter a valid stat to increase, for example input (health or action points)");

        }

    }
    public void levelLootSystem (int level){
        if (level == 2){
            action firework = new action(3,"harmful",-6,"health");
            _playerActionList.put("firework",firework);
        }
        if(level == 3) {
            action examsOver = new action(0, "helpful", 5, "actionPoints");
            _playerActionList.put("exams over", examsOver);
        }

    }

    public void increaseExperiencePoints(){
        this._experiencePoints = _experiencePoints + 20;
    }
    public int getExperiencePoints(){
        return _experiencePoints;
    }

}

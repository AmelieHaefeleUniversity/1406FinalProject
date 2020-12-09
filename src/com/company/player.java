package com.company;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class player extends Character {
    actionList _objActionList = new actionList();
    public HashMap<String, action> _playerActionList;
    private ArrayList<Character> _givenArray;

    public player(String name){
        super(name, 20, 15);
        _playerActionList = _objActionList.getPlayerActions();

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
            System.out.println(_givenArray.get(i).getName());
        }
        Scanner input2 = new Scanner(System.in);
        while(true){
           System.out.println("\nWho would you like to target?\n");
            String targetName = input2.nextLine();
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
        System.out.println("Action List:\n");
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
        System.out.println("\nWhat action would you like to do?\n");
        String actionName = input.nextLine();

        if(enoughActionPoint(obj,_playerActionList.get(actionName))){
            action chosenAction = _playerActionList.get(actionName);
            return chosenAction;
        }

        while (true){
            System.out.println("Please enter a valid action\n");
            System.out.println("What action would you like to do?\n");
            actionName = input.nextLine();
            if (_playerActionList.containsKey(actionName)){
                if(enoughActionPoint(obj,_playerActionList.get(actionName))){
                    return _playerActionList.get(actionName);
                }
                else{
                    System.out.println("Error not enough action points!");
                }
            }
        }
    }



}

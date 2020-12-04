package com.company;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class player extends Character {
    actionList _objActionList = new actionList();
    public HashMap<String, action> _playerActionList;

    public player(String name){
        super(name, 20, 15);
        _playerActionList = _objActionList.getPlayerActions();

    }

    //TODO: finish getTarget
    public Character getTarget(ArrayList<Character> givenArray){
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("Who would you like to target?\n");
            String targetName = input.nextLine();
            for (Character character : givenArray) {
                String givenArrayName = character.getName();
                if (targetName.equals(givenArrayName)) {
                    return character;
                }
            }
            System.out.println("Please enter a valid action\n");
        }

    }

    public action getAction(ArrayList<Character> heroArray, ArrayList<Character> enemyArray, Character obj){
        Scanner input = new Scanner(System.in);

        while (true){
            System.out.println("What action would you like to do?\n");
            String actionName = input.nextLine();
            if (_playerActionList.containsKey(actionName)){
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



}

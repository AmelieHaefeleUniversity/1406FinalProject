package com.company;

import java.util.HashMap;
import java.util.ArrayList;

public class fight {

    public ArrayList<Character> _heroArray;
    public ArrayList<Character> _enemyArray;
    private Character _target;
    private action _action;
    actionList _objActionList = new actionList();
    public HashMap<String, action> masterActionList;
    public ArrayList<Character> _charactersInFight;

    public fight(ArrayList<Character> heroArray, ArrayList<Character> enemyArray) {
        this._heroArray = heroArray;
        this._enemyArray = enemyArray;
        masterActionList = _objActionList.getPlayerActions();
        this._charactersInFight = addLists(heroArray, enemyArray);
    }

    //TODO: deep copy the hero Lists and full heal them after every fight so you can remove them from the list of possible targets
    public boolean playFight() {
        boolean _play = true;
        while (_play) {
            for (int i = 0; i< _heroArray.size(); i++){
                // Checks if the player has won
                //always pass in the player first so this can be checked
                //TODO: Check if you make _enemy Array null or the size equal to zero when you remove all objects inside it
                if (_enemyArray.size() == 0) {
                    return true;
                }

                // Checks if the player got removed "dead"
                if (_heroArray.get(0) == null) {
                    return false;
                }
                heroTurn(i);

            }
            for (int i = 0; i< _enemyArray.size(); i++){
                // Checks if the player has won
                //always pass in the player first so this can be checked
                if (_enemyArray.size() == 0) {
                    return true;
                }

                // Checks if the player got removed "dead"
                if (_heroArray.get(0) == null) {
                    return false;
                }
                enemyTurn(i);

            }


        }
        return false;
    }
    private void heroTurn(int i){
        // Sets the current character to get info from, so they can have their turn
        Character obj = _heroArray.get(i);

        // Checks if the current Character is dead, if so they are removed from the array of possible characters
        if (!checkDead(obj,i)){
            return;
        }
        this._action = obj.getAction(_heroArray,_enemyArray,obj);
        String chosenActionType = _action.getActionType();
        if (chosenActionType.equals("neural")){
            //set target as current persons turn
            this._target = _heroArray.get(i);
        }
        if (chosenActionType.equals("helpful")){
            this._target = obj.getTarget(_heroArray);

        }
        if (chosenActionType.equals("harmful")) {
            this._target = obj.getTarget(_enemyArray);
        }
        _target.effect(_action);

    }
    private void enemyTurn (int i){
        // Sets the current character to get info from, so they can have their turn
        Character obj = _enemyArray.get(i);

        // Checks if the current Character is dead, if so they are removed from the array of possible characters
        if (!checkDead(obj,i)){
            return;
        }
        this._action = obj.getAction(_enemyArray, _heroArray, obj);
        String chosenActionType = _action.getActionType();;
        if (chosenActionType.equals("neural")){
            //set target as current persons turn
            this._target = _enemyArray.get(i);
        }
        if (chosenActionType.equals("helpful")){
            //this.target = obj.getTarget(_enemyArray)

        }
        if (chosenActionType.equals("harmful")) {
            //this.target = obj.getTarget(_heroArray)
        }
        _target.effect(_action);

    }

    private boolean checkDead(Character obj, int i){
        // Checks if the current character is dead
        if (obj.getHealth() <= 0){
            _charactersInFight.remove(i);
            // removes the character from their original array so it can be properly passed into the chosen getTarget Array
            if (_heroArray.contains(obj)){
                _heroArray.remove(obj);
            }
            else{
                _enemyArray.remove(obj);
            }
            return false;
        }
        else{
            return true;
        }
    }

    private ArrayList<Character> addLists(ArrayList<Character> list1, ArrayList<Character> list2){
        ArrayList<Character> result = new ArrayList<Character>();
        int i = 0;
        while (i < list1.size()){
            result.add(i, list1.get(i));
            i++;
        }
        int j = 0;
        while (j < list2.size()){
            result.add(i, list2.get(j));
            i++;
            j++;
        }
        return result;
    }
}


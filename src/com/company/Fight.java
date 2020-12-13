package com.company;
import java.util.ArrayList;

public class Fight {

    private ArrayList<Character> _heroArray;
    private ArrayList<Character> _enemyArray;
    private Character _target;
    private Action _action;
    private ArrayList<Character> _charactersInFight;
    final static public String REST_ACTION_TYPE = "neutral";
    final static public String HARM_ACTION_TYPE = "harmful";
    final static public String HELP_ACTION_TYPE = "helpful";

    public Fight(ArrayList<Character> heroArray, ArrayList<Character> enemyArray) {
        this._heroArray = heroArray;
        this._enemyArray = enemyArray;
        this._charactersInFight = addLists(heroArray, enemyArray);
    }

    public boolean playFight() {
        boolean _play = true;
        while (_play) {
            checkDeadPlayers();
            // Checks if someone won
            if (_enemyArray.size() == 0) {
                System.out.println("Congrats you won!");
                return true;
            }
            // Checks if the player got removed "dead"
            if (_heroArray.size() == 0) {
                System.out.println("You lost better luck next time");
                return false;
            }
            //For loops don't work if there's only one item in the array list
            printCharacterStats();
            for (int i = 0; i< _heroArray.size(); i++){
                //always pass in the player first so this can be checked
                // Checks if someone won
                checkDeadPlayers();
                if (_enemyArray.size() == 0) {
                    System.out.println("Congrats you won!");
                    return true;
                }

                // Checks if the player got removed "dead"
                if (_heroArray.size() == 0) {
                    System.out.println("You lost better luck next time");
                    return false;
                }
                turn(_heroArray,_enemyArray,i);

            }
            checkDeadPlayers();
            for (int j = 0; j< _enemyArray.size(); j++){
                // Checks if the player has won
                //always pass in the player first so this can be checked
                checkDeadPlayers();
                if (_enemyArray.size() == 0) {
                    System.out.println("Congrats you won!");
                    return true;
                }

                // Checks if the player got removed "dead"
                if (_heroArray.size() == 0) {
                    System.out.println("You lost better luck next time");
                    return false;
                }
                turn(_enemyArray,_heroArray,j);
            }
        }
        System.out.println("Error this point should not be read");
        return false;
    }
    private void checkDeadPlayers(){
        for(int i = 0; i <_charactersInFight.size(); i++){
            Character currentCharacter = _charactersInFight.get(i);
            checkDead(currentCharacter);
        }
    }

    // PH methods start with a lower case
    private void turn(ArrayList<Character> AlliedArray , ArrayList<Character> MobArray, int i){
        // Sets the current character to get info from, so they can have their turn
        checkDeadPlayers();
        Character currentCharacter = AlliedArray.get(i);  // currentCharacter is bae variable name PH

        // Checks if the current Character is dead, if so they are removed from the array of possible characters
        if (!checkDead(currentCharacter)){
            return;
        }
        this._action = currentCharacter.getAction(AlliedArray,MobArray,currentCharacter);
        String chosenActionType = _action.getActionType();
        if (chosenActionType.equals(REST_ACTION_TYPE)){
            //set target as current persons turn
            this._target = AlliedArray.get(i);
        }
        if (chosenActionType.equals(HELP_ACTION_TYPE)){
            this._target = currentCharacter.getTarget(AlliedArray,MobArray,_action);

        }
        if (chosenActionType.equals(HARM_ACTION_TYPE)) {
            this._target = currentCharacter.getTarget(AlliedArray,MobArray,_action);
        }
        _target.effect(_action, currentCharacter, _target);

    }

    private boolean checkDead(Character currentCharacter){
        // Checks if the current character is dead
        if (currentCharacter.getHealth() <= 0){
            if (_heroArray.contains(currentCharacter)){
                _heroArray.remove(currentCharacter);
            }
            else{
                _enemyArray.remove(currentCharacter);
            }
            return false;
        }
        else{
            return true;
        }
    }

    private ArrayList<Character> addLists(ArrayList<Character> list1, ArrayList<Character> list2){
        ArrayList<Character> result = new ArrayList<>();
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

    private void printCharacterStats(){
        System.out.println("\nYour Team:");
        for (int i = 0; i< _heroArray.size(); i++) {
            System.out.println(_heroArray.get(i).getName().substring(0, 1).toUpperCase() + _heroArray.get(i).getName().substring(1)+":\tHealth:"+_heroArray.get(i).getHealth()+"\tAction Points:"+_heroArray.get(i).getActionPoints());
        }

        System.out.println("\nEnemy Team:");
        for (int j = 0; j< _enemyArray.size(); j++){
            System.out.println(_enemyArray.get(j).getName().substring(0, 1).toUpperCase() + _enemyArray.get(j).getName().substring(1)+":\tHealth:"+_enemyArray.get(j).getHealth()+"\tAction Points:"+_enemyArray.get(j).getActionPoints());
        }
    }
}


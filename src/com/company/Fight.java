package com.company;

import java.util.ArrayList;

public class Fight {
    /**
     * Setting up String variables and needed Arrays
     */
    final static public String REST_ACTION_TYPE = "neutral";
    private final ArrayList<Character> _heroArray;
    private final ArrayList<Character> _deadHeroArray;
    private final ArrayList<Character> _deadEnemyArray;
    private final ArrayList<Character> _enemyArray;
    private final ArrayList<Character> _charactersInFight;

    /**
     * Fight Constructor
     * @param heroArray the player and follower array
     * @param enemyArray the given enemy array
     */
    public Fight(ArrayList<Character> heroArray, ArrayList<Character> enemyArray) {
        this._heroArray = heroArray;
        this._enemyArray = enemyArray;
        this._charactersInFight = addLists(heroArray, enemyArray);
        this._deadHeroArray = new ArrayList<>();
        this._deadEnemyArray = new ArrayList<>();
    }

    /**
     * Main fight method
     * @return returns true if the player won, false if they lost
     */
    public boolean playFight() {
        while (true) {
            checkDeadPlayers();
            /**
             * Checks after all turns and before each turn if someone has done
             */
            if (_enemyArray.size() == 0) {
                System.out.println("Congrats you won!");
                return true;
            }
            if (_heroArray.size() == 0) {
                System.out.println("You lost better luck next time");
                return false;
            }
            /**
             * Hero team turn including the player and their two followers
             */
            printCharacterStats();
            for (int i = 0; i < _heroArray.size(); i++) {
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
                turn(_heroArray, _enemyArray, i);

            }
            checkDeadPlayers();
            /**
             * Enemy team turn
             */
            for (int j = 0; j < _enemyArray.size(); j++) {
                checkDeadPlayers();
                if (_enemyArray.size() == 0) {
                    System.out.println("Congrats you won!");
                    return true;
                }
                if (_heroArray.size() == 0) {
                    System.out.println("You lost better luck next time");
                    return false;
                }
                turn(_enemyArray, _heroArray, j);
            }
        }
        /**
         * Prints out an error if reached
         */
    }

    /**
     * Goes through all characters and does checkDead() on them
     */
    private void checkDeadPlayers() {
        for (Character currentCharacter : _charactersInFight) {
            checkDead(currentCharacter);
        }
    }

    /**
     * Main turn method
     * @param alliedArray the array the current character is fighting alongside
     * @param oppositionArray the array the current character is fighting against
     * @param i which character in the array the turn is currently on
     */
    private void turn(ArrayList<Character> alliedArray, ArrayList<Character> oppositionArray, int i) {
        checkDeadPlayers();
        Character currentCharacter = alliedArray.get(i);

        /**
         * Checks if the current Character is dead, if so they are removed from the array of possible characters and doesn't get to have their turn
         */
        if (!checkDead(currentCharacter)) {
            return;
        }
        Character target;
        Action action = currentCharacter.getAction(alliedArray, oppositionArray, currentCharacter);
        String chosenActionType = action.getActionType();

        if (chosenActionType.equals(REST_ACTION_TYPE)) {
            /**
             * Set target as current persons turn because you can only rest yourself, you can rest for others
             */
            target = alliedArray.get(i);
        }
        else{
            target = currentCharacter.getTarget(alliedArray, oppositionArray, action);
        }
        target.effect(currentCharacter, action);

    }

    private boolean checkDead(Character currentCharacter) {
        /**
         * Checks if the character being looked at is dead
         */
        if (currentCharacter.getHealth() <= 0) {
            if (_heroArray.contains(currentCharacter)) {
                _deadHeroArray.add(currentCharacter);
                _heroArray.remove(currentCharacter);
            }
            if (_enemyArray.contains(currentCharacter)){
                _deadEnemyArray.add(currentCharacter);
                _enemyArray.remove(currentCharacter);
            }
            return false;
        }
        else {
            return true;
        }
    }

    public void restoreAllCharacters() {
        /**
         * Restore all the characters
         */
        _heroArray.addAll(_deadHeroArray);
        _deadHeroArray.clear();
        _enemyArray.addAll(_deadEnemyArray);
        _deadEnemyArray.clear();

        /**
         * Move Player to front of list - hack but hey
         * Otherwise it keep going, this will change the order
         * of turns based on who dies first but deep copying is unrealistic to do with Arrays
         * but theoretically could be done
         * Set player to first position again as it looks better, but doesn't matter as the game will still play
         * normally
         */
        for (int i = 0; i < _heroArray.size(); i++) {
            if (_heroArray.get(i) instanceof Player) {
                Character temp = _heroArray.get(0);
                _heroArray.set(0, _heroArray.get(i));
                _heroArray.set(i,temp);
            }
        }

        for (Character p: _charactersInFight){
            p.restoreCharacter();
        }
    }

    /**
     * Adds both list so checkDeadPlayers can go through all characters at once
     * @param list1 first array given
     * @param list2 second array given
     * @return returns the combined two lists
     */
    private ArrayList<Character> addLists(ArrayList<Character> list1, ArrayList<Character> list2) {
        ArrayList<Character> result = new ArrayList<>();
        int i = 0;
        while (i < list1.size()) {
            result.add(i, list1.get(i));
            i++;
        }
        int j = 0;
        while (j < list2.size()) {
            result.add(i, list2.get(j));
            i++;
            j++;
        }
        return result;
    }

    /**
     * Goes through both lists and prints out their information
     */
    private void printCharacterStats() {
        System.out.println("\nYour Team:");
        for (Character character : _heroArray) {
            System.out.println(character.getName().substring(0, 1).toUpperCase() + character.getName().substring(1) + ":\tHealth:" + character.getHealth() + "\tAction Points:" + character.getActionPoints());
        }

        System.out.println("\nEnemy Team:");
        for (Character character : _enemyArray) {
            System.out.println(character.getName().substring(0, 1).toUpperCase() + character.getName().substring(1) + ":\tHealth:" + character.getHealth() + "\tAction Points:" + character.getActionPoints());
        }
    }

}


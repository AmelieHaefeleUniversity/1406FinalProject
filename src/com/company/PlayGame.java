package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayGame {
    /**
     * Setting up the variables needed for this class
     */
    public String _playerName;
    private ArrayList<Character> _playerTeam = new ArrayList<>();
    private final ArrayList<Fight> _fightList = new ArrayList<>();
    private Player _playerCharacter;

    /**
     * Constructor that takes the player name
     * @param playerName the chosen player's name
     */
    public PlayGame(String playerName) {
        this._playerName = playerName;
    }

    /**
     * Play game main method
     */
    public void playGameMethod() {
        createNPCsAndFights();
        boolean fightHappening;
        PrintMethods.printRule();
        PrintMethods.printIntro();
        /**
         * Cycles through all the fights
         */
        for (int i = 0; i < _fightList.size(); i++) {
            if (i == 2) {
                _playerCharacter.addHopeSword();
            }
            final Fight fight = _fightList.get(i);
            fightHappening = true;
            while (fightHappening) {

                /**
                 * Keeps going until the player wins or quits the game
                 * will resume on the current fight they died on
                 */
                PrintMethods.printFightIntro(i);

                /**
                 * Do Fight
                 */
                if (!fight.playFight()) {
                    if (!keepGoing()) {
                        System.out.println("Better luck next time");
                        return;
                    }
                } else {
                    fightHappening = false;
                }
                /**
                 * Get all characters healed up for the next round or a try-again.
                 */
                fight.restoreAllCharacters();
            }
            /**
             * if the player has more than or equal to 100 experience points the leveled up method is called on all Characters in the good guy lsit
             */
            int playerExperiencePoints = _playerCharacter.getExperiencePoints();
            if (playerExperiencePoints >= 100)
                for (Character character : _playerTeam) {
                    character.levelUp();
                }
        }
        /**
         * Once the user completes the game the winning method is printed out and the game ends
         */
        PrintMethods.printCompletedGame();

    }

    /**
     * Asks if they player wants to keep going if a valid action isn't inputted the user will be re prompted to enter a valid one
     * @return returns true if they player wants to keep going returns false if they don't want to
     */
    private boolean keepGoing() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Would you like to continue (yes/no)");
            String answer = input.nextLine().toLowerCase();
            if (answer.equals("yes")) {
                return true;
            }
            if (answer.equals("no")) {
                return false;
            }
            System.out.println("Please enter yes or no");
        }
    }

    /**
     * Creates all needed NCPs and puts them into their respected arrays
     */
    private void createNPCsAndFights() {
        ArrayList<Character> fightOneEnemyList = new ArrayList<>();
        ArrayList<Character> fightTwoEnemyList = new ArrayList<>();
        ArrayList<Character> fightThreeEnemyList = new ArrayList<>();

        Enemy alastair = new Enemy("fighter", "alastair", 15, 10, 1);
        Enemy prescott = new Enemy("spellCaster", "prescott", 10, 15, 1);

        fightOneEnemyList.add(0, alastair);  // Could make fightOneEnemyList as local variable an then add to fights PH
        fightOneEnemyList.add(1, prescott);

        Enemy fluffy = new Enemy("healer", "fluffy", 15, 20, 2);
        Enemy hoppy = new Enemy("spellCaster", "hoppy", 10, 20, 2);
        Enemy cinnabun = new Enemy("fighter", "cinnabun", 20, 10, 2);

        fightTwoEnemyList.add(0, fluffy);
        fightTwoEnemyList.add(1, hoppy);
        fightTwoEnemyList.add(2, cinnabun);

        Enemy officium = new Enemy("spellCaster", "officium", 50, 20, 5);
        fightThreeEnemyList.add(0, officium);

        Follower peter = new Follower("healer", "peter", 1, 15);
        Follower danielle = new Follower("fighter", "danielle", 1, 15);
        Player playerCharacter = new Player(_playerName);
        /**
         * Puts them into their array list
         */
        _playerTeam.add(0, playerCharacter);
        this._playerCharacter = playerCharacter;
        _playerTeam.add(1, peter);
        _playerTeam.add(2, danielle);

        _fightList.add(new Fight(_playerTeam, fightOneEnemyList));
        _fightList.add(new Fight(_playerTeam, fightTwoEnemyList));
        _fightList.add(new Fight(_playerTeam, fightThreeEnemyList));
    }
}

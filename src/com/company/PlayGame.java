package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayGame {
    /**
     * Setting up the variables needed for this class
     */
    public String _playerName;
    private final ArrayList<Enemy> _fightOne = new ArrayList<>();
    private final ArrayList<Enemy> _fightTwo = new ArrayList<>();
    private final ArrayList<Enemy> _fightThree = new ArrayList<>();
    private ArrayList<Character> _playerTeam = new ArrayList<>();
    private final ArrayList<ArrayList> _fights = new ArrayList<>();
    private ArrayList<Character> _playerTeamDeepCopy = new ArrayList<>();
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
        PrintMethods printMethod = new PrintMethods();
        createNPCs();
        boolean fightHappening;
        printMethod.printRule();
        printMethod.printIntro();
        /**
         * Cycles through all the fights
         */
        for (int i = 0; i < _fights.size(); i++) {
            if (i == 2) {
                _playerCharacter.addHopeSword();
            }
            fightHappening = true;
            while (fightHappening) {
                /**
                 * Keeps going until the player wins or quits the game
                 * will resume on the current fight they died on
                 */
                printMethod.printFightIntro(i);
                /**
                 * Copies the player before the fight so players removed from the fight(died) will be in the next fight
                 */
                _playerTeamDeepCopy = _playerTeam;
                Fight fightSetUp = new Fight(_playerTeam, _fights.get(i));
                if (!fightSetUp.playFight()) {
                    keepGoing();
                    if (!keepGoing()) {
                        System.out.println("Better luck next time");
                        return;
                    }
                } else {
                    fightHappening = false;
                }
                _playerTeam = _playerTeamDeepCopy;
                /**
                 * if the player has more than or equal to 100 experience points the leveled up method is called on all Characters in the good guy lsit
                 */
                int ep = _playerCharacter.getExperiencePoints();
                if (ep >= 100)
                    for (Character character : _playerTeam) {
                        character.levelUp();
                    }
            }
        }
        /**
         * Once the user completes the game the winning method is printed out and the game ends
         */
        printMethod.printCompletedGame();

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
    private void createNPCs() {
        Enemy alastair = new Enemy("fighter", "alastair", 15, 10, 1);
        Enemy prescott = new Enemy("spellCaster", "prescott", 10, 15, 1);

        _fightOne.add(0, alastair);  // Could make fightOne as local variable an then add to fights PH
        _fightOne.add(1, prescott);

        Enemy fluffy = new Enemy("healer", "fluffy", 15, 20, 2);
        Enemy hoppy = new Enemy("spellCaster", "hoppy", 10, 20, 2);
        Enemy cinnabun = new Enemy("fighter", "cinnabun", 20, 10, 2);

        _fightTwo.add(0, fluffy);
        _fightTwo.add(1, hoppy);
        _fightTwo.add(2, cinnabun);

        Enemy officium = new Enemy("spellCaster", "officium", 50, 20, 5);
        _fightThree.add(0, officium);

        Follower peter = new Follower("healer", "peter", 20, 15);
        Follower danielle = new Follower("fighter", "danielle", 20, 15);
        Player playerCharacter = new Player(_playerName);
        /**
         * Puts them into their array list
         */
        _playerTeam.add(0, playerCharacter);
        this._playerCharacter = playerCharacter;
        _playerTeam.add(1, peter);
        _playerTeam.add(2, danielle);

        _fights.add(0, _fightOne);
        _fights.add(1, _fightTwo);
        _fights.add(2, _fightThree);

    }
}

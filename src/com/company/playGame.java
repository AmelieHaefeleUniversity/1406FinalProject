package com.company;
import java.util.Scanner;
import java.util.ArrayList;

public class playGame {
    public String _playerName;
    private ArrayList<enemy> _fightOne;
    private ArrayList<enemy> _fightTwo;
    private ArrayList<enemy> _fightThree;
    private ArrayList<Character> _playerTeam;
    private ArrayList<ArrayList> _fights;
    private player _playerCharacter;

    public playGame(String playerName){
        this._playerName = playerName;
    }


    public void playGameMethod(){
        printMethods printMethod = new printMethods();
        setNPC();
        boolean fightHappening = true;
        printMethod.printRule();
        printMethod.printIntro();
        for(int i = 0; i < _fights.size();i++){
            fightHappening = true;
            while(true){
                printMethod.printFightIntro(i);
                fight fightSetUp = new fight(_playerTeam,_fights.get(i));
                if (!fightSetUp.playFight()){
                    keepGoing();
                    if (!keepGoing()){
                        System.out.println("Better luck next time");
                        return;
                    }
                }
                else {
                    fightHappening = false;
                }
                if(_playerCharacter.getExperiencePoints() >= 100)
                for(int j = 0; j < _playerTeam.size();i++){
                    _playerTeam.get(i).levelUp();
                }
            }

        }

    }
    public boolean keepGoing(){
        Scanner input = new Scanner(System.in);
        while(true) {
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

    public void setNPC(){
        enemy alastair = new enemy("fighter","Alastair",15,10,1);
        enemy prescott = new enemy("spellCaster","Prescott",10,15,1);

        _fightOne.add(0,alastair);
        _fightOne.add(1,prescott);

        enemy fluffy = new enemy("healer","Fluffy",15,20,2);
        enemy hoppy = new enemy("spellCaster","Hoppy",10,20,2);
        enemy cinnabun = new enemy("fighter","Cinnabun",20,10,2);

        _fightTwo.add(0,fluffy);
        _fightTwo.add(1,hoppy);
        _fightTwo.add(2,cinnabun);

        enemy officium = new enemy("spellCaster","officium",50,20,5);
        _fightThree.add(0,officium);

        follower peter = new follower("healer","Peter",20,15);
        follower danielle = new follower("fighter","Danielle",20,15);
        player playerCharacter = new player(_playerName);
        _playerTeam.add(0, playerCharacter);
        _playerTeam.add(1,peter);
        _playerTeam.add(2,danielle);

        _fights.add(0,_fightOne);
        _fights.add(1,_fightTwo);
        _fights.add(2,_fightThree);

    }
}

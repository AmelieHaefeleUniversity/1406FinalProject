package com.company;
import java.util.Scanner;
import java.util.ArrayList;

public class PlayGame {  // Capital PH
    public String _playerName;
    private ArrayList<Enemy> _fightOne= new ArrayList<Enemy>();
    private ArrayList<Enemy> _fightTwo= new ArrayList<Enemy>();
    private ArrayList<Enemy> _fightThree= new ArrayList<Enemy>();
    private ArrayList<Character> _playerTeam= new ArrayList<Character>();
    private ArrayList<ArrayList> _fights= new ArrayList<ArrayList>();
    private ArrayList<Character> _playerTeamDeepCopy= new ArrayList<Character>();
    // Just have the fights list PH
    private Player _playerCharacter;

    public PlayGame(String playerName){
        this._playerName = playerName;
    }

    public void playGameMethod() {
        PrintMethods printMethod = new PrintMethods();
        setNPC();
        boolean fightHappening = true;
        printMethod.printRule();
        printMethod.printIntro();
        for(int i = 0; i < _fights.size();i++){
            if (i == 2){
                _playerCharacter.addHopeSword();
            }
            fightHappening = true;
            while(fightHappening){
                printMethod.printFightIntro(i);
                _playerTeamDeepCopy = _playerTeam;
                Fight fightSetUp = new Fight(_playerTeam,_fights.get(i));
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
                this._playerTeam = _playerTeamDeepCopy;
                int ep = _playerCharacter.getExperiencePoints();
                if(ep >= 100)
                for(int j = 0; j < _playerTeam.size();j++){
                    _playerTeam.get(j).levelUp();
                }
            }
        }
        printMethod.printCompletedGame();
        return;

    }
    private boolean keepGoing(){
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("Would you like to continue (yes/no)");
            String answer = input.nextLine().toLowerCase();
            if (answer.equals("yes")) {   // equalsIgnoreCase?
                return true;
            }
            if (answer.equals("no")) {
                return false;
            }
            System.out.println("Please enter yes or no");
        }
    }

    private void setNPC(){  // Rename createNPCs.
        Enemy alastair = new Enemy("fighter","alastair",15,10,1);
        Enemy prescott = new Enemy("spellCaster","prescott",10,15,1);

        _fightOne.add(0,alastair);  // Could make fightOne as local variable an then add to fights PH
        _fightOne.add(1,prescott);

        Enemy fluffy = new Enemy("healer","fluffy",15,20,2);
        Enemy hoppy = new Enemy("spellCaster","hoppy",10,20,2);
        Enemy cinnabun = new Enemy("fighter","cinnabun",20,10,2);

        _fightTwo.add(0,fluffy);
        _fightTwo.add(1,hoppy);
        _fightTwo.add(2,cinnabun);

        Enemy officium = new Enemy("spellCaster","officium",50,20,5);
        _fightThree.add(0,officium);

        Follower peter = new Follower("healer","peter",20,15);
        Follower danielle = new Follower("fighter","danielle",20,15);
        Player playerCharacter = new Player(_playerName);
        _playerTeam.add(0, playerCharacter);
        this._playerCharacter = playerCharacter;
        _playerTeam.add(1,peter);
        _playerTeam.add(2,danielle);

        _fights.add(0,_fightOne);
        _fights.add(1,_fightTwo);
        _fights.add(2,_fightThree);

    }
}

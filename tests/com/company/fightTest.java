package com.company;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class fightTest {

    @org.junit.Test
    public void playFight() {
        enemy Carl = new enemy("fighter","Carl",20,15,1);
        follower Macey = new follower("fighter","Macey",20,15);
        player Amelie = new player("Amelie");
        ArrayList<Character> _testEnemyArray = new ArrayList<>();
        ArrayList<Character> _testHeroArray = new ArrayList<>();
        _testEnemyArray.add(Carl);
        _testHeroArray.add(Amelie);
        _testHeroArray.add(Macey);
        Amelie.levelUp();

        //fight testFight = new fight(_testHeroArray,_testEnemyArray);
        //testFight.playFight();
    }

    @org.junit.Test
    //Insert certain charters alive or dead to check
    public void checkDead() {
    }

    @org.junit.Test
    //this should work but you should at least write 1 test
    public void addLists() {
    }
}
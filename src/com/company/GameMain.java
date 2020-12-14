package com.company;
import java.util.Scanner;

/**
 * Asks the user for a name and plays the game
 */
public class GameMain {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("What's your name?");
        String playerName = input.nextLine().toLowerCase();
        PlayGame Game = new PlayGame(playerName);
        Game.playGameMethod();
    }
}
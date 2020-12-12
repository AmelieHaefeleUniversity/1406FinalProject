package com.company;

public class printMethods {

    public void printRule(){
        System.out.println(("\nTo move through the game to get to another screen simply press enter or anything you want when prompted by\n"+
                "the console. At the start of your turn a list of all actions you can preform and information about them \n"+
                "will be displayed, simply type in the action you want to preform then hit enter. Similarly a list of\n"+
                "possible targets will be displayed. Enter the full name of your target into the console and hit enter.\n"+
                "Remember to have fun. Good luck!"));
    }

    public void printIntro(){
        System.out.println("You enter the land of Sènes where some say the land is as old as time itself flanked on either side by your friends Peter and Danielle. Peter smiled towards the horizon, he’s been your friend for forever, a kind soul, the meaning of lawful good. Danielle you’ve known for a couple of years but you grew close quickly, she doesn’t seem as interested, a chaotic neutral. “This is stupid, Chaotic Neutral? What does that even mean! This game is too nerdy if people at school knew we were playing this we’d never live it down.” she said angrily while biting into a large piece of pizza. “Hey, Peter set this up for us just be quite your running the emerson.” You reply scowling at Danielle. Peter gave an awkward smile and continued. This evil world is controlled by a powerful wizard named Officium! “What’s that latin for? Dumbass?” Danielle laughed quietly to herself. It’s your job as a young adventurers to destroy his evil grip on the world and find peace. Good Luck!");
    }

    public void printFightIntro(int i){
        if(i == 0){
            System.out.println("fight one intro here");
        }
        if (i == 1){
            System.out.println("fight two intro here");
        }
        if(i == 2){
            System.out.println("fight three intro here");
        }
    }


}

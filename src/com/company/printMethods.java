package com.company;

public class printMethods {

    public void printRule(){
        System.out.println(("\n\nTo move through the game to get to another screen simply press enter or anything you want when prompted by\n"+
                "the console. At the start of your turn a list of all actions you can preform and information about them \n"+
                "will be displayed, simply type in the action you want to preform then hit enter. Similarly a list of\n"+
                "possible targets will be displayed. Enter the full name of your target into the console and hit enter.\n"+
                "Remember to have fun. Good luck!"));
    }

    public void printIntro(){
        System.out.println("\n\nYou enter the land of Sènes where some say the land is as old as time itself flanked on either side by your\n" +
                " friends Peter and Danielle. Peter smiled towards the horizon, he’s been your friend for forever, a kind soul, the meaning\n" +
                " of lawful good. Danielle you’ve known for a couple of years but you grew close quickly, she doesn’t seem as interested, a\n" +
                " chaotic neutral. “This is stupid, Chaotic Neutral? What does that even mean! This game is too nerdy if people at school \n" +
                "knew we were playing this we’d never live it down.” she said angrily while biting into a large piece of pizza. “Hey, Peter \n" +
                "set this up for us just be quite your running the emerson.” You reply scowling at Danielle. Peter gave an awkward smile and \n" +
                "continued. This evil world is controlled by a powerful wizard named Officium! “What’s that latin for? Dumbass?” Danielle \n" +
                "laughed quietly to herself. \n It’s your job as a young adventurers to destroy his evil grip on the world and find peace. Good Luck!");
    }

    public void printFightIntro(int i){
        if(i == 0){
            System.out.println("\n\nAfter a while of traveling you enter a small forest clearing. “I roll a perception check.” you say while rolling a dice,\n" +
                    " “Yes a 16!”. You listen carefully and hear what sound like two voices in the background. They jump out brandishing their fierce \n" +
                    "claws! Some goblins approach and attack you! \n You encountered: \n Goblin 1 and Goblin \n They don't look too difficult \n Goblin" +
                    " 2: Hey! My name's Alastair and this is my friend Prescott. I think we deserve to actually have our names said!\n With that Danielle " +
                    "smiles and lets a small chuckle but quickly hides it. \"Uhhh ok \n You encountered: \n Alastair \n Prescott" +
                    " \n Alastair: Thank you, now prepare to die! \n");
        }
        if (i == 1){
            System.out.println("\n\n“Ha we totally kicked their butts!” Danielle exclaimed excitedly. Congrats adventures you’ve completed the first battle but what comes next.\n" +
                    " A while passes and your party has grown, not in numbers but, as some would say, wisdom. Peter seemed distracted while frantically writing something down.\n" +
                    "“It’s time to chill dude this is our night of fun before the big exam,” you say smiling. “I can’t ‘chill’ this decides what highschool what we get into,\n" +
                    "which determines our university chances, which determines what jobs well get, and if I don’t get into a good university, then I won’t get a good job, and\n" +
                    "if-” Peter’s ranting was cut off short by Danielle who had heard this spiral too many times to count “and if you don’t get a good job, you won’t make money,\n" +
                    " and if you don’t make money, you’ll be homeless and live on the streets,” she said laughing. “Precisely,” Peter replied, grabbing a red bull. “Stop spiraling,\n" +
                    " you'll be fine you’re the smartest kid in our class just please, take a break,” you say pleadingly. “Fine,” Peter replies, putting down his notepad.\n" +
                    "“It’s all gonna be fine,” Danielle says laughing. You pass through a small town just as a couple villagers run past you fleeing from something.\n" +
                    "“Looks like someone needs our help!” Danelle says nudging Peter. From the commotion emerges what look like two small rabbits. Just as Peter begins\n" +
                    "to laugh one of the bunnies latch onto a fleeing villager ripping their throat out. \n" +
                    "You encountered:\nFluffy\nHoppy\nand Cinnabun\nVery fitting names.\nI wonder what they might be in reference to?\n");
        }
        if(i == 2){
            System.out.println("\n\nYou enter the dark and eerie castle of Officum alone. The dark shades of purple protruding from the castle grounds seemed to have seeped into\n" +
                    " the lush forest like some horrible plague. The sky is dark and just the sight of this large castle ahead of you fills you with dread trying to strip out\n" +
                    "any hope you may still be holding onto. But you enter anyway because there’s no way but forward. \n" +
                    "You feel your body giving up as you shrink to the floorgasping for air “It’s not fair!” You yell into the void, but no one answers.\n" +
                    "Just as you start to feel the unfamiliar grasp of defeat set in a bright spark fills your heart healing you to full health. “Hey idiot\n" +
                    "you didn’t think you could get through this alone could you?” Danelle said standing at your side. “Ya c’mon dude we’re always here for\n" +
                    "you!” Peter said running up and hugging you. Danelle smiled, giving you a hard but friendly pat on the back, “Let’s beat this nobody\n" +
                    "together, no point in doing it alone.” You feel a sense of hope rising in your chest, and this hope becomes your weapon. Congrats! You\n" +
                    "gain the sword of hope, I don’t have time to come up with a more creative name so it’ll have to do. With your friends at your side, you\n" +
                    "may fall down, you may struggle but in the end you will win!\n");
        }
    }

    public void printCompletedGame(){
        System.out.println("\n\n“So that's the end,” Peter said smiling “we won and conquered evil!”. “Don’t worry I’m sure this won’t be the last challenge we’ll\n" +
                " have to face,” Danelle replied. “I think that's a good note to leave it on tonight though,” you say. “Good timing wanna head down to grab a\n" +
                "drink, there’s someone very special I’d like you guys to meet,” Danelle said, grabbing her coat and keys. You and Peter both give each other an\n" +
                "excited look and get ready to go. This particular campaign ends but it’s not over for our young-ish adventures and their team may grow in time.\n" +
                "There will be more struggles, more hardships, but one thing that stays constant is your support.\n" +
                "So don’t forget those who are close to you because you’re never truly alone!\n");
    }


}

package com.company;

import java.util.HashMap;

public class actionList {
    public HashMap<String, action> getPlayerActions;
    private action fireball = new action(5,"harmful",-5,"health");
    private action rest = new action(0,"neural",2,"actionPoints");
    private action heal = new action(3,"helpful",4,"health");
    public HashMap<String, action> playerActions = new HashMap<String, action>();
    public HashMap<String, action> masterActionList = new HashMap<String, action>();
    public HashMap<String, action> NPCActionList = new HashMap<String, action>();

    //TODO: finish making action lists for the player, followers, and enemies, unlockable actions
    //TODO: CREATE A MASTER LIST OF ALL ACTIONS THAT THE FIGHT CLASS CAN REFERENCE



    public HashMap<String, action> getPlayerActions(){
        playerActions.put("fireball",fireball);
        playerActions.put("rest",rest);
        playerActions.put("heal",heal);
        return playerActions;
    }

    public HashMap<String, action> getMasterActions(){
        playerActions.put("fireball",fireball);
        playerActions.put("rest",rest);
        playerActions.put("heal",heal);
        return masterActionList;
    }

    public HashMap<String, action> getNPCActions(){
        playerActions.put("fireball",fireball);
        playerActions.put("rest",rest);
        playerActions.put("heal",heal);
        return NPCActionList;
    }
}

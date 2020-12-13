package com.company;

import java.util.HashMap;

public class ActionList {  // Start with Capital PH
    private Action fireball = new Action(5,HARM_ACTION_TYPE,-5,HEALTH_STAT_EFFECT);
    private Action rest = new Action(0,REST_ACTION_TYPE,2,ACTION_POINTS_STATS_EFFECT);
    private Action heal = new Action(3,HELP_ACTION_TYPE,4,HEALTH_STAT_EFFECT);
    private Action stab = new Action(2,HARM_ACTION_TYPE,-3,HEALTH_STAT_EFFECT);
    public HashMap<String, Action> playerActions = new HashMap<String, Action>();
    public HashMap<String, Action> masterActionList = new HashMap<String, Action>();  // Unused? PH
    public HashMap<String, Action> NPCActionList = new HashMap<String, Action>();
    final static public String REST_ACTION_TYPE = "neutral";
    final static public String HARM_ACTION_TYPE = "harmful";
    final static public String HELP_ACTION_TYPE = "helpful";
    final static public String HEALTH_STAT_EFFECT = "health";
    final static public String ACTION_POINTS_STATS_EFFECT = "actionPoints";

    public HashMap<String, Action> getPlayerActions(){
        playerActions.put("fireball",fireball);
        playerActions.put("rest",rest);
        playerActions.put("heal",heal);
        playerActions.put("stab",stab);
        return playerActions;
    }

    public HashMap<String, Action> getNPCActions(){
        NPCActionList.put("fireball",fireball);
        NPCActionList.put("rest",rest);
        NPCActionList.put("heal",heal);
        NPCActionList.put("stab",stab);
        return NPCActionList;
    }
}

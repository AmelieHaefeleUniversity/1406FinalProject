package com.company;

import java.util.HashMap;

public class ActionList {
    private final static Action fireball = new Action(5, Character.HARM_ACTION_TYPE, -5, Character.HEALTH_STAT_EFFECT);
    private final static Action rest = new Action(0, Character.REST_ACTION_TYPE, 2, Character.ACTION_POINTS_STAT_EFFECT);
    private final static Action heal = new Action(3, Character.HELP_ACTION_TYPE, 4, Character.HEALTH_STAT_EFFECT);
    private final static Action stab = new Action(2, Character.HARM_ACTION_TYPE, -3, Character.HEALTH_STAT_EFFECT);

    /**
     * Utility class with no constructor
     */
    private ActionList() {
    }

    static public HashMap<String, Action> getPlayerActions() {
        HashMap<String, Action> playerActions = new HashMap<String, Action>();
        playerActions.put("fireball", fireball);
        playerActions.put("rest", rest);
        playerActions.put("heal", heal);
        playerActions.put("stab", stab);
        return playerActions;
    }

    static public HashMap<String, Action> getNPCActions() {
        HashMap<String, Action> NPCActionList = new HashMap<String, Action>();
        NPCActionList.put("fireball", fireball);
        NPCActionList.put("rest", rest);
        NPCActionList.put("heal", heal);
        NPCActionList.put("stab", stab);
        return NPCActionList;
    }
}

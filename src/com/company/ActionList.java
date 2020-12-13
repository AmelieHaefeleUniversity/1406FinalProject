package com.company;

import java.util.HashMap;

public class ActionList {
    /**
     * Utility Class to store information and variables
     */
    private final static Action fireball = new Action(5, Character.HARM_ACTION_TYPE, -5, Character.HEALTH_STAT_EFFECT,"fireball");
    private final static Action rest = new Action(0, Character.REST_ACTION_TYPE, 2, Character.ACTION_POINTS_STAT_EFFECT,"res");
    private final static Action heal = new Action(3, Character.HELP_ACTION_TYPE, 4, Character.HEALTH_STAT_EFFECT,"heal");
    private final static Action stab = new Action(2, Character.HARM_ACTION_TYPE, -3, Character.HEALTH_STAT_EFFECT,"stab");

    /**
     * Utility class with no constructor
     */
    private ActionList() {
    }

    /**
     * Getter method that returns the starting player and action lists
     * @return returns the action list
     */
    static public HashMap<String, Action> getActionList() {
        HashMap<String, Action> actionList;
        actionList = new HashMap<>();
        actionList.put("fireball", fireball);
        actionList.put("rest", rest);
        actionList.put("heal", heal);
        actionList.put("stab", stab);
        return actionList;
    }
}

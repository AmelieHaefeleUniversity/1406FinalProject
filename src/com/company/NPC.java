package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class NPC extends Character {
    /**
     * Set up String variables
     */
    final static public String HEALER_TYPE = "healer";
    final static public String SPELL_CASTER_TYPE = "spellCaster";
    final static public String FIGHTER_TYPE = "fighter";
    final static public String FIREBALL_ACTION = "fireball";
    final static public String HEAL_ACTION = "heal";
    final static public String REST_ACTION = "rest";
    final static public String STAB_ACTION = "stab";

    /**
     * Setting up class variables
     */
    private final String _playStyle;
    private final HashMap<String, Action> _NPCActionList;

    /**
     * NPC constructor different from characters as they also have a playstyle
     * @param playStyle healer, fighter, or spell caster, defines what actions and targets the AI will chose
     * @param name the NPCs name
     * @param health the NPCs health
     * @param actionPoints the NPCs action points
     */
    public NPC(String playStyle, String name, int health, int actionPoints) {
        super(name, health, actionPoints);
        this._NPCActionList = ActionList.getActionList();
        this._playStyle = playStyle;
    }

    /**
     * Get Action based on play style
     * @param teamArray the array the current character is fighting alongside
     * @param enemyArray the array the current character is fighting
     * @param currentNPC the current character getting an action
     * @return returns the action
     */
    public Action getAction(ArrayList<Character> teamArray, ArrayList<Character> enemyArray, Character currentNPC) {
        if (_playStyle.equals(HEALER_TYPE)) {
            return getHealerAction(teamArray, currentNPC);
        }
        if (_playStyle.equals(FIGHTER_TYPE)) {
            return getFighterAction(currentNPC);
        }
        if (_playStyle.equals(SPELL_CASTER_TYPE)) {
            return getSpellCasterAction(currentNPC);
        }
        return null;
    }

    /**
     * Checks if anyone needs to be healed if not checks if they have enough action points to fireball if not they rest
     * @param teamArray the array the current character is fighting alongside
     * @param currentNPC the current character getting an action
     * @return returns the healer AI's chosen action
     */
    private Action getHealerAction(ArrayList<Character> teamArray, Character currentNPC) {
        for (Character character : teamArray) {
            if (character.getHealth() < 15) {
                if (checkAndChangeActionPoints(currentNPC, _NPCActionList.get(HEAL_ACTION))) {
                    return _NPCActionList.get(HEAL_ACTION);
                }
            }
        }
        // Return something else please
        if (checkAndChangeActionPoints(currentNPC, _NPCActionList.get(FIREBALL_ACTION))) {
            return _NPCActionList.get(FIREBALL_ACTION);
        }
        return _NPCActionList.get(REST_ACTION);
    }

    private Action getFighterAction(Character currentNPC) {
        // change to melee attack that costs less Action Points
        if (checkAndChangeActionPoints(currentNPC, _NPCActionList.get(STAB_ACTION))) {
            return _NPCActionList.get(STAB_ACTION);
        }
        return _NPCActionList.get(REST_ACTION);

    }

    /**
     * checks if they have enough action points to fireball if not they rest
     * @param currentNPC the current character getting an action
     * @return returns the spell casters AI's chosen action
     */
    private Action getSpellCasterAction(Character currentNPC) {
        if (checkAndChangeActionPoints(currentNPC, _NPCActionList.get(FIREBALL_ACTION))) {
            return _NPCActionList.get(FIREBALL_ACTION);
        }
        return _NPCActionList.get(REST_ACTION);

    }

    /**
     * Get target dependant on play style
     * @param teamArray the array the current character is fighting alongside
     * @param oppositionArray the array the current character is fighting against
     * @param givenAction the action the character AI already chose
     * @return returns the AI chosen character
     */
    public Character getTarget(ArrayList<Character> teamArray, ArrayList<Character> oppositionArray, Action givenAction) {
        if (_playStyle.equals(HEALER_TYPE)) {
            return getHealerTarget(teamArray, oppositionArray, givenAction);
        }
        if (_playStyle.equals(FIGHTER_TYPE)) {
            return getFighterTarget(oppositionArray);
        }
        if (_playStyle.equals(SPELL_CASTER_TYPE)) {
            return getSpellCasterTarget(oppositionArray);
        } else {
            return null;
        }
    }

    /**
     * If their action is healing they check which person on their team has the lowest health
     * if not healing they target the highest health target
     * @param teamArray the array the current character is fighting alongside
     * @param oppositionArray the array the current character is fighting against
     * @param givenAction the action the character AI already chose
     * @return returns the healer AI's chosen target
     */
    private Character getHealerTarget(ArrayList<Character> teamArray, ArrayList<Character> oppositionArray, Action givenAction) {
        if (givenAction.getActionType().equals(HELP_ACTION_TYPE)) {
            return lowestHealth(teamArray);
        } else {
            return highestHealth(oppositionArray);

        }
    }

    /**
     * targets the lowest health opposition member
     * @param oppositionArray the array the current character is fighting against
     * @return returns the fighter AI's chosen target
     */
    private Character getFighterTarget(ArrayList<Character> oppositionArray) {
        return lowestHealth(oppositionArray);
    }

    /**
     * targets the highest health opposition member
     * @param oppositionArray the array the current character is fighting against
     * @return returns the spell caster AI's chosen target
     */
    private Character getSpellCasterTarget(ArrayList<Character> oppositionArray) {
        return highestHealth(oppositionArray);
    }

    /**
     * @param givenArray the array passed into the method
     * @return returns the character with the lowest health from the given array
     */
    private Character lowestHealth(ArrayList<Character> givenArray) {
        Character lowestHealthCharacter = givenArray.get(0);
        for (int i = 1; i < givenArray.size(); i++) {
            if (givenArray.get(i).getHealth() < lowestHealthCharacter.getHealth()) {
                lowestHealthCharacter = givenArray.get(i);
            }
        }
        return lowestHealthCharacter;
    }

    /**
     * @param givenArray the array passed into the method
     * @return returns the highest health character from the given array
     */
    private Character highestHealth(ArrayList<Character> givenArray) {
        Character highestHealthCharacter = givenArray.get(0);
        for (int i = 1; i < givenArray.size(); i++) {
            if (givenArray.get(i).getHealth() < highestHealthCharacter.getHealth()) {
                highestHealthCharacter = givenArray.get(i);
            }
        }
        return highestHealthCharacter;
    }

    /**
     * Levels up the NPC switching from health and action ppoints
     */
    public void levelUp() {
        if (_level % 2 == 0) {
            this._healthCap = _healthCap + 5;
            this._health = _healthCap;
        } else {
            this._actionPointCap = _actionPointCap + 5;
            this._actionPoints = _actionPointCap;
        }
        this._level = _level + 1;
    }
}




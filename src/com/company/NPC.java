package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class NPC extends Character {
    final static public String HEALER_TYPE = "healer";
    final static public String SPELL_CASTER_TYPE = "spellCaster";
    final static public String FIGHTER_TYPE = "fighter";
    final static public String FIREBALL_ACTION = "fireball";
    final static public String HEAL_ACTION = "heal";
    final static public String REST_ACTION = "rest";
    final static public String STAB_ACTION = "stab";

    private final String _playStyle;
    public HashMap<String, Action> _NPCActionList;  // Never public PH
    ActionList _objActionList = new ActionList();



    public NPC(String playStyle,String name, int health, int actionPoints){
        super(name,health,actionPoints);
        this._NPCActionList = _objActionList.getNPCActions();
        this._playStyle = playStyle;
    }


    public Action getAction(ArrayList<Character> teamArray, ArrayList<Character> enemyArray, Character currentNPC){
        if (_playStyle.equals(HEALER_TYPE)){
            return getHealerAction(teamArray, currentNPC);
        }
        if(_playStyle.equals(FIGHTER_TYPE)){
            return getFighterAction(currentNPC);
        }
        if (_playStyle.equals(SPELL_CASTER_TYPE)){
            return getSpellCasterAction(currentNPC);
        }
        return null;
    }

    private Action getHealerAction(ArrayList<Character> teamArray, Character currentNPC){
        for (Character character : teamArray) {
            if (character.getHealth() < 15) {
                if (checkAndChangeActionPoints(currentNPC, _NPCActionList.get(HEAL_ACTION))) {
                    return _NPCActionList.get(HEAL_ACTION);
                }
            }
        }
        // Return something else please
        if (checkAndChangeActionPoints(currentNPC,_NPCActionList.get(FIREBALL_ACTION))){
            return _NPCActionList.get(FIREBALL_ACTION);
        }
        return _NPCActionList.get(REST_ACTION);
    }

    private Action getFighterAction(Character currentNPC){
        // change to melee attack that costs less Action Points
        if (checkAndChangeActionPoints(currentNPC,_NPCActionList.get(STAB_ACTION))){
            return _NPCActionList.get(STAB_ACTION);
        }
        return _NPCActionList.get(REST_ACTION);

    }

    private Action getSpellCasterAction(Character currentNPC){
        if (checkAndChangeActionPoints(currentNPC,_NPCActionList.get(FIREBALL_ACTION))){
            return _NPCActionList.get(FIREBALL_ACTION);
        }
        return _NPCActionList.get(REST_ACTION);

    }

    public Character getTarget(ArrayList<Character> teamArray, ArrayList<Character> oppositionArray, Action givenAction){
        if (_playStyle.equals(HEALER_TYPE)){
            return getHealerTarget(teamArray, oppositionArray,givenAction );
        }
        if(_playStyle.equals(FIGHTER_TYPE)){
            return getFighterTarget(oppositionArray);
        }
        if (_playStyle.equals(SPELL_CASTER_TYPE)){
            return getSpellCasterTarget(oppositionArray);
        }
        else{
            return null;
        }
    }

    private Character getHealerTarget(ArrayList<Character> teamArray, ArrayList<Character> oppositionArray, Action givenAction) {
        if (givenAction.getActionType().equals(HELP_ACTION_TYPE)){
           return lowestHealth(teamArray);
        }
        else{
            return highestHealth(oppositionArray);

        }
    }

    private Character getFighterTarget(ArrayList<Character> oppositionArray) {
        return lowestHealth(oppositionArray);
    }

    private Character getSpellCasterTarget(ArrayList<Character> oppositionArray) {
        return highestHealth(oppositionArray);
    }

    private Character lowestHealth(ArrayList<Character> givenArray){
        Character lowestHealthCharacter = givenArray.get(0);
        for (int i = 1; i <givenArray.size(); i ++){
            if (givenArray.get(i).getHealth() < lowestHealthCharacter.getHealth()){
                lowestHealthCharacter = givenArray.get(i);
            }
        }
        return lowestHealthCharacter;
    }

    private Character highestHealth( ArrayList<Character> givenArray){
        Character highestHealthCharacter = givenArray.get(0);
        for (int i = 1; i <givenArray.size(); i ++){
            if (givenArray.get(i).getHealth() < highestHealthCharacter.getHealth()){
                highestHealthCharacter = givenArray.get(i);
            }
        }
        return highestHealthCharacter;
    }

    public void levelUp(){
        if (_level % 2 == 0){
            this._healthCap = _healthCap + 5;
            this._health = _healthCap;
        }
        else {
            this._actionPointCap = _actionPointCap + 5;
            this._actionPoints = _actionPointCap;
        }
        this._level = _level + 1;
    }
}




package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class NPC extends Character{  // Space between end of string and { PH
    private final String _playStyle;
    public HashMap<String, Action> _NPCActionList;  // Never public PH
    ActionList _objActionList = new ActionList();
    final static public String HELP_ACTION_TYPE = "helpful";
    final static public String HEALER_TYPE = "healer";
    final static public String SPELL_CASTER_TYPE = "spellCaster";
    final static public String FIGHTER_TYPE = "fighter";
    final static public String FIREBALL_ACTION = "fireball";
    final static public String HEAL_ACTION = "heal";
    final static public String REST_ACTION = "rest";
    final static public String STAB_ACTION = "stab";




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
                if (enoughActionPoint(currentNPC, _NPCActionList.get(HEAL_ACTION))) {
                    return _NPCActionList.get(HEAL_ACTION);
                }
            }
        }
        // Return something else please
        if (enoughActionPoint(currentNPC,_NPCActionList.get(FIREBALL_ACTION))){
            return _NPCActionList.get(FIREBALL_ACTION);
        }
        return _NPCActionList.get(REST_ACTION);
    }
    private Action getFighterAction(Character currentNPC){
        // change to melee attack that costs less Action Points
        if (enoughActionPoint(currentNPC,_NPCActionList.get(STAB_ACTION))){
            return _NPCActionList.get(STAB_ACTION);
        }
        return _NPCActionList.get(REST_ACTION);

    }
    private Action getSpellCasterAction(Character currentNPC){
        if (enoughActionPoint(currentNPC,_NPCActionList.get(FIREBALL_ACTION))){
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

    private Character getSpellCasterTarget(ArrayList<Character> oppositionArray) { return highestHealth(oppositionArray); }

    private Character lowestHealth( ArrayList<Character> givenArray){
        Character lowestHealthNPC = givenArray.get(0);
        int lowestHealth = givenArray.get(0).getHealth();
        for (int i = 1; i <givenArray.size(); i ++){
            if (givenArray.get(i).getHealth() < lowestHealth){
                lowestHealthNPC = givenArray.get(i);
                lowestHealth = givenArray.get(i).getHealth();
            }
        }
        return lowestHealthNPC;
    }

    private Character highestHealth( ArrayList<Character> givenArray){
        Character highestHealthNPC = givenArray.get(0);
        int highestHealth = givenArray.get(0).getHealth();
        for (int i = 1; i <givenArray.size(); i ++){
            if (givenArray.get(i).getHealth() < highestHealth){
                highestHealthNPC = givenArray.get(i);
                highestHealth = givenArray.get(i).getHealth();
            }
        }
        return highestHealthNPC;
    }

    public void levelUp(){
        if (_level % 2 == 0){
            this._healthCap = _healthCap +5;
            this._health = _healthCap;
        }
        else {
            this._actionPointCap = _actionPointCap +5;
            this._actionPoints = _actionPointCap;
        }
        this._level = _level+1;
    }
}




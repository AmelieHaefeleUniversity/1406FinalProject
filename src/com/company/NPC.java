package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class NPC extends Character{
    private final String _playStyle;
    public HashMap<String, action> _NPCActionList;
    actionList _objActionList = new actionList();

    public NPC(String playStyle,String name, int health, int actionPoints){
        super(name,health,actionPoints);
        this._NPCActionList = _objActionList.getNPCActions();
        this._playStyle = playStyle;
    }

    public action getAction(ArrayList<Character> teamArray, ArrayList<Character> enemyArray, Character currentNPC){
        if (_playStyle.equals("healer")){
            return getHealerAction(teamArray, currentNPC);
        }
        if(_playStyle.equals("fighter")){
            return getFighterAction(currentNPC);
        }
        if (_playStyle.equals("spellCaster")){
            return getSpellCasterAction(currentNPC);
        }
        return null;
    }

    protected action getHealerAction(ArrayList<Character> teamArray,Character currentNPC){
        for (Character character : teamArray) {
            if (character.getHealth() < 15) {
                if (enoughActionPoint(currentNPC, _NPCActionList.get("heal"))) {
                    return _NPCActionList.get("heal");
                }
            }
        }
        // Return something else please
        if (enoughActionPoint(currentNPC,_NPCActionList.get("fireball"))){
            return _NPCActionList.get("fireball");
        }
        return _NPCActionList.get("rest");
    }
    protected action getFighterAction(Character currentNPC){
        // change to melee attack that costs less Action Points
        if (enoughActionPoint(currentNPC,_NPCActionList.get("fireball"))){
            return _NPCActionList.get("fireball");
        }
        return _NPCActionList.get("rest");

    }
    protected action getSpellCasterAction(Character currentNPC){
        if (enoughActionPoint(currentNPC,_NPCActionList.get("fireball"))){
            return _NPCActionList.get("fireball");
        }
        return _NPCActionList.get("rest");

    }


    public Character getTarget(ArrayList<Character> teamArray, ArrayList<Character> oppositionArray, action givenAction){
        if (_playStyle.equals("healer")){
            return getHealerTarget(teamArray, oppositionArray,givenAction );
        }
        if(_playStyle.equals("fighter")){
            return getFighterTarget(oppositionArray);
        }
        if (_playStyle.equals("spellCaster")){
            return getSpellCasterTarget(oppositionArray);
        }
        else{
            return null;
        }
    }
    protected Character getHealerTarget(ArrayList<Character> teamArray, ArrayList<Character> oppositionArray, action givenAction) {
        if (givenAction.toString().equals("heal")){
           return lowestHealth(teamArray);
        }
        else{
            return highestHealth(oppositionArray);

        }
    }

    protected Character getFighterTarget(ArrayList<Character> oppositionArray) {
        return lowestHealth(oppositionArray);
    }

    protected Character getSpellCasterTarget(ArrayList<Character> oppositionArray) {
        return highestHealth(oppositionArray);

    }

    public Character lowestHealth( ArrayList<Character> givenArray){
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

    public Character highestHealth( ArrayList<Character> givenArray){
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
}




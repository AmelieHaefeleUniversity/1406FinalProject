package com.company;

public class Action {   // Class names should start with a Capital letter PH

    private int _apCost;
    private String _actionType;
    private int _effect;
    private String _statEffect;

    public Action(int apCost, String actionType, int effect, String statEffect){
        this._apCost = apCost;
        this._actionType = actionType;
        this._effect = effect;
        this._statEffect = statEffect;
    }

    public String getActionType() { return _actionType; }

    public int getEffect() { return _effect;}

    public int getApCost(){return _apCost;} // Neaten PH

    public String getStatEffect(){return _statEffect;}
}

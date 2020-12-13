package com.company;

public class Action {

    private final int _apCost;
    private final String _actionType;
    private final int _effect;
    private final String _statEffect;

    public Action(int apCost, String actionType, int effect, String statEffect) {
        this._apCost = apCost;
        this._actionType = actionType;
        this._effect = effect;
        this._statEffect = statEffect;
    }

    public String getActionType() {
        return _actionType;
    }

    public int getEffect() {
        return _effect;
    }

    public int getApCost() {
        return _apCost;
    }

    public String getStatEffect() {
        return _statEffect;
    }
}

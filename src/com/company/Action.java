package com.company;

public class Action {
    /**
     * Set up all needed variables for the actions to have
     */
    private final int _apCost;
    private final String _actionType;
    private final int _effect;
    private final String _statEffect;

    /**
     * Action constructor
     * @param apCost number of action points needed to preform the action
     * @param actionType what type of action it is, helpful, harmful, or neutral, determines the possible target list
     * @param effect what the effect is, negative for harmful positive for helpful and neutral
     * @param statEffect which stat is effected
     */
    public Action(int apCost, String actionType, int effect, String statEffect) {
        this._apCost = apCost;
        this._actionType = actionType;
        this._effect = effect;
        this._statEffect = statEffect;
    }

    /**
     * Getter methods
     * @return
     */

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

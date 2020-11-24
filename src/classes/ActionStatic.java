package classes;

import classes.abstracts.Action;
import classes.enums.ActionType;

public class ActionStatic extends Action {

    ActionType type;

    public ActionStatic(int value, ActionType type) {
        super(value);
        this.type = type;
    }
}

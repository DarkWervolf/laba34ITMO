package classes.actions;

import classes.abstracts.Action;
import classes.abstracts.Thing;
import classes.enums.ActionTypeThing;

public class ActionThing extends Action {

    ActionTypeThing type;
    Thing thing;

    public ActionThing(int value, ActionTypeThing type) {
        super(value);
        this.type = type;
    }

    public String perform(Thing thing){
        return (this.type.outStringAction() + " " + thing.getTitle());
    }
}

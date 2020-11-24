package classes;

import classes.abstracts.Action;
import classes.enums.ActionTypePerson;

public class ActionPerson extends Action {

    ActionTypePerson type;

    public ActionPerson(int value, ActionTypePerson type) {
        super(value);
        this.type = type;
    }
}

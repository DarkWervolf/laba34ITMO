package classes;

import classes.abstracts.Action;
import classes.enums.ActionTypePerson;

public class ActionPerson extends Action {

    ActionTypePerson type;

    public ActionPerson(int value, ActionTypePerson type) {
        super(value);
        this.type = type;
    }

    public ActionTypePerson getType() {
        return type;
    }

    public String perform(Person victim){
        return (this.type.outStringAction() + " " + victim.getName());
    }
}

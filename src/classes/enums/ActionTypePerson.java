package classes.enums;

public enum ActionTypePerson {
    HANGING (" is hanging "),
    EATING (" is eating "),
    KICKING (" is kicking ");

    private final String actionOutput;
    ActionTypePerson(String actionOutput){
        this.actionOutput = actionOutput;
    }

    public String outStringAction(){
        return this.actionOutput;
    }
}

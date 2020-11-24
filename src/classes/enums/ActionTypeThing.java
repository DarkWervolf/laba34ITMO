package classes.enums;

public enum ActionTypeThing {
    PUTTING ( " is putting "),
    THROWING (" is throwing "),
    TAKING (" is taking "),
    HANGING (" is hanging "),
    EATING (" is eating "),
    GETTING (" is getting ");

    private final String actionOutput;
    ActionTypeThing(String actionOutput){
        this.actionOutput = actionOutput;
    }

    public String outStringAction(){
        return this.actionOutput;
    }
}

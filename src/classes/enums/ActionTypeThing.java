package classes.enums;

import java.util.Random;

public enum ActionTypeThing {
    PUTTING ( " is putting "),
    THROWING (" is throwing "),
    TAKING (" is taking "),
    HANGING (" is hanging "),
    EATING (" is eating "),
    GETTING (" is getting ");

    private final String actionOutput;
    private static final ActionTypeThing[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    ActionTypeThing(String actionOutput){
        this.actionOutput = actionOutput;
    }

    public String outStringAction(){
        return this.actionOutput;
    }

    public static ActionTypeThing randomAction()  {
        return VALUES[RANDOM.nextInt(SIZE)];
    }
}

package classes.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum ActionTypeThing {
    PUTTING ( " is putting "),
    THROWING (" is throwing "),
    TAKING (" is taking "),
    HANGING (" is hanging "),
    EATING (" is eating "),
    GETTING (" is getting ");

    private final String actionOutput;
    private static final List<ActionTypeThing> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    ActionTypeThing(String actionOutput){
        this.actionOutput = actionOutput;
    }

    public String outStringAction(){
        return this.actionOutput;
    }

    public static ActionTypeThing randomAction()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}

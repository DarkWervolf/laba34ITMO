package classes.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum ActionType {

    CRYING (" is crying"),
    SMILING (" is smiling"),
    EATING (" is eating "),
    MOVING ( "is moving "),


    private final String actionOutput;
    private static final List<ActionType> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    ActionType(String actionOutput){
        this.actionOutput = actionOutput;
    }

    public String outStringAction(){
        return this.actionOutput;
    }

    public static ActionType randomAction()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}

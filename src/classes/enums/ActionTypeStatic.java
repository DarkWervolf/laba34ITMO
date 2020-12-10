package classes.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum ActionTypeStatic {

    CRYING (" is crying"),
    SMILING (" is smiling"),
    EATING (" is eating"),
    WHISTLING (" is whistling"),
    FREEING( " is giving freedom to "),
    LAUTHING (" is laughing");


    private final String actionOutput;
    private static final ActionTypeStatic[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    ActionTypeStatic(String actionOutput){
        this.actionOutput = actionOutput;
    }

    public String outStringAction(){
        return this.actionOutput;
    }

    public static ActionTypeStatic randomAction()  {
        return VALUES[RANDOM.nextInt(SIZE)];
    }
}

package classes.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum ActionTypePerson {
    KISSING (" is kissing"),
    WATCHING (" is attentively watching"),
    TOUCHING (" is touching"),
    EATING (" is eating"),
    BEATING(" is beating"),
    HUGGING (" is hugging"),
    KICKING (" is kicking");

    private final String actionOutput;
    private static final ActionTypePerson[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    ActionTypePerson(String actionOutput){
        this.actionOutput = actionOutput;
    }

    public String outStringAction(){
        return this.actionOutput;
    }

    public static ActionTypePerson randomAction()  {
        return VALUES[RANDOM.nextInt(SIZE)];
    }
}

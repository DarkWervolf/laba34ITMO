package classes.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum ActionTypePerson {
    KISSING (" kisses"),
    WATCHING (" is attentively watching"),
    TOUCHING (" touches"),
    EATING (" eats"),
    BEATING(" beats"),
    HUGGING (" hugs"),
    PRISONING( " prisons"),
    FREEING (" gives freedom to"),
    KICKING (" kicks");

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

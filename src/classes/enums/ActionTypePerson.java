package classes.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum ActionTypePerson {
    HANGING (" is hanging "),
    EATING (" is eating "),
    BEATING(" is beating"),
    HUGGING (" is hugging"),
    KICKING (" is kicking ");

    private final String actionOutput;
    private static final List<ActionTypePerson> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    ActionTypePerson(String actionOutput){
        this.actionOutput = actionOutput;
    }

    public String outStringAction(){
        return this.actionOutput;
    }

    public static ActionTypePerson randomAction()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}

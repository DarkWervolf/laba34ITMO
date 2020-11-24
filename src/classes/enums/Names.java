package classes.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Names {
    Vintik,
    Shpuntik,
    Vova,
    Donut,
    Mark,
    Peter,
    Lee,
    Lili,
    Harry,
    Hermione,
    Ron,
    Zabini,
    Kevin;

    private static final Names[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static String randomName()  {
        return VALUES[RANDOM.nextInt(SIZE)].toString();
    }
}

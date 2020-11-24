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
    Kevin;

    private static final List<Names> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static String randomName()  {
        return VALUES.get(RANDOM.nextInt(SIZE)).toString();
    }
}

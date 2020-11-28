package classes.enums;

import java.util.Random;

public enum ContainerTitles {
    Chest,
    Barrel,
    Sack,
    Cage;

    private static final ContainerTitles[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static String randomContainerTitle()  {
        return VALUES[RANDOM.nextInt(SIZE)].toString();
    }
}

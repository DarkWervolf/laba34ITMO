package classes.enums;

import java.util.Random;

public enum CarType {
    Truck,
    Van,
    Crossover,
    Sports,
    Luxury;

    private static final CarType[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static CarType randomCarType()  {
        return VALUES[RANDOM.nextInt(SIZE)];
    }
}

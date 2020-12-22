package classes.enums;

import java.util.Random;

public enum FoodTitles {
    CocaCola,
    Popcorn,
    GiantSeeds,
    HotDog;

    private static final FoodTitles[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static String randomFoodTitle()  {
        return VALUES[RANDOM.nextInt(SIZE)].toString();
    }
}

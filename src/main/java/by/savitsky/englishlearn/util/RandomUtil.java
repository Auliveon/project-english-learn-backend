package by.savitsky.englishlearn.util;

import java.util.Random;

public class RandomUtil {

    public static int getRandomIntValue(int interval) {
        final Random random = new Random();
        return random.nextInt(interval);
    }

    public static int[] getRandomIntUniqueValues(int interval, int count) {
        final int[] generatedValues = new int[count];
        for (int i = 0; i < count - 1; i++) {
            int generated = getRandomIntValue(interval);
            boolean contains = false;
            for (int value : generatedValues) {
                if (value == generated) {
                    contains = true;
                    break;
                }
            }
            if (contains) {
                i--;
                continue;
            }
            generatedValues[i] = generated;
        }
        return generatedValues;
    }

}

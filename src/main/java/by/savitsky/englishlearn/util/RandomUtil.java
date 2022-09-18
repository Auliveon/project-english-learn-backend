package by.savitsky.englishlearn.util;

import java.util.Random;

public class RandomUtil {

    public static int getRandomIntValue(int interval) {
        final Random random = new Random();
        return random.nextInt(interval);
    }

    public static Integer[] getRandomIntUniqueValues(int interval, int count) {
        final Integer[] generatedValues = new Integer[count];
        for (int i = 0; i < count; i++) {
            int generated = getRandomIntValue(interval);
            boolean contains = false;
            for (Integer value : generatedValues) {
                if (value != null && generated == value) {
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

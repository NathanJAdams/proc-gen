package com.ripplargames.procgen;

import java.util.Random;

public class JdkRandom implements RandomWrapper {
    private final Random random;

    public JdkRandom() {
        this(new Random());
    }

    public JdkRandom(long seed) {
        this(new Random(seed));
    }

    public JdkRandom(Random random) {
        this.random = random;
    }

    @Override
    public boolean nextBoolean() {
        return random.nextBoolean();
    }

    @Override
    public double nextDouble(double low, double high) {
        double range = high - low;
        return low + random.nextDouble() * range;
    }

    @Override
    public long nextLong(long low, long high) {
        if (low >= high) {
            return low;
        }
        long bounds = high - low;
        if (bounds < 0) {
            // difference can't fit in a long
            // just keep trying
            return nextLongBetween(low, high);
        } else if (bounds >= Integer.MAX_VALUE) {
            // difference can't fit in an int but can fit in a long
            // get the maximum possible multiple of bounds that can fit in a long for fairness and speed, then mod the result
            long multiple = Long.MAX_VALUE / bounds;
            long multipleBounds = multiple * bounds;
            long multipleRandom = nextLongBetween(0, multipleBounds);
            return low + (multipleRandom % bounds);
        } else {
            // difference can fit in an int
            // just get a random int
            return low + random.nextInt((int) bounds);
        }
    }

    private long nextLongBetween(long low, long high) {
        do {
            long test = random.nextLong();
            if ((test >= low) && (test <= high)) {
                return test;
            }
        } while (true);
    }
}

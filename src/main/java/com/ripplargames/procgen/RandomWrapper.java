package com.ripplargames.procgen;

public interface RandomWrapper {
    boolean nextBoolean();

    default byte nextByte(byte low, byte high) {
        return (byte) nextLong(low, high);
    }

    default short nextShort(short low, short high) {
        return (short) nextLong(low, high);
    }

    default char nextChar(char low, char high) {
        return (char) nextLong(low, high);
    }

    default int nextInt(int low, int high) {
        return (int) nextLong(low, high);
    }

    long nextLong(long low, long high);

    default float nextFloat(float low, float high) {
        return (float) nextDouble(low, high);
    }

    double nextDouble(double low, double high);
}

package com.ripplargames.procgen.util;

import com.ripplargames.procgen.annotations.Range;
import java.lang.annotation.Annotation;

public class RangeBase implements Range {
    @Override
    public byte minByte() {
        return Byte.MIN_VALUE;
    }

    @Override
    public byte maxByte() {
        return Byte.MAX_VALUE;
    }

    @Override
    public short minShort() {
        return Short.MIN_VALUE;
    }

    @Override
    public short maxShort() {
        return Short.MAX_VALUE;
    }

    @Override
    public int minInt() {
        return Integer.MIN_VALUE;
    }

    @Override
    public int maxInt() {
        return Integer.MAX_VALUE;
    }

    @Override
    public long minLong() {
        return Long.MIN_VALUE;
    }

    @Override
    public long maxLong() {
        return Long.MAX_VALUE;
    }

    @Override
    public float minFloat() {
        return -Float.MAX_VALUE;
    }

    @Override
    public float maxFloat() {
        return Float.MAX_VALUE;
    }

    @Override
    public double minDouble() {
        return -Double.MAX_VALUE;
    }

    @Override
    public double maxDouble() {
        return Double.MAX_VALUE;
    }

    @Override
    public char minChar() {
        return Character.MIN_VALUE;
    }

    @Override
    public char maxChar() {
        return Character.MAX_VALUE;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return Range.class;
    }
}

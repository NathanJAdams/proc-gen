package com.ripplargames.procgen.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Range {
    byte minByte() default Byte.MIN_VALUE;

    byte maxByte() default Byte.MAX_VALUE;

    short minShort() default Short.MIN_VALUE;

    short maxShort() default Short.MAX_VALUE;

    int minInt() default Integer.MIN_VALUE;

    int maxInt() default Integer.MAX_VALUE;

    long minLong() default Long.MIN_VALUE;

    long maxLong() default Long.MAX_VALUE;

    float minFloat() default -Float.MAX_VALUE;

    float maxFloat() default Float.MAX_VALUE;

    double minDouble() default -Double.MAX_VALUE;

    double maxDouble() default Double.MAX_VALUE;

    char minChar() default Character.MIN_VALUE;

    char maxChar() default Character.MAX_VALUE;
}

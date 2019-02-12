package com.ripplargames.procgen;

import com.ripplargames.procgen.annotations.Range;
import java.lang.annotation.Annotation;

public interface RangeGenerator<T> extends Generator<T> {
    boolean canGenerateType(Class<?> type);

    default boolean isGeneratable(Class<?> type, Annotation[] annotations, Context context) throws ProcgenException {
        if (!canGenerateType(type)) {
            return false;
        }
        Range range = context.findFirstOrNull(annotations, Range.class);
        if (range == null) {
            throw new ProcgenException(type.getName() + " fields must have a " + Range.class.getSimpleName() + " annotation");
        }
        return true;
    }

    T generateWithRange(Range range, Context context) throws ProcgenException;

    default T generateNext(Class<?> type, Annotation[] annotations, Context context) throws ProcgenException {
        Range range = context.findFirstOrNull(annotations, Range.class);
        return generateWithRange(range, context);
    }
}

package com.ripplargames.procgen;

import java.lang.annotation.Annotation;

public class BooleanGenerator implements Generator<Boolean> {
    @Override
    public boolean isGeneratable(Class<?> type, Annotation[] annotations, Context context) throws ProcgenException {
        return (type == Boolean.class) || (type == Boolean.TYPE);
    }

    @Override
    public Boolean generateNext(Class<?> type, Annotation[] annotations, Context context) throws ProcgenException {
        return context.getRandom().nextBoolean();
    }
}

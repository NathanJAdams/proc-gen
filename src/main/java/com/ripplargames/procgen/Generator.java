package com.ripplargames.procgen;

import java.lang.annotation.Annotation;

public interface Generator<T> {
    boolean isGeneratable(Class<?> type, Annotation[] annotations, Context context) throws ProcgenException;

    T generateNext(Class<?> type, Annotation[] annotations, Context context) throws ProcgenException;
}

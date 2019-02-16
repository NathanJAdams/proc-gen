package com.ripplargames.procgen.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Collection {
    int min() default 0;

    int max() default 0;

    Class<? extends java.util.Collection<?>> collectionClass();

    Class<?> elementType();
}

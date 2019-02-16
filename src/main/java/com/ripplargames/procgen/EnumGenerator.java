package com.ripplargames.procgen;

import com.ripplargames.procgen.annotations.EnumName;
import java.lang.annotation.Annotation;

public class EnumGenerator implements Generator<Enum> {
    @Override
    public boolean isGeneratable(Class<?> type, Annotation[] annotations, Context context) throws ProcgenException {
        return type.isEnum();
    }

    @Override
    public Enum generateNext(Class<?> type, Annotation[] annotations, Context context) throws ProcgenException {
        EnumName enumChoice = context.findFirstOrNull(annotations, EnumName.class);
        if (enumChoice == null) {
            return null;
        }
        Class<?> enumClass = enumChoice.enumClass();
        Object[] enumConstants = enumClass.getEnumConstants();
        int index = context.getRandom().nextInt(0, enumConstants.length - 1);
        return (Enum) enumConstants[index];
    }
}

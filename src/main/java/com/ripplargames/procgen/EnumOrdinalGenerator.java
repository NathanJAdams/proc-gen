package com.ripplargames.procgen;

import com.ripplargames.procgen.annotations.EnumOrdinal;
import java.lang.annotation.Annotation;

public class EnumOrdinalGenerator implements Generator<Integer> {
    private final EnumGenerator enumGenerator = new EnumGenerator();

    @Override
    public boolean isGeneratable(Class<?> type, Annotation[] annotations, Context context) throws ProcgenException {
        EnumOrdinal enumOrdinal = context.findFirstOrNull(annotations, EnumOrdinal.class);
        if (enumOrdinal == null) {
            return false;
        }
        if ((type != Integer.class) || (type != Integer.TYPE)) {
            throw new ProcgenException(EnumOrdinal.class.getSimpleName() + " annotation must only be used for int or Integer fields");
        }
        return true;
    }

    @Override
    public Integer generateNext(Class<?> type, Annotation[] annotations, Context context) throws ProcgenException {
        Enum enumChoice = enumGenerator.generateNext(type, annotations, context);
        return (enumChoice == null)
                ? null
                : enumChoice.ordinal();
    }
}

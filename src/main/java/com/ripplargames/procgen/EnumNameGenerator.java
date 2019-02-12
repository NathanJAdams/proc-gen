package com.ripplargames.procgen;

import com.ripplargames.procgen.annotations.EnumName;
import java.lang.annotation.Annotation;

public class EnumNameGenerator implements Generator<String> {
    private final EnumGenerator enumGenerator = new EnumGenerator();

    @Override
    public boolean isGeneratable(Class<?> type, Annotation[] annotations, Context context) throws ProcgenException {
        EnumName enumName = context.findFirstOrNull(annotations, EnumName.class);
        if (enumName == null) {
            return false;
        }
        if (type != String.class) {
            throw new ProcgenException(EnumName.class.getSimpleName() + " annotation must only be used for String fields");
        }
        return true;
    }

    @Override
    public String generateNext(Class<?> type, Annotation[] annotations, Context context) throws ProcgenException {
        Enum enumChoice = enumGenerator.generateNext(type, annotations, context);
        return (enumChoice == null)
                ? null
                : enumChoice.name();
    }
}

package com.ripplargames.procgen;

import com.ripplargames.procgen.annotations.Choice;
import java.lang.annotation.Annotation;
import java.util.List;

public class ChoiceGenerator implements Generator<Object> {
    @Override
    public boolean isGeneratable(Class<?> type, Annotation[] annotations, Context context) throws ProcgenException {
        return (context.findFirstOrNull(annotations, Choice.class) != null);
    }

    @Override
    public Object generateNext(Class<?> type, Annotation[] annotations, Context context) throws ProcgenException {
        Choice choice = context.findFirstOrNull(annotations, Choice.class);
        if (choice == null) {
            return null;
        }
        List<?> choices = context.getChoices(choice);
        int index = context.getRandom().nextInt(0, choices.size() - 1);
        return choices.get(index);
    }
}

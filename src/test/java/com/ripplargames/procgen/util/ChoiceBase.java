package com.ripplargames.procgen.util;

import com.ripplargames.procgen.annotations.Choice;
import java.lang.annotation.Annotation;

public abstract class ChoiceBase implements Choice {
    @Override
    public Class<? extends Annotation> annotationType() {
        return Choice.class;
    }
}

package com.ripplargames.procgen.annotations;

import com.ripplargames.procgen.ChoiceProvider;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Choice {
    Class<? extends ChoiceProvider> choiceProvider() default ChoiceProvider.class;
}

package com.ripplargames.procgen;

import java.util.List;

public interface ChoiceProvider<T> {
    List<T> choices();
}

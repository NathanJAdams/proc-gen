package com.ripplargames.procgen.range;

import com.ripplargames.procgen.Context;
import com.ripplargames.procgen.ProcgenException;
import com.ripplargames.procgen.RangeGenerator;
import com.ripplargames.procgen.annotations.Range;

public class CharGenerator implements RangeGenerator<Character> {
    @Override
    public boolean canGenerateType(Class<?> type) {
        return (type == Character.class) || (type == Character.TYPE);
    }

    @Override
    public Character generateWithRange(Range range, Context context) throws ProcgenException {
        char min = Character.MIN_VALUE;
        char max = Character.MAX_VALUE;
        if (range != null) {
            min = range.minChar();
            max = range.maxChar();
        }
        if (min > max) {
            throw new ProcgenException("Invalid range");
        }
        if (min == max) {
            return min;
        }
        return context.getRandom().nextChar(min, max);
    }
}

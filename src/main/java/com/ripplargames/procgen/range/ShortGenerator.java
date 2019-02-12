package com.ripplargames.procgen.range;

import com.ripplargames.procgen.Context;
import com.ripplargames.procgen.ProcgenException;
import com.ripplargames.procgen.RangeGenerator;
import com.ripplargames.procgen.annotations.Range;

public class ShortGenerator implements RangeGenerator<Short> {
    @Override
    public boolean canGenerateType(Class<?> type) {
        return (type == Short.class) || (type == Short.TYPE);
    }

    @Override
    public Short generateWithRange(Range range, Context context) throws ProcgenException {
        short min = Short.MIN_VALUE;
        short max = Short.MAX_VALUE;
        if (range != null) {
            min = range.minShort();
            max = range.maxShort();
        }
        if (min > max) {
            throw new ProcgenException("Invalid range");
        }
        if (min == max) {
            return min;
        }
        return context.getRandom().nextShort(min, max);
    }
}

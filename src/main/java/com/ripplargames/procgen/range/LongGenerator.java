package com.ripplargames.procgen.range;

import com.ripplargames.procgen.Context;
import com.ripplargames.procgen.ProcgenException;
import com.ripplargames.procgen.RangeGenerator;
import com.ripplargames.procgen.annotations.Range;

public class LongGenerator implements RangeGenerator<Long> {
    @Override
    public boolean canGenerateType(Class<?> type) {
        return (type == Long.class) || (type == Long.TYPE);
    }

    @Override
    public Long generateWithRange(Range range, Context context) throws ProcgenException {
        long min = Long.MIN_VALUE;
        long max = Long.MAX_VALUE;
        if (range != null) {
            min = range.minLong();
            max = range.maxLong();
        }
        if (min > max) {
            throw new ProcgenException("Invalid range");
        }
        if (min == max) {
            return min;
        }
        return context.getRandom().nextLong(min, max);
    }
}

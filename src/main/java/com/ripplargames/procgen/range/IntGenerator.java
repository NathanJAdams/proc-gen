package com.ripplargames.procgen.range;

import com.ripplargames.procgen.Context;
import com.ripplargames.procgen.ProcgenException;
import com.ripplargames.procgen.RangeGenerator;
import com.ripplargames.procgen.annotations.Range;

public class IntGenerator implements RangeGenerator<Integer> {
    @Override
    public boolean canGenerateType(Class<?> type) {
        return (type == Integer.class) || (type == Integer.TYPE);
    }

    @Override
    public Integer generateWithRange(Range range, Context context) throws ProcgenException {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        if (range != null) {
            min = range.minInt();
            max = range.maxInt();
        }
        if (min > max) {
            throw new ProcgenException("Invalid range");
        }
        if (min == max) {
            return min;
        }
        return context.getRandom().nextInt(min, max);
    }
}

package com.ripplargames.procgen.range;

import com.ripplargames.procgen.Context;
import com.ripplargames.procgen.ProcgenException;
import com.ripplargames.procgen.RangeGenerator;
import com.ripplargames.procgen.annotations.Range;

public class FloatGenerator implements RangeGenerator<Float> {
    @Override
    public boolean canGenerateType(Class<?> type) {
        return (type == Float.class) || (type == Float.TYPE);
    }

    @Override
    public Float generateWithRange(Range range, Context context) throws ProcgenException {
        float min = -Float.MAX_VALUE;
        float max = Float.MAX_VALUE;
        if (range != null) {
            min = range.minFloat();
            max = range.maxFloat();
        }
        if (min > max) {
            throw new ProcgenException("Invalid range");
        }
        if (min == max) {
            return min;
        }
        return context.getRandom().nextFloat(min, max);
    }
}

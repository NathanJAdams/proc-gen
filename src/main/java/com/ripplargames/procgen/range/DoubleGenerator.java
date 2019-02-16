package com.ripplargames.procgen.range;

import com.ripplargames.procgen.Context;
import com.ripplargames.procgen.ProcgenException;
import com.ripplargames.procgen.RangeGenerator;
import com.ripplargames.procgen.annotations.Range;

public class DoubleGenerator implements RangeGenerator<Double> {
    @Override
    public boolean canGenerateType(Class<?> type) {
        return (type == Double.class) || (type == Double.TYPE);
    }

    @Override
    public Double generateWithRange(Range range, Context context) throws ProcgenException {
        double min = -Double.MAX_VALUE;
        double max = Double.MAX_VALUE;
        if (range != null) {
            min = range.minDouble();
            max = range.maxDouble();
        }
        if (min > max) {
            throw new ProcgenException("Invalid range");
        }
        if (min == max) {
            return min;
        }
        return context.getRandom().nextDouble(min, max);
    }
}

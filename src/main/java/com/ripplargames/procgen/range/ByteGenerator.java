package com.ripplargames.procgen.range;

import com.ripplargames.procgen.Context;
import com.ripplargames.procgen.ProcgenException;
import com.ripplargames.procgen.RangeGenerator;
import com.ripplargames.procgen.annotations.Range;

public class ByteGenerator implements RangeGenerator<Byte> {
    @Override
    public boolean canGenerateType(Class<?> type) {
        return (type == Byte.class) || (type == Byte.TYPE);
    }

    @Override
    public Byte generateWithRange(Range range, Context context) throws ProcgenException {
        byte min = Byte.MIN_VALUE;
        byte max = Byte.MAX_VALUE;
        if (range != null) {
            min = range.minByte();
            max = range.maxByte();
        }
        if (min > max) {
            throw new ProcgenException("Invalid range");
        }
        if (min == max) {
            return min;
        }
        return context.getRandom().nextByte(min, max);
    }
}

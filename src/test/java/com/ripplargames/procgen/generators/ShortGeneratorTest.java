package com.ripplargames.procgen.generators;

import com.ripplargames.procgen.JdkRandom;
import com.ripplargames.procgen.Context;
import com.ripplargames.procgen.ProcgenException;
import com.ripplargames.procgen.RandomWrapper;
import com.ripplargames.procgen.annotations.Range;
import com.ripplargames.procgen.range.ShortGenerator;
import com.ripplargames.procgen.util.RangeBase;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

public class ShortGeneratorTest {
    @Test
    public void testGood() throws ProcgenException {
        performTest((short) 0, (short) 9);
        performTest((short) 0, null);
        performTest(null, (short) 9);
        performTest(null, null);
        performTest((short) 0, (short) 0);
    }

    @Test(expected = ProcgenException.class)
    public void testInvalidRange() throws ProcgenException {
        performTest((short) 9, (short) 0);
    }

    private void performTest(Short min, Short max) throws ProcgenException {
        RandomWrapper random = new JdkRandom(new Random(0));
        Context context = new Context(random);
        ShortGenerator shortGenerator = new ShortGenerator();
        Range range = null;
        if (min != null || max != null) {
            range = new RangeBase() {
                @Override
                public short minShort() {
                    return (min == null) ? super.minShort() : min;
                }

                @Override
                public short maxShort() {
                    return (max == null) ? super.maxShort() : max;
                }

            };
        }
        for (int i = 0; i < 10000; i++) {
            short s = shortGenerator.generateWithRange(range, context);
            if (min != null) {
                Assert.assertTrue(s >= min);
            }
            if (max != null) {
                Assert.assertTrue(s <= max);
            }
        }
    }
}

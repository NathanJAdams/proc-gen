package com.ripplargames.procgen.generators;

import com.ripplargames.procgen.JdkRandom;
import com.ripplargames.procgen.Context;
import com.ripplargames.procgen.ProcgenException;
import com.ripplargames.procgen.RandomWrapper;
import com.ripplargames.procgen.annotations.Range;
import com.ripplargames.procgen.range.LongGenerator;
import com.ripplargames.procgen.util.RangeBase;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

public class LongGeneratorTest {
    @Test
    public void testGood() throws ProcgenException {
        performTest((long) 0, (long) 9);
        performTest((long) 0, null);
        performTest(null, (long) 9);
        performTest(null, null);
        performTest((long) 0, (long) 0);
    }

    @Test(expected = ProcgenException.class)
    public void testInvalidRange() throws ProcgenException {
        performTest((long) 9, (long) 0);
    }

    private void performTest(Long min, Long max) throws ProcgenException {
        RandomWrapper random = new JdkRandom(new Random(0));
        Context context = new Context(random);
        LongGenerator longGenerator = new LongGenerator();
        Range range = null;
        if (min != null || max != null) {
            range = new RangeBase() {
                @Override
                public long minLong() {
                    return (min == null) ? super.minLong() : min;
                }

                @Override
                public long maxLong() {
                    return (max == null) ? super.maxLong() : max;
                }

            };
        }
        for (int i = 0; i < 10000; i++) {
            long L = longGenerator.generateWithRange(range, context);
            if (min != null) {
                Assert.assertTrue(L >= min);
            }
            if (max != null) {
                Assert.assertTrue(L <= max);
            }
        }
    }
}

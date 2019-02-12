package com.ripplargames.procgen.generators;

import com.ripplargames.procgen.JdkRandom;
import com.ripplargames.procgen.Context;
import com.ripplargames.procgen.ProcgenException;
import com.ripplargames.procgen.RandomWrapper;
import com.ripplargames.procgen.annotations.Range;
import com.ripplargames.procgen.range.IntGenerator;
import com.ripplargames.procgen.util.RangeBase;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

public class IntGeneratorTest {
    @Test
    public void testGood() throws ProcgenException {
        performTest(0, 9);
        performTest(0, null);
        performTest(null, 9);
        performTest(null, null);
        performTest(0, 0);
    }

    @Test(expected = ProcgenException.class)
    public void testInvalidRange() throws ProcgenException {
        performTest(9, 0);
    }

    private void performTest(Integer min, Integer max) throws ProcgenException {
        RandomWrapper random = new JdkRandom(new Random(0));
        Context context = new Context(random);
        IntGenerator intGenerator = new IntGenerator();
        Range range = null;
        if (min != null || max != null) {
            range = new RangeBase() {
                @Override
                public int minInt() {
                    return (min == null) ? super.minInt() : min;
                }

                @Override
                public int maxInt() {
                    return (max == null) ? super.maxInt() : max;
                }

            };
        }
        for (int i = 0; i < 10000; i++) {
            int ii = intGenerator.generateWithRange(range, context);
            if (min != null) {
                Assert.assertTrue(ii >= min);
            }
            if (max != null) {
                Assert.assertTrue(ii <= max);
            }
        }
    }
}

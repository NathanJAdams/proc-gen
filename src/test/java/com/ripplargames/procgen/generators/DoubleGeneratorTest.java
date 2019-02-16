package com.ripplargames.procgen.generators;

import com.ripplargames.procgen.JdkRandom;
import com.ripplargames.procgen.Context;
import com.ripplargames.procgen.ProcgenException;
import com.ripplargames.procgen.RandomWrapper;
import com.ripplargames.procgen.annotations.Range;
import com.ripplargames.procgen.range.DoubleGenerator;
import com.ripplargames.procgen.util.RangeBase;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

public class DoubleGeneratorTest {
    @Test
    public void testGood() throws ProcgenException {
        performTest((double) 0, (double) 9);
        performTest((double) 0, null);
        performTest(null, (double) 9);
        performTest(null, null);
        performTest((double) 0, (double) 0);
    }

    @Test(expected = ProcgenException.class)
    public void testInvalidRange() throws ProcgenException {
        performTest((double) 9, (double) 0);
    }

    private void performTest(Double min, Double max) throws ProcgenException {
        RandomWrapper random = new JdkRandom(new Random(0));
        Context context = new Context(random);
        DoubleGenerator doubleGenerator = new DoubleGenerator();
        Range range = null;
        if (min != null || max != null) {
            range = new RangeBase() {
                @Override
                public double minDouble() {
                    return (min == null) ? super.minDouble() : min;
                }

                @Override
                public double maxDouble() {
                    return (max == null) ? super.maxDouble() : max;
                }

            };
        }
        for (int i = 0; i < 10000; i++) {
            double d = doubleGenerator.generateWithRange(range, context);
            if (min != null) {
                Assert.assertTrue(d >= min);
            }
            if (max != null) {
                Assert.assertTrue(d <= max);
            }
        }
    }
}

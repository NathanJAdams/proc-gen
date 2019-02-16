package com.ripplargames.procgen.generators;

import com.ripplargames.procgen.JdkRandom;
import com.ripplargames.procgen.Context;
import com.ripplargames.procgen.ProcgenException;
import com.ripplargames.procgen.RandomWrapper;
import com.ripplargames.procgen.annotations.Range;
import com.ripplargames.procgen.range.FloatGenerator;
import com.ripplargames.procgen.util.RangeBase;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

public class FloatGeneratorTest {
    @Test
    public void testGood() throws ProcgenException {
        performTest((float) 0, (float) 9);
        performTest((float) 0, null);
        performTest(null, (float) 9);
        performTest(null, null);
        performTest((float) 0, (float) 0);
    }

    @Test(expected = ProcgenException.class)
    public void testInvalidRange() throws ProcgenException {
        performTest((float) 9, (float) 0);
    }

    private void performTest(Float min, Float max) throws ProcgenException {
        RandomWrapper random = new JdkRandom(new Random(0));
        Context context = new Context(random);
        FloatGenerator floatGenerator = new FloatGenerator();
        Range range = null;
        if (min != null || max != null) {
            range = new RangeBase() {
                @Override
                public float minFloat() {
                    return (min == null) ? super.minFloat() : min;
                }

                @Override
                public float maxFloat() {
                    return (max == null) ? super.maxFloat() : max;
                }

            };
        }
        for (int i = 0; i < 10000; i++) {
            float f = floatGenerator.generateWithRange(range, context);
            if (min != null) {
                Assert.assertTrue(f >= min);
            }
            if (max != null) {
                Assert.assertTrue(f <= max);
            }
        }
    }
}

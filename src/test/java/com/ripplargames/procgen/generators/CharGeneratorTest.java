package com.ripplargames.procgen.generators;

import com.ripplargames.procgen.JdkRandom;
import com.ripplargames.procgen.Context;
import com.ripplargames.procgen.ProcgenException;
import com.ripplargames.procgen.RandomWrapper;
import com.ripplargames.procgen.annotations.Range;
import com.ripplargames.procgen.range.CharGenerator;
import com.ripplargames.procgen.util.RangeBase;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

public class CharGeneratorTest {
    @Test
    public void testGood() throws ProcgenException {
        performTest('a', 'z');
        performTest('a', null);
        performTest(null, 'z');
        performTest(null, null);
        performTest('a', 'a');
    }

    @Test(expected = ProcgenException.class)
    public void testInvalidRange() throws ProcgenException {
        performTest('z', 'a');
    }

    private void performTest(Character min, Character max) throws ProcgenException {
        RandomWrapper random = new JdkRandom(new Random(0));
        Context context = new Context(random);
        CharGenerator charGenerator = new CharGenerator();
        Range range = null;
        if (min != null || max != null) {
            range = new RangeBase() {
                @Override
                public char minChar() {
                    return (min == null) ? super.minChar() : min;
                }

                @Override
                public char maxChar() {
                    return (max == null) ? super.maxChar() : max;
                }

            };
        }
        for (int i = 0; i < 10000; i++) {
            char c = charGenerator.generateWithRange(range, context);
            if (min != null) {
                Assert.assertTrue(c >= min);
            }
            if (max != null) {
                Assert.assertTrue(c <= max);
            }
        }
    }
}

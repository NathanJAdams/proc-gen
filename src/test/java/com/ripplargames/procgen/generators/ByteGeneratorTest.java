package com.ripplargames.procgen.generators;

import com.ripplargames.procgen.JdkRandom;
import com.ripplargames.procgen.Context;
import com.ripplargames.procgen.ProcgenException;
import com.ripplargames.procgen.RandomWrapper;
import com.ripplargames.procgen.annotations.Range;
import com.ripplargames.procgen.range.ByteGenerator;
import com.ripplargames.procgen.util.RangeBase;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

public class ByteGeneratorTest {
    @Test
    public void testGood() throws ProcgenException {
        performTest((byte) 0, (byte) 9);
        performTest((byte) 0, null);
        performTest(null, (byte) 9);
        performTest(null, null);
        performTest((byte) 0, (byte) 0);
    }

    @Test(expected = ProcgenException.class)
    public void testInvalidRange() throws ProcgenException {
        performTest((byte) 9, (byte) 0);
    }

    private void performTest(Byte min, Byte max) throws ProcgenException {
        RandomWrapper random = new JdkRandom(new Random(0));
        Context context = new Context(random);
        ByteGenerator byteGenerator = new ByteGenerator();
        Range range = null;
        if (min != null || max != null) {
            range = new RangeBase() {
                @Override
                public byte minByte() {
                    return (min == null) ? super.minByte() : min;
                }

                @Override
                public byte maxByte() {
                    return (max == null) ? super.maxByte() : max;
                }

            };
        }
        for (int i = 0; i < 10000; i++) {
            byte b = byteGenerator.generateWithRange(range, context);
            if (min != null) {
                Assert.assertTrue(b >= min);
            }
            if (max != null) {
                Assert.assertTrue(b <= max);
            }
        }
    }
}

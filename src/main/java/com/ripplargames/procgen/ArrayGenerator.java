package com.ripplargames.procgen;

import com.ripplargames.procgen.annotations.Array;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

public class ArrayGenerator implements Generator<Object> {
    @Override
    public boolean isGeneratable(Class<?> type, Annotation[] annotations, Context context) throws ProcgenException {
        if (!type.isArray()) {
            return false;
        }
        if (context.findFirstOrNull(annotations, Array.class) == null) {
            throw new ProcgenException("Array fields must have a " + Array.class.getSimpleName() + " annotation");
        }
        return true;
    }

    @Override
    public Object generateNext(Class<?> type, Annotation[] annotations, Context context) throws ProcgenException {
        Class<?> componentType = type.getComponentType();
        if (componentType == null) {
            throw new ProcgenException("Cannot determine array type");
        }
        Array procgenArray = context.findFirstOrNull(annotations, Array.class);
        int count = getCount(procgenArray, context);
        Object array = createArray(componentType, count);
        Annotation[] annotationsWithoutCount = Arrays.stream(annotations).filter(a -> (!(a instanceof Array))).toArray(Annotation[]::new);
        List<?> list = context.generateList(componentType, annotationsWithoutCount, count);
        for (int i = 0; i < count; i++) {
            java.lang.reflect.Array.set(array, i, list.get(i));
        }
        return array;
    }

    private int getCount(Array array, Context context) {
        int min = Math.max(0, array.min());
        int max = Math.max(0, array.max());
        return context.getRandom().nextInt(min, max);
    }

    private Object createArray(Class<?> componentType, int count) {
        if (componentType == Boolean.TYPE) {
            return new boolean[count];
        } else if (componentType == Byte.TYPE) {
            return new byte[count];
        } else if (componentType == Short.TYPE) {
            return new short[count];
        } else if (componentType == Character.TYPE) {
            return new char[count];
        } else if (componentType == Integer.TYPE) {
            return new int[count];
        } else if (componentType == Long.TYPE) {
            return new long[count];
        } else if (componentType == Float.TYPE) {
            return new float[count];
        } else if (componentType == Double.TYPE) {
            return new double[count];
        } else {
            return java.lang.reflect.Array.newInstance(componentType, count);
        }
    }
}

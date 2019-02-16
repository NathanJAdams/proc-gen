package com.ripplargames.procgen;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class ObjectGenerator implements Generator<Object> {
    @Override
    public boolean isGeneratable(Class<?> type, Annotation[] annotations, Context context) throws ProcgenException {
        return !type.isPrimitive()
                && !type.isArray()
                && !type.isEnum()
                && !type.isAnnotation()
                && !type.isAnonymousClass()
                && !type.isInterface()
                && !type.isLocalClass()
                && !type.isMemberClass();
    }

    @Override
    public Object generateNext(Class<?> type, Annotation[] annotations, Context context) throws ProcgenException {
        Object instance = createFromClass(type);
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            boolean isAccessible = field.isAccessible();
            field.setAccessible(true);
            Annotation[] subFieldAnnotations = field.getDeclaredAnnotations();
            Class<?> subFieldType = field.getType();
            Object subFieldInstance = context.generate(subFieldType, subFieldAnnotations);
            try {
                field.set(instance, subFieldInstance);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new ProcgenException("Cannot set value " + subFieldInstance + " on " + type.getName() + '.' + field.getName(), e);
            }
            field.setAccessible(isAccessible);
        }
        return instance;
    }

    private <T> T createFromClass(Class<T> cls) throws ProcgenException {
        try {
            return cls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ProcgenException("Cannot generate class " + cls, e);
        }
    }
}

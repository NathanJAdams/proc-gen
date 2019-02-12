package com.ripplargames.procgen;

import com.ripplargames.procgen.annotations.Collection;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

public class CollectionGenerator implements Generator<Object> {
    @Override
    public boolean isGeneratable(Class<?> type, Annotation[] annotations, Context context) throws ProcgenException {
        if (!java.util.Collection.class.isAssignableFrom(type)) {
            return false;
        }
        Collection collection = context.findFirstOrNull(annotations, Collection.class);
        if (collection == null) {
            throw new ProcgenException("Collection fields must have a " + Collection.class.getSimpleName() + " annotation");
        }
        if (!type.isAssignableFrom(collection.collectionClass())) {
            throw new ProcgenException(Collection.class.getSimpleName() + " annotation must be used with a class it is assignable to");
        }
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object generateNext(Class<?> type, Annotation[] annotations, Context context) throws ProcgenException {
        Collection procgenCollection = context.findFirstOrNull(annotations, Collection.class);
        java.util.Collection collection = createCollection(procgenCollection);
        int count = getCount(procgenCollection, context);
        Annotation[] annotationsWithoutCount = Arrays.stream(annotations).filter(a -> (!(a instanceof Collection))).toArray(Annotation[]::new);
        List list = context.generateList(procgenCollection.elementType(), annotationsWithoutCount, count);
        for (int i = 0; i < count; i++) {
            collection.add(list.get(i));
        }
        return collection;
    }

    private java.util.Collection createCollection(Collection collection) throws ProcgenException {
        try {
            return collection.collectionClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ProcgenException("Cannot create Collection instance from " + collection.collectionClass());
        }
    }

    private int getCount(Collection collection, Context context) {
        int min = Math.max(0, collection.min());
        int max = Math.max(0, collection.max());
        return context.getRandom().nextInt(min, max);
    }
}

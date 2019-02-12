package com.ripplargames.procgen;

import com.ripplargames.procgen.annotations.Choice;
import com.ripplargames.procgen.annotations.Optional;
import com.ripplargames.procgen.range.ByteGenerator;
import com.ripplargames.procgen.range.CharGenerator;
import com.ripplargames.procgen.range.DoubleGenerator;
import com.ripplargames.procgen.range.FloatGenerator;
import com.ripplargames.procgen.range.IntGenerator;
import com.ripplargames.procgen.range.LongGenerator;
import com.ripplargames.procgen.range.ShortGenerator;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;

public class Context {
    @Getter
    private final RandomWrapper random;
    private final List<Generator<?>> allGenerators = new ArrayList<>();
    private final Map<Type, List<?>> choices = new HashMap<>();

    public Context() {
        this(0);
    }

    public Context(long jdkRandomSeed) {
        this(new JdkRandom(jdkRandomSeed));
    }

    public Context(RandomWrapper random) {
        this.random = random;
        allGenerators.add(new ArrayGenerator());
        allGenerators.add(new CollectionGenerator());
        allGenerators.add(new EnumGenerator());
        allGenerators.add(new EnumNameGenerator());
        allGenerators.add(new EnumOrdinalGenerator());
        allGenerators.add(new ChoiceGenerator());
        allGenerators.add(new BooleanGenerator());
        allGenerators.add(new ByteGenerator());
        allGenerators.add(new CharGenerator());
        allGenerators.add(new ShortGenerator());
        allGenerators.add(new IntGenerator());
        allGenerators.add(new LongGenerator());
        allGenerators.add(new FloatGenerator());
        allGenerators.add(new DoubleGenerator());
        allGenerators.add(new ObjectGenerator());
    }

    public <T> T generate(Class<T> type) throws ProcgenException {
        return generate(type, new Annotation[0]);
    }

    <T> List<T> generateList(Class<T> type, Annotation[] annotations, int count) throws ProcgenException {
        List<T> list = new ArrayList<>();
        Generator<T> generator = getGenerator(type, annotations);
        for (int i = 0; i < count; i++) {
            T instance = generator.generateNext(type, annotations, this);
            list.add(instance);
        }
        return list;
    }

    <T> T generate(Class<T> type, Annotation[] annotations) throws ProcgenException {
        if (hasOptionalVetoed(annotations)) {
            return null;
        }
        Generator<T> generator = getGenerator(type, annotations);
        return generator.generateNext(type, annotations, this);
    }

    private boolean hasOptionalVetoed(Annotation[] annotations) {
        Optional optional = findFirstOrNull(annotations, Optional.class);
        if (optional == null) {
            return false;
        }
        return (random.nextDouble(0, 1) > optional.generatedChance());
    }

    @SuppressWarnings("unchecked")
    private <T> Generator<T> getGenerator(Class<T> type, Annotation[] annotations) throws ProcgenException {
        for (Generator<?> generator : allGenerators) {
            if (generator.isGeneratable(type, annotations, this)) {
                return (Generator<T>) generator;
            }
        }
        throw new ProcgenException("No generator could be found for type " + type.getName());
    }

    List<?> getChoices(Choice choice) throws ProcgenException {
        Class<? extends ChoiceProvider> choiceProviderClass = choice.choiceProvider();
        List<?> list = choices.get(choiceProviderClass);
        if (list == null) {
            try {
                ChoiceProvider<?> choiceProvider = choiceProviderClass.newInstance();
                list = choiceProvider.choices();
                choices.put(choiceProviderClass, list);
            } catch (InstantiationException | IllegalAccessException e) {
                choices.put(choiceProviderClass, null);
                throw new ProcgenException("Cannot create choice provider from class " + choiceProviderClass);
            }
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    <A extends Annotation> A findFirstOrNull(Annotation[] annotations, Class<A> annotationClass) {
        return (A) Arrays.stream(annotations)
                .filter(a -> a.annotationType() == annotationClass)
                .findFirst()
                .orElse(null);
    }
}

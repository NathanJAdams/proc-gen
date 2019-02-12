## A library for procedurally generating Java objects.

### Usage
Create data classes to represent your objects, eg.

    public class Animal {
        private final AnimalType type;
        private final String name;
        private final Head head;
        private final List<Limb> limbs;
    }

Now add annotations to fields from the [procgen/annotations](src/main/java/com/ripplargames/procgen/annotations) package, eg.

    public class Animal {
        private final AnimalType type;
        @Choice(choiceProvider = AnimalNames.class)
        private final String name;
        private final Head head;
        @Collection(min = 2, max = 4, collectionClass = ArrayList.class, elementType = Limb.class)
        private final List<Limb> limbs;
    }


Not all fields will need annotating, eg. Enums and your own class references don't necessarily require annotating. In the example above, if <code>AnimalType</code> is an Enum, a random one will be chosen from <code>AnimalType.values()</code>. Likewise, a procedurally generated Head object will be created automatically.<br>
To generate a procedural object, create a Context object and invoke the <code>T generate(Class<T> type)</code> method.
This will create and return a new procedurally generated instance.

### TODO
There is currently poor support for recursion or cyclic dependencies but this is on the TODO list. Until then, use the @Optional annotation, bearing in mind this may still cause a StackOverflow.

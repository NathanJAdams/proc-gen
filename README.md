## A library for procedurally generating Java objects.

### Usage
Create data classes to represent your objects, these only require the type and names, eg.

<code>
public class Animal {<br>
&nbsp;private final String name;<br>
&nbsp;private final Head head;<br>
&nbsp;private final List&lt;Limb&gt; limbs;<br>
}
</code>

Now add annotations to each field from the [procgen/annotations](src/main/java/com/ripplargames/procgen/annotations) package, eg.

<code>
public class Animal {<br>
&nbsp;@Choice(choiceProvider = AnimalNames.class)<br>
&nbsp;private final String name;<br><br>
&nbsp;private final Head head;<br><br>
&nbsp;@Collection(min = 2, max = 4, collectionClass = ArrayList.class, elementType = Limb.class)<br>
&nbsp;private final List&lt;Limb&gt; limbs;<br>
}
</code>


To generate a procedural object, create a Context object and invoke the generate(Class type) function.
This will return a new procedurally generated instance.
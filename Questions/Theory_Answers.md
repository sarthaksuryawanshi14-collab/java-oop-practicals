# Non-Programming Questions (Java OOP, Hard Level)

## Q1. Abstract class vs Interface. When do you choose which?

| Point | Abstract class | Interface |
|---|---|---|
| Methods | Abstract and concrete | Abstract, `default`, `static`, `private` |
| Fields | Any (instance, static, final) | Only `public static final` constants |
| Constructors | Yes | No |
| Inheritance | A class extends only **one** | A class implements **many** |
| Relationship | "IS-A" with shared state/code | "CAN-DO" capability/contract |

Choose an **abstract class** when subclasses share state or common code (`Shape` with a `name` field). Choose an **interface** to define a capability that unrelated classes can have (`Comparable`, `Runnable`) or when multiple inheritance of type is needed.

## Q2. Method overloading vs overriding

- **Overloading**: same name, different parameter list, same class (or subclass). Resolved at **compile time** (static binding). Return type alone cannot differ.
- **Overriding**: subclass redefines a superclass method with the same signature. Resolved at **runtime** (dynamic binding). Access cannot be reduced, return type must be same or covariant, and it cannot throw broader checked exceptions. `static`, `final` and `private` methods cannot be overridden.

## Q3. Meaning of `static`, `final`, `this` and `super`

- `static`: belongs to the class, not the object (shared variable, utility method, static block, nested class).
- `final`: variable cannot be reassigned, method cannot be overridden, class cannot be extended.
- `this`: reference to the current object. Used to resolve name clashes and to call another constructor `this(...)`.
- `super`: reference to the parent. Used for `super.method()`, `super.field` and `super(...)`, which must be the first statement in a constructor.

## Q4. Explain JVM, JRE and JDK. How does Java achieve platform independence? What is garbage collection?

- **JDK** = JRE + development tools (`javac`, `jar`, debugger).
- **JRE** = JVM + core libraries needed to run programs.
- **JVM** = executes bytecode. Parts: Class Loader, Runtime Data Areas (Heap, Stack, Method Area, PC register, Native stack), Execution Engine (interpreter + JIT), Garbage Collector.
- **Platform independence**: `javac` compiles `.java` to platform-neutral **bytecode** (`.class`). Each OS has its own JVM that runs the same bytecode ("write once, run anywhere").
- **Garbage collection**: the JVM automatically frees heap objects that are no longer reachable from any live reference. `System.gc()` is only a hint.

## Q5. Explain the four pillars of OOP with an example each

1. **Encapsulation**: private fields + public getters/setters (`Student.marks` hidden, accessed via `addMarks()` with validation).
2. **Inheritance**: `Student extends Person` reuses `id` and `name`.
3. **Polymorphism**: `Person p = new Student(...)`; `p.getRole()` runs Student's version at runtime.
4. **Abstraction**: expose *what* an object does, hide *how* (abstract class `Person`, interface `Gradable`).

## Q6. Checked vs unchecked exceptions. `throw` vs `throws`. What does `finally` guarantee?

- **Checked** (extend `Exception`, not `RuntimeException`): checked by the compiler, must be caught or declared (`IOException`, `SQLException`).
- **Unchecked** (extend `RuntimeException`) and **Errors**: not forced (`NullPointerException`, `ArithmeticException`).
- `throw` actually throws one exception object. `throws` declares in the method signature which exceptions may escape.
- `finally` runs whether or not an exception occurred, even with `return`. It is skipped only if the JVM exits (`System.exit`) or crashes. `try-with-resources` closes `AutoCloseable` resources automatically.

## Q7. Thread life cycle and synchronization. `sleep()` vs `wait()`

- States: **NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED**.
- Without synchronization, two threads updating shared data cause a **race condition**. `synchronized` gives mutual exclusion (one thread per monitor lock) and visibility of changes.
- `sleep(ms)`: a `Thread` method that pauses the thread but **keeps** any lock it holds.
- `wait()`: an `Object` method, must be called inside `synchronized`, **releases** the lock, and waits until `notify()`/`notifyAll()`.
- **Deadlock** happens when threads hold locks and wait for each other in a cycle. Avoid it with a consistent lock order.

## Q8. `==` vs `equals()`. The `hashCode()` contract. How does HashMap work internally?

- `==` compares references for objects (values for primitives). `equals()` compares logical content when overridden.
- **Contract**: if `a.equals(b)` then `a.hashCode() == b.hashCode()`. The reverse is not required. If you override `equals`, you must override `hashCode`.
- **HashMap**: computes `hash(key)`, picks a bucket index, then compares keys with `equals()` inside the bucket. Collisions are stored in a linked list, converted to a balanced tree when a bucket grows beyond 8 entries (Java 8+). It resizes at load factor 0.75. Allows one `null` key. It is not thread-safe.

## Q9. Java Collections: List vs Set vs Map. ArrayList vs LinkedList. HashMap vs TreeMap vs LinkedHashMap

- **List**: ordered, allows duplicates, index access. **Set**: no duplicates. **Map**: key to value pairs, unique keys (not a `Collection` subtype).
- `ArrayList`: dynamic array, fast random access O(1), slow insert/delete in the middle O(n). `LinkedList`: doubly linked, fast insert/delete at ends or via iterator, slow `get(i)` O(n).
- `HashMap`: no order, O(1) average. `LinkedHashMap`: insertion order. `TreeMap`: sorted by key (Red-Black tree), O(log n), no null key.
- `Comparable` = natural order inside the class (`compareTo`). `Comparator` = external, custom order (`compare`).

## Q10. Explain the SOLID principles with a short example each

1. **S**ingle Responsibility: a class has one reason to change (`StudentManager` manages students, `ReportGenerator` builds reports).
2. **O**pen/Closed: open for extension, closed for modification. Add a new `Shape` subclass without editing existing code.
3. **L**iskov Substitution: a subclass must be usable wherever its parent is expected without breaking behaviour.
4. **I**nterface Segregation: prefer many small interfaces over one fat one.
5. **D**ependency Inversion: depend on abstractions (`Gradable`) rather than concrete classes.

This is a video store kata, based on the example from Martin Fowler's Refactoring book.

The kata's goal is to test the effectiveness of a rule that I came up with in an attempt to unify all Object-Oriented Programming best practices
and principles into a single, concise rule. I call the it **Unknown Receiver Rule** for short.

## Unknown Receiver Rule 
In any and all message sends inside of the application, it must not be possible to determine the concrete type of the receiver by just reading the current class' code.

How would the concrete type of a receiver be known?
- `this.foo()`: unless `foo` is abstract, the receiver is the own class and is clearly known.
- `SomeClass.someStaticMethod()`: receiver is `SomeClass`, also clearly known.
- `SomeType someVariable = new SomeType();` and `someVariable.doSomething()` in the same method: 
The object was created with a known type and used in the same method, so its type is clearly known.
- Same as above, but when the object is created in one method and used in another, and there's no way to inject a different object from outside (e.g., no constructor receiving it, no setter, etc).

Effects of applying this rule:
- **Single Responsibility Principle (SRP)**: Objects will be smaller because they cannot decompose complex operations in terms of the usual "private helper methods".
This means that an object responsible for complex operations will have to decompose them into smaller, simpler objects,
leading to a distribution of the responsiblities until all of them have a single one.
- **Open-Closed Principle (OCP)**: Since objects must decompose tasks by delegating to dependencies, and since dependencies cannot be created and used in the
same context, they must be provided from outside.
This means that the behavior of the object can be changed without modifying its source code, by providing different
implementations of its dependencies.
- **Liskov Substitution Princpiple (LSP)**: Since all dependencies are provided from outside, objects that respond to the same messages must also agree to follow the complete 
contract, so no surprises arise from passing different types at runtime.
- **Interface Segregation Principle (ISP)**: Since static and private helper methods cannot be used, the number of methods each object responds to is reduced.
This reduction translates also to the object's "interface area".
- **Dependency Inversion Principle (DIP)**: An object's dependencies are injected from outside, but it defines which messages it will send to each dependency.
This means that dependencies' contracts (or interfaces) are defined by the dependent-on object, and not the other way around.
- **Don't Repeat Yourself (DRY)**: Since private helper methods and static utilities cannot be used, common tasks which would 
usually be "tucked away" in those methods must be abstracted into own, globally available objects.
This leads to a situation where those common tasks are eventually generalized and reused.

Context of the used terminology:
- Message send:
In Java, C# and many other programming communities, this is usually said as "to invoking a method"
instead of "to send a message".
This term comes from Smalltalk is also used in Ruby communities. Form OO's original definition (Alan Kay's), 
in an OO system, all operations are performed by sending messages to objects.
This was translated to Java & others as "all operations are performed by invoking methods". 
To me, this is exactly a part of the problem: programmers are conditioned to think in terms of the specific implementations
that they want to invoke to produce the desired behavior, almost as if we were still programming with procedures.
The intention of saying that we should "send messages to objects" is exactly to condition programmers to think in terms of the
abstractions first, and implementations second.

(To be continued)

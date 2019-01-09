This is a video store kata, based on the example from Martin Fowler's Refactoring book.

The kata's goal is to test the effectiveness of a rule I developed, in an attempt to create a guideline formal enough to be automated, that when followed precicely, yields good object-oriented desings. I call it **Unknown Receiver Rule**, for short.

# Unknown Receiver Rule 
The rule is: 
***It must not be possible to determine at design-time the exact concrete type of the receiver of any message sent within an application.***

The scope of the rule is exactly one **class**, independently of which source file it is defined in.

Formally, the concrete type of a receiver can be known at design-time in the following situations (considering Java):
- `this.foo()`: the receiver is the own class, except if `foo` is abstract.
- `SomeClass.someStaticMethod()`: receiver is `SomeClass`.
- `SomeType someVariable = new SomeType();` and `someVariable.doSomething()`, with no way to change the content of `someVariable` from outside: 
The object was created with a known type and used in the same method, or in the same class without providing a way to inject a different object from outside (e.g., no constructor or setter injection).

Principles achieved by applying this rule:
- **Single Responsibility Principle (SRP)**: Objects will be smaller because they cannot decompose complex operations in terms of "private helper methods".
This means that an object responsible for complex operations will have to delegate into multiple, simpler objects,
leading to a distribution of the responsiblities until all of them have only one.
- **Open-Closed Principle (OCP)**: Because objects must decompose tasks by delegating to dependencies, and because dependencies cannot be created and used in the same context, they must be provided from outside.
This means that the behavior of the object can be changed without modifying its source code, by providing different
implementations of its dependencies.
- **Liskov Substitution Princpiple (LSP)**: Since all dependencies are provided from outside, objects that respond to the same messages must also agree to follow the complete 
contract, so no surprises arise from passing different types at runtime.
- **Interface Segregation Principle (ISP)**: Since static and private helper methods cannot be used, the number of methods each object responds to is reduced. This reduction translates also to the object's "interface area".
- **Dependency Inversion Principle (DIP)**: An object's dependencies are injected from outside, but it defines which messages it will send to each dependency.
This means that dependencies' contracts (or interfaces) are defined by the dependent-on object, and not the other way around.
- **Don't Repeat Yourself (DRY)**: Since private helper methods and static utility classes cannot be used, common tasks which would usually be "tucked away" in such places must be abstracted into own, globally available objects. This leads to a situation where those common tasks are generalized and reused, reducing repetition.

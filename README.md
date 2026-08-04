# Alchemy

Alchemy is a Scala library for **data model transformation**, inspired by functional programming principles and modern compiler architecture.

Its goal is to transform one representation of data into another through a pipeline of pure, strongly typed, and composable transformations.

Alchemy's first use case is the generation of SQL scripts from a declarative description of a relational schema. However, the library is designed to remain independent of both input and output formats. The domain model serves as the central representation around which all transformations are built.

The development of Alchemy is guided by the following principles:

* Favor pure transformations over side effects.
* Let the type system express the domain.
* Leverage the compiler to enforce domain invariants whenever possible.
* Build simple, composable, and testable transformation pipelines.
* Clearly separate the domain model from implementation details.

Alchemy is also a playground for exploring Scala 3, functional programming, and software design techniques inspired by modern compiler architecture.

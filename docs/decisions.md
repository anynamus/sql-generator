# Design Decisions

## DD-001 — ValidationResult

### Decision

The first version of `ValidationResult` is defined as:

```scala
type ValidationResult[+A] = Either[Vector[String], A]
```

### Context

The initial business validation rules require only plain-text error messages.

### Alternatives Considered

```scala
type ValidationResult[+A] = Either[Vector[ValidationError], A]
```

### Rationale

At this stage, business rules only need to identify which rules were violated.
Introducing a dedicated `ValidationError` model would add complexity without providing immediate value.
The design intentionally leaves room for future evolution. If business rules later require richer information (rule identifier, message, source location, severity, affected column, etc.), `String` can be replaced by `ValidationError` without changing the `Validator` contract.

## DD-002 — Validation Rules

### Decision

Each business rule is implemented as an independent validation rule.

### Rationale

This design follows the Single Responsibility Principle and allows rules to be added or removed independently.

The current contract returns `Option[String]`, as each business rule reports at most one violation.

If a future rule needs to report multiple violations, the contract may evolve to return `Vector[String]` or a richer validation model.

## DD-003 - ValidationError Representation

### Decision

Validation errors are represented by the `ValidationError` type alias.
This decision supersedes the original representation introduced in DD-001.

```scala
type ValidationError = String
```

### Rationale

This model is still simple while allowing it to evolve with minimal impact on the current API.

In future developments, this type could be replaced by an enum, a trait or any other necessary implementation.

### Consequence

The ValidationResult is now defined like this:

```scala
type ValidationResult[+A] = Either[Vector[ValidationError], A]
```
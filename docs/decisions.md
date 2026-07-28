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

If future requirements evolve (rule identifiers, source location, severity levels, etc.), the error type can be replaced with a richer `ValidationError` model without changing the `Validator` contract.
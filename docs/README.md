# Documentation Guidelines

This directory contains the project's documentation.

The goal of the documentation is to capture **knowledge that is not obvious from the source code**.

The documentation is intentionally lightweight and evolves incrementally with the project.

## Documentation Overview

| File                | Purpose                                                                      |
| ------------------- | ---------------------------------------------------------------------------- |
| `backlog.md`        | User stories and planned work.                                               |
| `business-rules.md` | Functional rules that define the expected behavior of the domain.            |
| `decisions.md`      | Design decisions and their rationale.                                        |
| `adr/`              | Architecture Decision Records (ADRs) describing major architectural choices. |

## General Principles

* Keep documentation concise.
* Prefer simple English over complex wording.
* Update the documentation as part of the same change that modifies the code.
* Document **why**, not **how**.
* Avoid duplicating information already expressed clearly in the source code.
* When documentation becomes outdated, update it or remove it.

## Design Decisions

`decisions.md` documents design decisions that influence the implementation but are not significant enough to justify a full ADR.

Typical examples include:

* choosing a data representation;
* introducing a new abstraction;
* defining an API contract;
* documenting an intentional trade-off;
* recording a design decision that may evolve in the future.

Each decision should follow this structure:

```markdown
## Decision Title

### Context

Describe the problem or motivation.

### Decision

Describe the chosen solution.

### Rationale

Explain why this solution was selected instead of other alternatives.

### Consequences

Describe the impact on the current implementation.

### Future Evolution (optional)

Describe possible future changes without committing to them.
```

A decision should explain **why the code looks the way it does**, not describe how the implementation works.

## Architecture Decision Records

An ADR should only be created for decisions that have a significant impact on the project's architecture.

Typical examples include:

* introducing a new architectural pattern;
* defining module boundaries;
* choosing a major external dependency;
* establishing long-term architectural principles.

ADRs are expected to change much less frequently than design decisions.

## Business Rules

`business-rules.md` documents the functional rules of the domain.

Business rules should:

* be implementation independent;
* describe observable behavior;
* remain valid even if the implementation changes.

Whenever possible, business rules should be traceable to automated tests.

## Backlog

The backlog tracks planned work using user stories.

A user story should describe **what** needs to be achieved, not how it will be implemented.

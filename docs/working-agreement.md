# Working Agreement

## Development

- Red → Green → Refactor
- Only green commits
- Small commits
- Continuous refactoring

## Testing

- Every production class is covered by tests.
- No ignored tests.
- Tests should be easy to read.

## Architecture

- Keep the domain independent.
- I/O belongs in repositories.
- Parsers do not validate business rules.

## Scala

- Prefer immutable collections.
- Prefer expressions over statements.
- Avoid null.
- Avoid mutable state whenever practical.
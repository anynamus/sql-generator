# Business Rules

## BR-001 — A table must contain at least one column

### Description

A table definition without any columns is invalid.

### Motivation

A SQL table without columns does not make sense in the context of this library.

### Expected Behaviour

The validator must report an error when the `columns` collection is empty.

## BR-002 — Column names must be unique

### Description

Two columns within the same table cannot have the same name.

### Motivation

A SQL statement cannot define multiple columns with the same identifier.

### Expected Behaviour

Validation fails if two or more columns have the same name.

## BR-003 — A table may contain only one AutoNumber column

### Description

Only one `AutoNumber` column is allowed per table.

### Motivation

The current model considers this column to be the table's technical key.

### Expected Behaviour

Validation fails if more than one column is of type `AutoNumber`.

# Future Rules

- References must point to an existing table.
- A reference column must target a candidate key.
- Table names must be unique within a schema.
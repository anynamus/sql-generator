package sqlgenerator.core

type ValidationResult[A] = Either[Vector[ValidationError], A]

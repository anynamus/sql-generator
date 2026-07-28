package sqlgenerator.core

type ValidationResult[A] = Either[Vector[String], A]

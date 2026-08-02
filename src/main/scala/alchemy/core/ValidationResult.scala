package alchemy.core

type ValidationResult[A] = Either[Vector[ValidationError], A]

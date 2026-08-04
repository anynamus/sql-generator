package io.github.anynamus.alchemy.core

type ValidationResult[A] = Either[Vector[ValidationError], A]

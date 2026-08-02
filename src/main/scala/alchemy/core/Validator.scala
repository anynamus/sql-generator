package alchemy.core

trait Validator[A]:
  def validate(value: A): ValidationResult[A]
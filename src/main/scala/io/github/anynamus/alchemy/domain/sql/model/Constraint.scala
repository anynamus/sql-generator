package io.github.anynamus.alchemy.domain.sql.model

sealed trait Constraint

object Constraint:
  case object NotNull extends Constraint
  final case class Reference(table: String) extends Constraint
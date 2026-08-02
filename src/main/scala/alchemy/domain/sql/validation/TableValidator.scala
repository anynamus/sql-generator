package alchemy.domain.sql.validation

import alchemy.core.{ValidationResult, Validator}
import alchemy.domain.sql.model.{ColumnType, Table}


private trait Rule:
  def validate(table: Table): Option[String]

private class AtLeastOneColumnValidator extends Rule:
  override def validate(table: Table): Option[String] =
    if(table.columns.isEmpty)
      Some("BR-001")
    else
      None

private class UniqueColumnNameValidator extends Rule:
  override def validate(table: Table): Option[String] =
    if(table.columns.map(c => c.name).toSet.size != table.columns.size)
      Some("BR-002")
    else
      None

private class UniqueAutoNumberValidator extends Rule:
  override def validate(table: Table): Option[String] =
    if(table.columns.count(c => ColumnType.AutoNumber == c.`type`) > 1)
      Some("BR-003")
    else
      None

class TableValidator extends Validator[Table]:

  private val rules = Vector(
    new AtLeastOneColumnValidator(),
    new UniqueColumnNameValidator(),
    new UniqueAutoNumberValidator()
  )

  override def validate(table: Table): ValidationResult[Table] =
    val violations = rules.flatMap(_.validate(table))

    if(violations.isEmpty)
      Right(table)
    else
      Left(violations)

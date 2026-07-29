package sqlgenerator.schema

import sqlgenerator.core.{ValidationResult, Validator}
import sqlgenerator.domain.{Column, ColumnType, Table}

class TableValidator extends Validator[Table]:
  override def validate(table: Table): ValidationResult[Table] =
    var errors = Vector.empty[String]

    if(!atLeastOneColumn(table.columns))
      errors :+= "BR-001"
    else
      if(!uniqueColumnName(table.columns))
        errors :+= "BR-002"
      if(!uniqueAutoNumber(table.columns))
        errors :+= "BR-003"

    if errors.isEmpty then Right(table)
    else Left(errors)

  private def atLeastOneColumn(columns: Vector[Column]): Boolean =
    columns.nonEmpty

  private def uniqueColumnName(columns: Vector[Column]): Boolean =
    columns.map(c => c.name).toSet.size == columns.size

  private def uniqueAutoNumber(columns: Vector[Column]): Boolean =
    columns.count(c => ColumnType.AutoNumber == c.`type`) == 1

package alchemy.domain.sql.model
import alchemy.domain.sql.model.ColumnType

enum ColumnType(val yamlName: String):
  case AutoNumber extends ColumnType("autonumber")
  case String extends ColumnType("string")

object ColumnType:
  def fromString(name: String):Option[ColumnType] =
    ColumnType.values.find(_.yamlName == name)

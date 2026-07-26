package sqlgenerator.schema

import sqlgenerator.core.Result
import sqlgenerator.domain.{Column, Table}
import sqlgenerator.yaml.YamlNode

class TableDecoder
  extends Decoder[Table] :
  override def decode(node: YamlNode): Result[Table] =
    node match
      case YamlNode.Mapping(fields) =>
        for
          tableName <- decodeTableName(fields.get("table"))
          columns <- decodeColumns(fields.get("columns"))
        yield Table(tableName, columns)
      case _ => Left("Expected a mapping for table definition")

  private def decodeTableName(optionNode: Option[YamlNode]): Result[String] =
    optionNode match
      case Some(YamlNode.Scalar(tableName)) => Right(tableName)
      case Some(_) => Left("Table name must be of type Scalar")
      case None => Left("Missing field 'table'")

  private def decodeColumns(optionNode:Option[YamlNode]): Result[Vector[Column]] =
    Right(Vector())

package alchemy.domain.sql.decoder

import alchemy.core.Traverse.traverse
import alchemy.core.{Decoder, Result}
import alchemy.domain.sql.model.{Column, ColumnType, Constraint}
import alchemy.yaml.YamlMappingOps.*
import alchemy.yaml.YamlNode
import alchemy.yaml.YamlNodeOps.*

class ColumnDecoder(constraintDecoder: Decoder[YamlNode, Constraint])
  extends Decoder[YamlNode,Column]:

  override def decode(node: YamlNode): Result[Column] =
    node match
      case YamlNode.Mapping(fields) =>
        for
          name <-
            fields.required("name")
              .flatMap(_.asScalar)
          `type` <-
            fields.required("type")
            .flatMap(_.asScalar)
            .flatMap(columnType => ColumnType
              .fromString(columnType)
              .toRight(s"Invalid column type '$columnType'"))
          constraints <- decodeConstraints(fields.get("constraints"))
        yield Column(name, `type`, constraints)
      case _ => Left("Expected a mapping for column definition")

  private def decodeConstraints(node: Option[YamlNode]): Result[Vector[Constraint]] =
    node match
      case None => Right(Vector.empty)
      case Some(constraints: YamlNode.Sequence) =>
        traverse(constraints.values)(constraintDecoder.decode)
      case _ => Left("Invalid constraint definition")

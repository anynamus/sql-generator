package alchemy.domain.sql.decoder

import alchemy.core.Result
import alchemy.domain.sql.decoder.Decoder
import alchemy.domain.sql.model.{Column, ColumnType}
import alchemy.yaml.YamlMappingOps.*
import alchemy.yaml.YamlNode
import alchemy.yaml.YamlNodeOps.*

class ColumnDecoder extends Decoder[Column]:
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
        yield Column(name, `type`)
      case _ => Left("Expected a mapping for column definition")

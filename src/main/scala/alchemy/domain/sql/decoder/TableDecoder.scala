package alchemy.domain.sql.decoder

import alchemy.core.Result
import alchemy.core.Traverse.traverse
import alchemy.domain.sql.model.{Column, Table}
import alchemy.yaml.YamlMappingOps.*
import alchemy.yaml.YamlNode
import alchemy.yaml.YamlNodeOps.*

class TableDecoder(columnDecoder: Decoder[Column]) extends Decoder[Table] :

  override def decode(node: YamlNode): Result[Table] =
    node match
      case YamlNode.Mapping(fields) =>
        for
          tableName <-
            fields.required("table")
              .flatMap(_.asScalar)
          columns <-
            fields.required("columns")
              .flatMap(_.asSequence)
              .flatMap(nodes => traverse(nodes)(columnDecoder.decode))
        yield Table(tableName, columns)
      case _ => Left("Expected a mapping for table definition")

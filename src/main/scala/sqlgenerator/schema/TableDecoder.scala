package sqlgenerator.schema

import sqlgenerator.core.Result
import sqlgenerator.core.Traverse.traverse
import sqlgenerator.domain.{Column, Table}
import sqlgenerator.yaml.YamlMappingOps.*
import sqlgenerator.yaml.YamlNode
import sqlgenerator.yaml.YamlNodeOps.*

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

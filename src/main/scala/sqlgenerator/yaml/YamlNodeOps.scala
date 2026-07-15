package sqlgenerator.yaml

import sqlgenerator.core.Result

object YamlNodeOps:

  extension (node: YamlNode)

    def asScalar: Result[String] =
      node match
        case YamlNode.Scalar(value) => Right(value)
        case YamlNode.Sequence(_) => Left("Cannot map a Sequence node to Scalar")
        case YamlNode.Mapping(_) => Left("Cannot map a Mapping node to Scalar")


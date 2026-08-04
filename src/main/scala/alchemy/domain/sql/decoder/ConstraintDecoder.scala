package alchemy.domain.sql.decoder

import alchemy.core.{Decoder, Result}
import alchemy.domain.sql.model.Constraint
import alchemy.domain.sql.model.Constraint.{NotNull, Reference}
import alchemy.yaml.YamlNode

class ConstraintDecoder extends Decoder[YamlNode,Constraint]:
  override def decode(node: YamlNode): Result[Constraint] =
    node match
      case YamlNode.Scalar(value) =>
        value match
          case "not null" => Right(NotNull)
          case _ => Left(s"Unknown constraint '$value'")
      case YamlNode.Mapping(fields) =>
        val allowed = Set("reference")
        val unknown = fields.keySet -- allowed
        if unknown.nonEmpty then {
          Left(s"Unknown constraint field(s): '${unknown.mkString(",")}'")
        } else
          fields.get("reference") match
            case Some(YamlNode.Scalar(value)) => Right(Reference(value))
            case _ => Left("Missing or invalid 'reference' field")
      case _ => Left("Expected a mapping or a scalar for constraint definition")

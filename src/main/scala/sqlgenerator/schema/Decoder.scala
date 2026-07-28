package sqlgenerator.schema

import sqlgenerator.core.Result
import sqlgenerator.yaml.YamlNode

trait Decoder[A]:

  def decode(
              node: YamlNode
            ): Result[A]
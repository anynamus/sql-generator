package alchemy.domain.sql.decoder

import alchemy.core.Result
import alchemy.yaml.YamlNode

trait Decoder[A]:

  def decode(
              node: YamlNode
            ): Result[A]
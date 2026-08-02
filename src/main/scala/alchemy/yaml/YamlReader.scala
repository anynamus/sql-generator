package alchemy.yaml

import alchemy.core.Result

trait YamlReader:

  def read(
            text: String
          ): Result[YamlNode]


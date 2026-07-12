package sqlgenerator.yaml

import sqlgenerator.core.Result

trait YamlReader:

  def read(
            text: String
          ): Result[YamlNode]


package io.github.anynamus.alchemy.yaml

import io.github.anynamus.alchemy.core.Result

object YamlMappingOps:

  extension (fields: Map[String, YamlNode])

    def required(name: String): Result[YamlNode] =
      fields
        .get(name)
        .toRight(s"Missing required field '$name'")

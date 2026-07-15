package sqlgenerator.schema

import org.scalatest.funsuite.AnyFunSuite
import sqlgenerator.yaml.YamlNode
import sqlgenerator.domain.Table

class TableDecoderSpec extends AnyFunSuite:

  private val decoder = new TableDecoder()

  test("A table node must be of type Mapping"):
    val node = YamlNode.Scalar("Customer")

    val result = decoder.decode(node)

    assert(result.isLeft)
    assert(result.left.getOrElse(null) == "Expected a mapping for table definition")


  test("reads a table without table definition"):
    val node = YamlNode.Mapping(Map("dummy" -> YamlNode.Scalar("Customer")))

    val result = decoder.decode(node)

    assert(result.isLeft)
    assert(result.left.getOrElse(null) == "Missing 'table' field")


  test("A table name must be of type Scalar"):
    val node = YamlNode.Mapping(Map("table" -> YamlNode.Sequence(Vector())))

    val result = decoder.decode(node)

    assert(result.isLeft)
    assert(result.left.getOrElse(null) == "Table name must be of type Scalar")


  test("Reads a table (without columns)"):
    val node = YamlNode.Mapping(Map("table" -> YamlNode.Scalar("Customer")))

    val result = decoder.decode(node)

    assert(result.isRight)
    assert(result.getOrElse(null) == Table("Customer", Vector.empty))

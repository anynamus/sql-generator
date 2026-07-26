package sqlgenerator.schema

import org.scalatest.funsuite.AnyFunSuite
import sqlgenerator.yaml.YamlNode
import sqlgenerator.domain.Table

class TableDecoderSpec extends AnyFunSuite:

  private val decoder = new TableDecoder()

  test("A table node must be of type Mapping"):
    val node = YamlNode.Scalar("Customer")

    val result = decoder.decode(node)

    result match
      case Left(error) =>
        assert(error == "Expected a mapping for table definition")

      case Right(_) =>
        fail("expected failure")


  test("reads a table without table definition"):
    val node = YamlNode.Mapping(Map("dummy" -> YamlNode.Scalar("Customer")))

    val result = decoder.decode(node)

    result match
      case Left(error) =>
        assert(error == "Missing field 'table'")

      case Right(_) =>
        fail("expected failure")


  test("A table name must be of type Scalar"):
    val node = YamlNode.Mapping(Map("table" -> YamlNode.Sequence(Vector())))

    val result = decoder.decode(node)

    result match
      case Left(error) =>
        assert(error == "Table name must be of type Scalar")

      case Right(_) =>
        fail("expected failure")


  test("Reads a table (without columns)"):
    val node = YamlNode.Mapping(Map("table" -> YamlNode.Scalar("Customer")))

    val result = decoder.decode(node)

    result match
      case Right(table) =>
        assert(table == Table("Customer", Vector.empty))
      case _ =>
        fail("expected table named Customer")

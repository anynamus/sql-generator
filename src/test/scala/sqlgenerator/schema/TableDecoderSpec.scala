package sqlgenerator.schema

import org.scalatest.funsuite.AnyFunSuite
import sqlgenerator.yaml.YamlNode
import sqlgenerator.domain.{Table, Column, ColumnType}

class TableDecoderSpec extends AnyFunSuite:

  private val decoder = new TableDecoder(new ColumnDecoder())

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
        assert(error == "Missing required field 'table'")

      case Right(_) =>
        fail("expected failure")


  test("A table name must be of type Scalar"):
    val node = YamlNode.Mapping(Map("table" -> YamlNode.Sequence(Vector())))

    val result = decoder.decode(node)

    result match
      case Left(error) =>
        assert(error == "Cannot map a Sequence node to Scalar")

      case Right(_) =>
        fail("expected failure")


  test("Reads a table with a single column"):
    val columns = YamlNode.Mapping(Map("name" -> YamlNode.Scalar("id"), "type" -> YamlNode.Scalar("autonumber")))
    val node = YamlNode.Mapping(Map("table" -> YamlNode.Scalar("Customer"), "columns" -> YamlNode.Sequence(Vector(columns))))

    val result = decoder.decode(node)

    result match
      case Right(table) =>
        assert(table == Table("Customer", Vector(Column("id", ColumnType.AutoNumber))))
      case _ =>
        fail("expected table named Customer")

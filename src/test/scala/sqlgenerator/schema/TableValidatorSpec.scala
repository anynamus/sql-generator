package sqlgenerator.schema

import org.scalatest.funsuite.AnyFunSuite
import sqlgenerator.domain.{Column, ColumnType, Table}

class TableValidatorSpec extends AnyFunSuite:
  private val validator = new TableValidator()

  test("BR-001 — A table must contain at least one column"):
    val table = Table("Customer", Vector.empty)

    val result = validator.validate(table)

    result match
      case Left(errors) => assert(errors == Vector("BR-001"))
      case Right(_) => fail("error expected")

  test("BR-002 — Column names must be unique"):
    val table = Table("Customer", Vector(Column("id", ColumnType.AutoNumber), Column("id", ColumnType.String)))

    val result = validator.validate(table)

    result match
      case Left(errors) => assert(errors == Vector("BR-002"))
      case Right(_) => fail("error expected")

  test("BR-003 — A table may contain only one AutoNumber column"):
    val table = Table("Customer", Vector(Column("id", ColumnType.AutoNumber), Column("name", ColumnType.AutoNumber)))

    val result = validator.validate(table)

    result match
      case Left(errors) => assert(errors == Vector("BR-003"))
      case Right(_) => fail("error expected")

  test("BR-002 & BR-003 Violated"):
    val table = Table("Customer", Vector(Column("id", ColumnType.AutoNumber), Column("id", ColumnType.AutoNumber)))

    val result = validator.validate(table)

    result match
      case Left(errors) => assert(errors == Vector("BR-002", "BR-003"))
      case Right(_) => fail("error expected")

  test("A valid table has no error"):
    val table = Table("Customer", Vector(Column("id", ColumnType.AutoNumber)))

    val result = validator.validate(table)

    result match
      case Right(t) => assert(t == table)
      case Left(_) => fail("no error expected")

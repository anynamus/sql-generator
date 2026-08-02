package alchemy.domain.sql.model

import alchemy.domain.sql.model.{Column, ColumnType, Table}
import org.scalatest.funsuite.AnyFunSuite

class TableSpec extends AnyFunSuite:

  test("create a table"):

    val table =
      Table(
        "Customer",
        Vector(
          Column(
            "id",
            ColumnType.AutoNumber
          ),
          Column(
            "name",
            ColumnType.String
          )
        )
      )

    assert(table.name == "Customer")
    assert(table.columns.size == 2)

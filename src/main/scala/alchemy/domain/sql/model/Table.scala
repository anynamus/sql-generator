package alchemy.domain.sql.model
import alchemy.domain.sql.model.Column

final case class Table(
                        name: String,
                        columns: Vector[Column]
                      )
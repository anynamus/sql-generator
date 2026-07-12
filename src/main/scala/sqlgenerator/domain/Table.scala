package sqlgenerator.domain

final case class Table(
                        name: String,
                        columns: Vector[Column]
                      )
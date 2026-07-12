package sqlgenerator.domain

final case class Column(
                         name: String,
                         `type`: ColumnType
                       )
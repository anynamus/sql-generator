package sqlgenerator.core

type Result[+A] = Either[String, A]
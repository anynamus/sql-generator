# Scala Notes

## Result

Nous utilisons un alias de type :

type Result[A] = Either[String, A]

plutôt qu'une hiérarchie dédiée.

Pourquoi ?

Parce que la bibliothèque standard fournit déjà toutes les opérations utiles (`map`, `flatMap`, `fold`, etc.).
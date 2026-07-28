# ADR-0001

## Incremental Development

The project is developed incrementally.

Every Git commit must:

- compile
- pass all tests
- be potentially releasable

# ADR-0002

## Contexte

SnakeYAML expose déjà son propre arbre.

## Décision

Créer notre propre représentation `YamlNode`.

## Conséquences

Le domaine ne dépend plus de SnakeYAML.

# ADR-0003

## Contexte

Le modèle `YamlNode` pourrait être généralisé afin de représenter un document hiérarchique indépendamment de son format d'origine.

## Décision

Conserver le nom `YamlNode` tant que YAML reste l'unique source prise en charge.

## Conséquences

Si une seconde source (JSON, XML...) apparaît, réévaluer cette décision et envisager l'introduction d'un type `Node` ou `DocumentNode`.
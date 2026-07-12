# Architecture

```mermaid
flowchart LR

    YAML["YAML document"]

    Reader["SnakeYamlReader"]

    Node["YamlNode"]

    YAML --> Reader

    Reader --> Node
```

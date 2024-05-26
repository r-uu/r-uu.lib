# no integration, plain markdown
```plantuml
@startuml 1
a->b
@enduml
```

# include local file puml
```plantuml 2
@startuml
!include integration-test.puml
@enduml
```

# include local file iuml
```plantuml 3
@startuml
!include integration-test.iuml
@enduml
```

# use url
![use url](https://github.com/r-uu/r-uu.lib/blob/main/markdown-plantuml-integration-test/integration-test.puml)

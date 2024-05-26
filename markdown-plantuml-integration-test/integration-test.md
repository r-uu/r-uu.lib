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

# use url puml
![use url](https://github.com/r-uu/r-uu.lib/blob/main/markdown-plantuml-integration-test/integration-test.puml)

# use url iuml
![use url](https://github.com/r-uu/r-uu.lib/blob/main/markdown-plantuml-integration-test/integration-test.iuml)

# use url working example from elsewhere
![your-UML-diagram-name](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/jonashackt/plantuml-markdown/master/example-uml.iuml)

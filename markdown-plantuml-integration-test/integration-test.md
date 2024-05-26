# no integration, plain markdown
```plantuml
@startuml 1
a->b
@enduml
```

# include local file puml
```plantuml
@startuml 2
!include integration-test.puml
@enduml
```

# include local file iuml
```plantuml
@startuml 3
!include integration-test.iuml
@enduml
```

# use url puml
![use url puml](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://github.com/r-uu/r-uu.lib/blob/main/markdown-plantuml-integration-test/integration-test.puml)

# use url puml 2
![use url iuml](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/r-uu/r-uu.lib/main/markdown-plantuml-integration-test/integration-test.puml)

# use url puml 3
![use url iuml](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/r-uu/r-uu.lib/main/lib/fx/comp/doc/fx-comp-architecture-overview.puml)

# use url iuml
![use url iuml](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://github.com/r-uu/r-uu.lib/blob/main/markdown-plantuml-integration-test/integration-test.iuml)

# use url iuml 2
![use url iuml](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/r-uu/r-uu.lib/main/markdown-plantuml-integration-test/integration-test.iuml)

# use url working example from elsewhere
![your-UML-diagram-name](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/jonashackt/plantuml-markdown/master/example-uml.iuml)

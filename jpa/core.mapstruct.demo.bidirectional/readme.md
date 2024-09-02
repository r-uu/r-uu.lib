@startuml

skinparam linetype ortho
' skinparam linetype polyline

'''''''
' types
'''''''

interface DTO<Entity>

interface Entity<DTO>
{
    Long  id()
    Short version()
}

interface Node<T extends Node>
{
    String      name();
    Optional<T> parent();
    List<T>     children();

	boolean add   (T node);
    boolean remove(T node);
}

abstract class AbstractDTO<AbstractEntity> implements DTO, Entity
abstract class AbstractEntity<AbstractDTO> implements Entity
{
    protected void mapIdAndVersion(D source)
}

interface BiMappedSource<T extends BiMappedTarget>
{
    void beforeMapping(T input);
    void afterMapping (T input);

	T toTarget();
}
interface BiMappedTarget<S extends BiMappedSource>
{
    void beforeMapping(S input); 
    void afterMapping (S input);

	S toSource();
}

abstract class AbstractMappedEntity extends AbstractEntity
abstract class AbstractMappedDTO extends AbstractDTO

abstract class AbstractNode<T extends AbstractNode> implements Node<T>
{
    - String name
    - T parent
    - List<T> children
    + <<Create>> AbstractNode(String)
    + <<Create>> AbstractNode(String,T)
    + Optional<T> parent()
    + List<T> children()
    + boolean add(T)
    + boolean remove(T)
}

'''''''''''
' relations
'''''''''''

DTO         -left- Entity
AbstractDTO -left- AbstractEntity

BiMappedSource - BiMappedTarget

BiMappedTarget <|-- AbstractMappedDTO
BiMappedSource <|-- AbstractMappedEntity

@enduml
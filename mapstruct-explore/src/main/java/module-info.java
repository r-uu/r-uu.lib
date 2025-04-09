module mapstruct.explore
{

    requires org.mapstruct;
	requires org.slf4j;
	requires de.ruu.lib.mapstruct;
    requires org.jetbrains.annotations;
    requires de.ruu.lib.jpa.core;
    requires static lombok;
    requires jakarta.annotation;

    opens de.ruu.lib.mapstruct.explore.objectfactory to org.mapstruct;
}
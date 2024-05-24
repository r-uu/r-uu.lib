module ruu.lib.jpa.se.eclipselink
{
	exports de.ruu.lib.jpa.se.eclipselink;

	requires eclipselink;
	requires jakarta.activation;
	requires jakarta.annotation;
	requires jakarta.persistence;
	requires java.sql;
	requires lombok;
//requires slf4j.api;
	requires org.slf4j;
	requires ruu.lib.jpa.core;
}
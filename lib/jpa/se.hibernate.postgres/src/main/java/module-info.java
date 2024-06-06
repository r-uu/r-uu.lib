module ruu.lib.jpa.se.hibernate.postgres
{
	exports de.ruu.lib.jpa.se.hibernate.postgres;
	opens   de.ruu.lib.jpa.se.hibernate.postgres;

	requires jakarta.annotation;
	requires jakarta.cdi;
	requires jakarta.el;
	requires jakarta.inject;
	requires jakarta.persistence;
	requires java.sql;
	requires lombok;
	requires org.slf4j;
	requires microprofile.config.api;
	requires org.hibernate.orm.core;
	requires org.postgresql.jdbc;
	requires ruu.lib.jdbc.core;
	requires ruu.lib.jdbc.postgres;
	requires ruu.lib.jpa.core;
	requires ruu.lib.jpa.se;
	requires ruu.lib.jpa.se.hibernate;
}
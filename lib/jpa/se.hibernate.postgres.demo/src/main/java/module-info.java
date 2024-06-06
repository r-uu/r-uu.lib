module ruu.lib.jpa.se.hibernate.postgres.demo
{
	exports de.ruu.lib.jpa.se.hibernate.postgres.demo;
	opens   de.ruu.lib.jpa.se.hibernate.postgres.demo;

	requires jakarta.annotation;
	requires jakarta.cdi;
	requires jakarta.inject;
	requires jakarta.interceptor;
	requires jakarta.persistence;
	requires lombok;
	requires org.slf4j;
	requires ruu.lib.cdi.se;
	requires ruu.lib.jpa.core;
	requires ruu.lib.jpa.se;
	requires ruu.lib.jpa.se.hibernate.postgres;
}
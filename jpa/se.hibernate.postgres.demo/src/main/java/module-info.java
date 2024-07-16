module de.ruu.lib.jpa.se.hibernate.postgres.demo
{
	exports de.ruu.lib.jpa.se.hibernate.postgres.demo;
	opens   de.ruu.lib.jpa.se.hibernate.postgres.demo;

	requires jakarta.annotation;
	requires jakarta.cdi;
	requires jakarta.inject;
	requires jakarta.interceptor;
	requires jakarta.persistence;
	requires org.slf4j;
	requires de.ruu.lib.cdi.se;
	requires de.ruu.lib.jpa.core;
	requires de.ruu.lib.jpa.se;
	requires de.ruu.lib.jpa.se.hibernate.postgres;
	requires de.ruu.lib.util;

	requires static lombok;
}
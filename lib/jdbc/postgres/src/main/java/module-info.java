module ruu.lib.jdbc.postgres
{
	exports de.ruu.lib.jdbc.postgres;

	requires java.naming;
	requires java.sql;
	requires lombok;
	requires org.postgresql.jdbc;
	requires ruu.lib.jdbc.core;
}
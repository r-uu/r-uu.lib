module ruu.lib.jpa.se
{
	exports de.ruu.lib.jpa.se;
	opens   de.ruu.lib.jpa.se;

	requires jakarta.annotation;
	requires jakarta.inject;
	requires jakarta.interceptor;
	requires jakarta.persistence;
	requires lombok;
//requires slf4j.api;
	requires org.slf4j;
}
module ruu.lib.cdi.se.demo
{
	exports de.ruu.lib.cdi.se.demo;
	exports de.ruu.lib.cdi.se.demo.parameters;
	
	opens de.ruu.lib.cdi.se.demo;
	opens de.ruu.lib.cdi.se.demo.parameters;

	requires jakarta.annotation;
	requires jakarta.cdi;
	requires jakarta.el;
	requires jakarta.inject;
	requires lombok;
//requires slf4j.api;
	requires org.slf4j;
	requires ruu.lib.cdi.se;
}
module de.ruu.lib.util.config.demo
{
	exports de.ruu.lib.util.config.demo;

	opens de.ruu.lib.util.config.demo;
//	opens de.ruu.lib.util.config.demo to
//			  weld.se.core                // for cdi-injection
//			, org.junit.platform.commons; // for junit-tests

	requires de.ruu.lib.util.config;

	requires jakarta.cdi;
	requires jakarta.el;
	requires microprofile.config.api;
	requires org.junit.jupiter.api;
	requires org.junit.jupiter.engine;
	requires weld.se.core;

	requires static lombok;
	requires org.slf4j;
	requires de.ruu.lib.util;
	requires org.hamcrest;
}
module de.ruu.lib.util
{
	exports de.ruu.lib.util;
	exports de.ruu.lib.util.annotation;
	exports de.ruu.lib.util.bimapped;
	exports de.ruu.lib.util.classpath;
	exports de.ruu.lib.util.json;
	exports de.ruu.lib.util.lang.model;
	exports de.ruu.lib.util.rs;
	exports de.ruu.lib.util.rs.filter.logging;

	requires java.compiler;
	requires jakarta.ws.rs;

	requires static lombok;
	requires org.slf4j;
}
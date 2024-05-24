module ruu.lib.util
{
	exports de.ruu.lib.util;
	exports de.ruu.lib.util.annotation;
	exports de.ruu.lib.util.classpath;
	exports de.ruu.lib.util.dto;
	exports de.ruu.lib.util.jsonb;
	exports de.ruu.lib.util.lang.model;
	exports de.ruu.lib.util.rs;
	exports de.ruu.lib.util.rs.filter.logging;

//	opens de.ruu.lib.util.annotation to org.junit.platform.commons;
	opens de.ruu.lib.util.jsonb to org.eclipse.yasson;

	requires lombok;
//requires slf4j.api;
	requires org.slf4j;
	requires java.compiler;
	requires jakarta.json;
	requires jakarta.json.bind;
	requires jakarta.ws.rs;
	requires org.apache.poi.poi;
}
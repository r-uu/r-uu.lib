module ruu.lib.junit
{
	exports de.ruu.lib.junit;

	requires jakarta.inject;
	requires lombok;
	requires org.junit.platform.commons;
	requires org.slf4j;
	requires microprofile.config.api;
	requires ruu.lib.util;

	requires transitive org.junit.jupiter.api;
}
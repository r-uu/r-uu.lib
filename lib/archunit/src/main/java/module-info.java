module ruu.lib.archunit
{
	exports de.ruu.lib.archunit;

	requires transitive com.tngtech.archunit;
	requires lombok;
//requires slf4j.api;
	requires org.slf4j;

	requires ruu.lib.util;
}
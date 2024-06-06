module ruu.lib.archunit
{
	exports de.ruu.lib.archunit;

	requires transitive com.tngtech.archunit;
	requires lombok;
	requires org.slf4j;

	requires ruu.lib.util;
}
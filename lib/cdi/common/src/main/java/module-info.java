module ruu.lib.cdi.common
{
	exports de.ruu.lib.cdi.common;
	opens   de.ruu.lib.cdi.common;

	requires jakarta.cdi;
	requires lombok;
//requires slf4j.api;
	requires org.slf4j;
}
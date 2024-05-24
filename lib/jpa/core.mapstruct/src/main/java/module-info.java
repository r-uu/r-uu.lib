module ruu.lib.jpa.core.mapstruct
{
	exports de.ruu.lib.jpa.core.mapstruct;

	requires lombok;
//requires slf4j.api;
	requires org.slf4j;
	requires org.mapstruct;
	requires ruu.lib.jpa.core;
	requires ruu.lib.mapstruct;
}
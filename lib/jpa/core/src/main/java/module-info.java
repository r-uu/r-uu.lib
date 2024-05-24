module ruu.lib.jpa.core
{
	exports de.ruu.lib.jpa.core;
	exports de.ruu.lib.jpa.core.criteria;
	exports de.ruu.lib.jpa.core.criteria.restriction;

	opens   de.ruu.lib.jpa.core;

	requires jakarta.persistence;
	requires java.sql;
	requires lombok;
//	requires ruu.lib.mapstruct;
	requires ruu.lib.util;
}
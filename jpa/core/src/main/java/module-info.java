module de.ruu.lib.jpa.core
{
	exports de.ruu.lib.jpa.core;
	exports de.ruu.lib.jpa.core.criteria;
	exports de.ruu.lib.jpa.core.criteria.restriction;

	opens   de.ruu.lib.jpa.core;

	requires de.ruu.lib.util;

	requires jakarta.annotation;
	requires jakarta.json.bind;
	requires jakarta.persistence;
	requires java.sql;

	requires static lombok;
}
module ruu.lib.gen.java
{
	exports de.ruu.lib.gen.java;
	exports de.ruu.lib.gen.java.bean;
	exports de.ruu.lib.gen.java.context;
	exports de.ruu.lib.gen.java.doc;
	exports de.ruu.lib.gen.java.element;
	exports de.ruu.lib.gen.java.element.field;
	exports de.ruu.lib.gen.java.element.method;
	exports de.ruu.lib.gen.java.element.pckg;
	exports de.ruu.lib.gen.java.element.type;
	exports de.ruu.lib.gen.java.naming;
	
	requires            com.tngtech.archunit;
	requires transitive java.compiler;
	requires            lombok;
	requires            org.slf4j;

	requires transitive ruu.lib.gen.core;
	requires            ruu.lib.util;
	requires transitive ruu.lib.archunit;
}
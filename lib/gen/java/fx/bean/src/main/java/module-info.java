module ruu.lib.gen.java.fx.bean
{
	exports de.ruu.lib.gen.java.fx.bean;

	requires transitive com.tngtech.archunit;
	requires transitive javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	requires lombok;
//requires slf4j.api;
	requires org.slf4j;

	requires transitive ruu.lib.gen.java;

	requires ruu.lib.gen.core;
	requires ruu.lib.util;
	requires ruu.lib.archunit;
}
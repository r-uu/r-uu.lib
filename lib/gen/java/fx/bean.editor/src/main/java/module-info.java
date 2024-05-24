module de.ruu.lib.gen.java.fx.bean.editor
{
	exports de.ruu.lib.gen.java.fx.bean.editor;

	requires transitive com.tngtech.archunit;

	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	requires lombok;
//requires slf4j.api;
	requires org.slf4j;

	requires transitive ruu.lib.gen.java;

	requires ruu.lib.archunit;
	requires ruu.lib.gen.core;
	requires ruu.lib.gen.java.fx.bean;
	requires ruu.lib.util;
}
module ruu.lib.gen.java.fx.comp
{
	exports de.ruu.lib.gen.java.fx.comp;
	exports de.ruu.lib.gen.java.fx.comp.demo;

	opens   de.ruu.lib.gen.java.fx.comp.demo;

	requires jakarta.cdi;
	requires jakarta.inject;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires lombok;
//requires slf4j.api;
	requires org.slf4j;

	requires ruu.lib.fx.comp;
	requires ruu.lib.gen.core;
	requires ruu.lib.gen.java;
	requires ruu.lib.util;
}
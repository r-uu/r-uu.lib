module ruu.lib.fx.demo
{
	exports de.ruu.lib.fx.demo;
	exports de.ruu.lib.fx.demo.bean;
	exports de.ruu.lib.fx.demo.comp.main;

	opens de.ruu.lib.fx.demo.bean;
	opens de.ruu.lib.fx.demo.comp.main;
	opens de.ruu.lib.fx.demo.gen;

	requires com.tngtech.archunit;
	requires javafx.controls;
	requires javafx.fxml;
	requires lombok;
	requires org.slf4j;

	requires transitive javafx.base;

	requires ruu.lib.fx.comp;
	requires ruu.lib.gen.core;
	requires ruu.lib.gen.java.fx.bean;
	requires ruu.lib.gen.java.fx.comp;
	requires ruu.lib.gen.java.fx.tableview;
}
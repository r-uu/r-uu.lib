module de.ruu.lib.fx.comp.demo.hierarchy
{
	exports de.ruu.lib.fx.comp.demo.empty;
	exports de.ruu.lib.fx.comp.demo.hierarchy;
	exports de.ruu.lib.fx.comp.demo.hierarchy.sub1;
	exports de.ruu.lib.fx.comp.demo.hierarchy.sub2;

	opens   de.ruu.lib.fx.comp.demo.empty;
	opens   de.ruu.lib.fx.comp.demo.hierarchy;
	opens   de.ruu.lib.fx.comp.demo.hierarchy.sub1;
	opens   de.ruu.lib.fx.comp.demo.hierarchy.sub2;

	requires jakarta.inject;

	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;

	requires static lombok;
	
	requires org.apache.logging.log4j;
	requires org.slf4j;

	requires ruu.lib.cdi.se;
	requires ruu.lib.fx.comp;
	requires ruu.lib.util;
	requires ruu.lib.fx.core;
}
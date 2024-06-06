module ruu.lib.fx.comp
{
	exports de.ruu.lib.fx.comp;

  opens   de.ruu.lib.fx.comp;

	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires jakarta.cdi;
	requires static lombok;
	requires org.slf4j;
	requires ruu.lib.cdi.common;
	requires ruu.lib.cdi.se;
	requires ruu.lib.fx.core;
	requires ruu.lib.util;
}
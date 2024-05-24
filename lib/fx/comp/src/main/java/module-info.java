module ruu.lib.fx.comp
{
	exports de.ruu.lib.fx.comp;

//	opens de.ruu.lib.fx.comp to javafx.fxml;
  opens   de.ruu.lib.fx.comp;

	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires jakarta.cdi;
	requires lombok;
//requires slf4j.api;
	requires org.slf4j;
	requires ruu.lib.cdi.common;
	requires ruu.lib.cdi.se;
	requires ruu.lib.fx.core;
	requires ruu.lib.util;
}
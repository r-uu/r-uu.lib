module ruu.lib.fx.core
{
	exports de.ruu.lib.fx;
	exports de.ruu.lib.fx.control;
	exports de.ruu.lib.fx.control.autocomplete;
	exports de.ruu.lib.fx.control.autocomplete.textfield;
	exports de.ruu.lib.fx.control.dialog;
	exports de.ruu.lib.fx.control.textfield.number;

	requires transitive javafx.controls;
	requires transitive javafx.fxml;
	requires lombok;
//requires slf4j.api;
	requires org.slf4j;

	requires ruu.lib.util;
}
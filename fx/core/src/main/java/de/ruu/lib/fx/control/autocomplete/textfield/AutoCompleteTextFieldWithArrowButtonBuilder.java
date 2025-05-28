package de.ruu.lib.fx.control.autocomplete.textfield;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

import static java.util.Objects.isNull;

public class AutoCompleteTextFieldWithArrowButtonBuilder<T> extends AutoCompleteTextFieldBuilder<T>
{
	public static <T> AutoCompleteTextFieldWithArrowButtonBuilder<T> create() { return new AutoCompleteTextFieldWithArrowButtonBuilder<>(); }

	public AutoCompleteTextFieldWithArrowButton<T> build()
	{
		return
				new AutoCompleteTextFieldWithArrowButton<>
				(
						items,
						suggestionFilter,
						comparator,
//						converterTest,
						graphicsProvider,
						textProvider,
						toolTipProvider,
						prompt
				);
	}
}
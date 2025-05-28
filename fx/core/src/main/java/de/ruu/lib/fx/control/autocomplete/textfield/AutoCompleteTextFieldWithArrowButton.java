package de.ruu.lib.fx.control.autocomplete.textfield;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import lombok.NonNull;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

import static de.ruu.lib.util.BooleanFunctions.not;

public class AutoCompleteTextFieldWithArrowButton<T> extends AutoCompleteTextField<T>
{
	private final Button arrowButton = new Button();

	public AutoCompleteTextFieldWithArrowButton
	(
			List                <T         > items,
			@NonNull BiPredicate<T, String > suggestionFilter,
			Comparator          <T         > comparator,
//			@NonNull BiPredicate<T, String > converterTest,
			Function            <T, Node   > graphicsProvider,
			Function            <T, String > textProvider,
			Function            <T, Tooltip> toolTipProvider,
			String                           promptText
	)
	{
		super(items, suggestionFilter, comparator/*, converterTest*/, graphicsProvider, textProvider, toolTipProvider, promptText);

		Polygon arrow = new Polygon(0, 0, 10, 0, 5, 7);
		arrow.setFill(Color.DIMGRAY);

		StackPane arrowPane = new StackPane(arrow);
		arrowPane.setPrefSize(15, 10);

		arrowButton.setGraphic(arrowPane);
		arrowButton.setFocusTraversable(false);
		arrowButton.setMinSize(30, 30);
		arrowButton.setMaxSize(30, 30);
		// make sure the button is not visible (and cannot be clicked) when the list of suggestions is empty
		arrowButton
				.visibleProperty()
				.bind(Bindings.createBooleanBinding(() -> not(listView.itemsProperty().get().isEmpty())));
		arrowButton.setOnAction(e -> onArrowButtonClicked(e));

		getChildren().add(arrowPane);
	}

	private void onArrowButtonClicked(ActionEvent e) { showPopup(); }
}
package de.ruu.lib.fx.control.autocomplete.textfield;

import de.ruu.lib.fx.control.autocomplete.Country;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import static de.ruu.lib.fx.control.autocomplete.BiPredicateCountryConverter.BIPREDICATE_COUNTRY_CONVERTER;
import static de.ruu.lib.fx.control.autocomplete.BiPredicateCountrySuggestion.BIPREDICATE_COUNTRY_SUGGESTION;
import static de.ruu.lib.fx.control.autocomplete.FunctionCountryGraphicsProvider.FUNCTION_COUNTRY_GRAPHICS_PROVIDER;
import static de.ruu.lib.fx.control.autocomplete.FunctionCountryTextProvider.FUNCTION_COUNTRY_TEXT_PROVIDER;
import static de.ruu.lib.fx.control.autocomplete.FunctionCountryToolTipProvider.FUNCTION_TOOL_TIP_PROVIDER;
import static java.util.Objects.isNull;

public class AutoCompleteTextFieldCountryDemo extends Application
{
	@Override	public void start(Stage stage)
	{
		AutoCompleteTextField<Country> textField =
				AutoCompleteTextFieldBuilder.<Country>create()
						.items           (Country.countries())
						.suggestionFilter(BIPREDICATE_COUNTRY_SUGGESTION)
						.converterTest   (BIPREDICATE_COUNTRY_CONVERTER)
						.graphicsProvider(FUNCTION_COUNTRY_GRAPHICS_PROVIDER)
						.textProvider    (FUNCTION_COUNTRY_TEXT_PROVIDER)
						.toolTipProvider (FUNCTION_TOOL_TIP_PROVIDER)
						.prompt          ("select country")
						.build();

		Button button = new Button("show text field value");
		button.setPrefWidth(300);

		Label label = new Label();
		label.setPrefHeight(25);
		label.prefWidthProperty().bind(textField.widthProperty());

		HBox root = new HBox();
		root.getChildren().add(textField);
		root.getChildren().add(button);
		root.getChildren().add(label);
		root.setSpacing(10);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

//		HBox.setHgrow(scene, Priority.ALWAYS);
		HBox.setHgrow(root, Priority.ALWAYS);

		textField.setMaxWidth(Double.MAX_VALUE);
		textField.setMinWidth(textField.getWidth());
		textField.setPrefWidth(textField.getWidth());
//		textField.valueProperty().addListener
//		(
//				(obs, old, newValue) ->
//				{
//					if (newValue == null) log.debug("new value: null");
//					else log.debug("new value: " + newValue.name());
//				}
//		);

		button.setOnAction
		(
				e -> buttonOnAction(textField, label)
		);

		// focus anything outside the combo box
		button.requestFocus();
		// focus the editor of the combo box
//		textField.requestFocus();
//		// focus the combo box
//		comboBox.requestFocus();

//		Robot robot = new Robot();
//		robot.keyPress(KeyCode.G);
//		robot.keyPress(KeyCode.E);
//		robot.keyPress(KeyCode.R);
	}

	private void buttonOnAction(AutoCompleteTextField<Country> textField, Label label)
	{
		Country country = textField.valueProperty().get();
		if (isNull(country))
		{
			label.setText("null");
			return;
		}
		else
		{
			label.setText(country.getName());
		}
	}
}
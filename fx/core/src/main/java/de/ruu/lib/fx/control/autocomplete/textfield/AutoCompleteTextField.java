package de.ruu.lib.fx.control.autocomplete.textfield;

import de.ruu.lib.fx.control.ClearableTextField;
import de.ruu.lib.fx.control.autocomplete.AutoCompleteCellFactory;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.stage.Popup;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;

import static de.ruu.lib.util.BooleanFunctions.not;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
public class AutoCompleteTextField<T> extends ClearableTextField
{
	private final ListView<T> listView = new ListView<>();
	private final Popup       popup    = new Popup();

	/** stores the current value of an instance at all time, accessible for clients via {@link #valueProperty()} */
	private final ObjectProperty<T> value = new SimpleObjectProperty<>();

	private final ObservableList<T         > items;
	private final BiPredicate   <T, String > suggestionFilter;
	private final BiPredicate   <T, String > converterTest;
	private final Function      <T, Node   > graphicsProvider;
	private final Function      <T, String > textProvider;
	private final Function      <T, Tooltip> toolTipProvider;

	/**
	 * @param suggestionFilter returns <code>true</code> if an item is a valid suggestion for the value of the combo box,
	 *        <code>false</code> otherwise
	 * @param converterTest returns <code>true</code> if an item corresponds to the text, <code>false</code> otherwise
	 * @param graphicsProvider
	 * @param textProvider
	 * @param toolTipProvider
	 * @param promptText
	 */
	public AutoCompleteTextField(
			List                <T         > items,
			@NonNull BiPredicate<T, String > suggestionFilter,
			@NonNull BiPredicate<T, String > converterTest,
			Function            <T, Node   > graphicsProvider,
			Function            <T, String > textProvider,
			Function            <T, Tooltip> toolTipProvider,
			String                           promptText)
	{
		if (isNull(items           )) items            = new ArrayList<>();
		if (isNull(graphicsProvider)) graphicsProvider = item -> null;
		if (isNull(textProvider    )) textProvider     = item -> isNull(item) ? "" : item.toString();
		if (isNull(toolTipProvider )) toolTipProvider  = item -> null;
		if (isNull(promptText      )) promptText       = "";

		this.items            = FXCollections.observableArrayList(items);
		this.suggestionFilter = suggestionFilter;
		this.converterTest    = converterTest;
		this.graphicsProvider = graphicsProvider;
		this.textProvider     = textProvider;
		this.toolTipProvider  = toolTipProvider;

		value.addListener((obs, old, cur) -> onValueChanged(cur));

		textField().setPromptText(promptText);

		setupPopup();
		setupListeners();
	}

	public void value(final T value)
	{
		if      (value == null)         this.value.set(value);
		else if (items.contains(value)) this.value.set(value);
		else                            log.warn("value not contained in items, did not set value to " + value);
	}
	public ObjectProperty<T> valueProperty() { return value; }

	public void items(List<T> items) { this.items.setAll(items); }

	public T selectedItem() { return listView.getSelectionModel().getSelectedItem(); }

//	private void onValueChanged(final T newValue) { textField().setText(converter.toString(newValue)); }
	private void onValueChanged(final T newValue) { textField().setText(textProvider.apply(newValue)); }

	private void setupPopup()
	{
		listView.setMaxHeight(150);
		listView.setPrefWidth(200);
		listView.setFocusTraversable(false);
		listView.setCellFactory(new AutoCompleteCellFactory<>(graphicsProvider, textProvider, toolTipProvider));

		popup.setAutoHide(true);
		popup.setAutoFix(true);
		popup.setHideOnEscape(true);
		popup.getContent().add(listView);
	}

	private void setupListeners()
	{
		textField().textProperty().addListener((obs, old, act) -> textFieldListener(act));

		textField().setOnKeyPressed
		(
				event ->
				{
					if (popup.isShowing())
					{
						if (event.getCode() == KeyCode.DOWN)
						{
							listView.requestFocus();
							listView.getSelectionModel().selectFirst();
							event.consume();
						}
					}
				}
		);

		listView.setOnKeyPressed
		(
				event ->
				{
					if (event.getCode() == KeyCode.ENTER)
					{
						applySelection();
					}
					else if (event.getCode() == KeyCode.ESCAPE)
					{
						popup.hide();
					}
				}
		);

		listView.setOnMouseClicked
		(
				event ->
				{
					if (event.getClickCount() == 1)
					{
						applySelection();
					}
				}
		);

		focusedProperty().addListener((obs, wasFocused, isNowFocused) -> { if (not(isNowFocused)) popup.hide(); } );
	}

	private void textFieldListener(String act)
	{
		if (isNull(act) || act.isEmpty())
		{
			popup.hide();
			value(null);
			return;
		}

		if (not(popup.isShowing()))
		{
			populatePopup(); // there is some text in the text field
			showPopup();
//			popup.show(textField(), textField().getLayoutX(), textField().getLayoutY() + textField().getHeight());
		}
		else
			popup.hide();
	}

	private Set<T> populatePopup()
	{
		Set<T> filteredItems = new HashSet<>();

		for (T item : items)
		{
			if (suggestionFilter.test(item, textField().getText()))
					filteredItems.add(item);
		}

		listView.getItems().setAll(filteredItems);

		return filteredItems;
	}

	private void applySelection()
	{
		T selected = listView.getSelectionModel().getSelectedItem();
		if (nonNull(selected))
		{
			String value = textProvider.apply(selected);
			textField().setText(value);
			textField().positionCaret(value.length());
			popup.hide();
			value(selected);
		}
	}

	private void showPopup()
	{
		// Preselect first item
		Platform.runLater
		(
				() ->
				{
					listView.getSelectionModel().selectFirst();
					listView.scrollTo(0);
					listView.requestFocus();
				}
		);
		Bounds bounds = textField().localToScreen(textField().getBoundsInLocal());
		popup.show(textField(), bounds.getMinX(), bounds.getMaxY());
	}
}
package de.ruu.lib.fx.control.autocomplete.textfield.v2;

import de.ruu.lib.fx.control.ClearableTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.util.List;
import java.util.stream.Collectors;

public class AutoCompleteTextField extends StackPane
{
    private final ClearableTextField textField        = new ClearableTextField();
    private final ContextMenu        suggestionsPopup = new ContextMenu();

    private final ObservableList<String> vornamen = FXCollections.observableArrayList();

    public AutoCompleteTextField()
    {
        getChildren().add(textField);
        // ensure textField and suggestionsPopup resize with the control
        textField       .prefWidthProperty().bind(widthProperty());
        suggestionsPopup.prefWidthProperty().bind(widthProperty());

        setupListeners();
    }

    public void setPersonen(List<Person> personen)
    {
        vornamen.setAll(
                personen.stream()
                        .map(Person::getVorname)
                        .distinct()
                        .sorted()
                        .collect(Collectors.toList())
        );
    }

    public String    getText          ()              { return textField.getText();             }
    public void      setPromptText    (String prompt) {        textField.setPromptText(prompt); }
    public TextField getInnerTextField()              { return textField.getTextField();        }

    private void setupListeners()
    {
        getInnerTextField().textProperty().addListener((obs, oldText, newText) ->
        {
            if (newText == null || newText.isEmpty())
            {
                suggestionsPopup.hide();
            }
            else
            {
                List<String> filtered = vornamen.stream()
                        .filter(name -> name.toLowerCase().startsWith(newText.toLowerCase()))
                        .collect(Collectors.toList());
                if (!filtered.isEmpty()) {
                    populatePopup(filtered);
                    if (!suggestionsPopup.isShowing()) {
                        suggestionsPopup.show(textField,
                                javafx.geometry.Side.BOTTOM, 0, 0);
                    }
                } else {
                    suggestionsPopup.hide();
                }
            }
        });

        textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                suggestionsPopup.hide();
            }
        });
    }

    private void populatePopup(List<String> suggestions) {
        List<CustomMenuItem> menuItems = suggestions.stream().map(suggestion -> {
            Label entryLabel = new Label(suggestion);
            CustomMenuItem item = new CustomMenuItem(entryLabel, true);
            item.setOnAction(e -> {
                textField.setText(suggestion);
                suggestionsPopup.hide();
            });
            return item;
        }).collect(Collectors.toList());

        suggestionsPopup.getItems().setAll(menuItems);
    }
}
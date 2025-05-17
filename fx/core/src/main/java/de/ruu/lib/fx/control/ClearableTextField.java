package de.ruu.lib.fx.control;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ClearableTextField extends StackPane
{
    private final TextField textField   = new TextField();
    private final Button    clearButton = new Button("✕");

    public ClearableTextField() { setup(); }

    private void setup()
    {
        // Button-Eigenschaften
        clearButton.setFocusTraversable(false);
//        clearButton.setStyle("-fx-background-color: transparent; -fx-text-fill: gray; -fx-font-weight: bold;");
        clearButton.setBackground(Background.EMPTY);
        clearButton.setTextFill(Color.GRAY);
        clearButton.setFont(Font.font(null, FontWeight.BOLD, Font.getDefault().getSize()));
        clearButton.setOnAction(e -> textField.clear());
        clearButton.visibleProperty().bind(Bindings.isNotEmpty(textField.textProperty()));

        // Layout
        getChildren().addAll(textField, clearButton);
        StackPane.setAlignment(clearButton, Pos.CENTER_RIGHT);
        StackPane.setMargin(clearButton, new Insets(0, 5, 0, 0));
    }

    public TextField textField()           { return textField;                       }
    public String    text()                { return textField.getText();             }
    public void      text  (String text)   {        textField.setText(text);         }
    public void      prompt(String prompt) {        textField.setPromptText(prompt); }
}
package de.ruu.lib.fx.control.autocomplete.textfield;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ClearableTextFieldApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        TextField textField = new TextField();
        textField.setPromptText("Text hier eingeben...");

        Button clearButton = new Button("✕");
        clearButton.setFocusTraversable(false);
        clearButton.setOnAction(e -> textField.clear());

        // Optional: Styling für besseren Look
        clearButton.setStyle("-fx-background-color: transparent; -fx-text-fill: gray; -fx-font-weight: bold;");

        // Sichtbarkeit des Buttons nur, wenn Text vorhanden ist
        clearButton.visibleProperty().bind(textField.textProperty().isNotEmpty());

        StackPane stack = new StackPane();
        stack.getChildren().addAll(textField, clearButton);
        StackPane.setAlignment(clearButton, Pos.CENTER_RIGHT);
        StackPane.setMargin(clearButton, new javafx.geometry.Insets(0, 5, 0, 0));

        HBox root = new HBox(stack);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        root.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(root, 300, 100);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Textfeld mit X-Button");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
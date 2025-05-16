package de.ruu.lib.fx.control.autocomplete.textfield.v2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class AutoCompleteTextFieldDemo extends Application
{
    @Override public void start(Stage primaryStage)
    {
        List<Person> personen = List.of(
                new Person("Anna", "Müller"),
                new Person("Andreas", "Schmidt"),
                new Person("Anja", "Meier"),
                new Person("Ben", "Schulz"),
                new Person("Bernd", "Koch")
        );

        AutoCompleteTextField autoField = new AutoCompleteTextField();
        autoField.setPersonen(personen);
        autoField.setPromptText("Vorname eingeben...");

        VBox root = new VBox(10, autoField);
        root.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(root, 350, 120);
        primaryStage.setScene(scene);
        primaryStage.setTitle("AutoCompleteTextField Demo");
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
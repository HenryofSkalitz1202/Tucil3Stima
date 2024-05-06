package com.henryofskalitz;

import java.util.Set;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class WordLadderSolverGUI extends Application {

    private TextField startWordField;
    private TextField endWordField;
    private ComboBox<String> algorithmComboBox;
    private TextArea resultTextArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Word Ladder Solver");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label startLabel = new Label("Start Word:");
        GridPane.setConstraints(startLabel, 0, 0);

        startWordField = new TextField();
        GridPane.setConstraints(startWordField, 1, 0);

        Label endLabel = new Label("End Word:");
        GridPane.setConstraints(endLabel, 0, 1);

        endWordField = new TextField();
        GridPane.setConstraints(endWordField, 1, 1);

        Label algorithmLabel = new Label("Algorithm:");
        GridPane.setConstraints(algorithmLabel, 0, 2);

        algorithmComboBox = new ComboBox<>();
        algorithmComboBox.getItems().addAll("Breadth First Search", "Depth First Search", "A* Search");
        algorithmComboBox.setValue("Breadth First Search"); // Default selection
        GridPane.setConstraints(algorithmComboBox, 1, 2);

        Button solveButton = new Button("Solve");
        GridPane.setConstraints(solveButton, 1, 3);
        solveButton.setOnAction(e -> solve());

        resultTextArea = new TextArea();
        resultTextArea.setEditable(false);
        GridPane.setConstraints(resultTextArea, 0, 4, 2, 1);

        gridPane.getChildren().addAll(startLabel, startWordField, endLabel, endWordField, algorithmLabel, algorithmComboBox, solveButton, resultTextArea);

        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void solve() {
        String startWord = startWordField.getText();
        String endWord = endWordField.getText();
        String selectedAlgorithm = algorithmComboBox.getValue();

        int length = startWord.length();
        
        // Handle the result as needed, such as displaying it to the user
        String result = "Result will be shown here using " + selectedAlgorithm + "...";
        resultTextArea.setText(result);
    }
}



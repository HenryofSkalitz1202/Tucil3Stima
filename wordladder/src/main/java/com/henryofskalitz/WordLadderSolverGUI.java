package com.henryofskalitz;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordLadderSolverGUI extends Application {

    private TextField startWordField;
    private TextField endWordField;
    private ComboBox<String> algorithmComboBox;
    private TextArea resultTextArea;

    private Set<String> wordList;
    File selectedFile;

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
        algorithmComboBox.getItems().addAll("Uniform Cost Search (UCS)", "Greedy Best First Search (GBFS)", "A* Search");
        algorithmComboBox.setValue("Uniform Cost Search (UCS)"); // Default selection
        GridPane.setConstraints(algorithmComboBox, 1, 2);

        Button uploadButton = new Button("Upload Dictionary");
        GridPane.setConstraints(uploadButton, 0, 3);
        uploadButton.setOnAction(e -> uploadDictionary());

        Button solveButton = new Button("Solve");
        GridPane.setConstraints(solveButton, 1, 3);
        solveButton.setOnAction(e -> solve());

        resultTextArea = new TextArea();
        resultTextArea.setEditable(false);
        GridPane.setConstraints(resultTextArea, 0, 4, 2, 1);

        gridPane.getChildren().addAll(startLabel, startWordField, endLabel, endWordField, algorithmLabel, algorithmComboBox, uploadButton, solveButton, resultTextArea);

        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private File uploadDictionary() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload Dictionary File");
        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            return selectedFile;
        }else{
            return null;
        }
    }

    public static Set<String> findWordsWithLength(File fileName, int length) {
        Set<String> words = new HashSet<>();
        
        if(fileName == null){
            return null;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] wordList = line.split("\\s+");
                for (String word : wordList) {
                    if (word.length() == length) {
                        words.add(word);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        
        return words;
    }

    private void solve(){
        String startWord = startWordField.getText();
        String goalWord = endWordField.getText();
        String selectedAlgorithm = algorithmComboBox.getValue();

        wordList = findWordsWithLength(selectedFile, startWord.length());

        if (wordList == null || wordList.isEmpty()) {
            resultTextArea.setText("Please upload a dictionary first.");
            return;
        }

        // Print the words found
        if (wordList.isEmpty()){
            resultTextArea.setText("No words found with length " + startWord.length());
            return;
        }
        
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long startTime = System.nanoTime();
        long startMemory = runtime.totalMemory() - runtime.freeMemory();
        SearchResult result = null;
        if(selectedAlgorithm == "Uniform Cost Search (UCS)"){
            result = UniformCostSearch.findWithUCS(startWord, goalWord, wordList);
        }else if(selectedAlgorithm == "Greedy Best First Search (GBFS)"){
            result = GreedyBestFirstSearch.findWithGBFS(startWord, goalWord, wordList);
        }else if(selectedAlgorithm == "A* Search"){
            result = AStarSearch.findWithAStar(startWord, goalWord, wordList);
        }
        long endTime = System.nanoTime();
        long afterMemory = runtime.totalMemory() - runtime.freeMemory();

        long elapsedTimeInNanoseconds = endTime - startTime;
        double elapsedTimeInMilliseconds = (double) elapsedTimeInNanoseconds / 1_000_000;
        long usedMemory = afterMemory - startMemory;
        

        // Extract the found path and visited words length from the result
        List<String> foundPath = result.getFoundPath();
        int visitedWordsLength = result.getVisitedWordsLength();

        String resultString = "Using " + selectedAlgorithm + "...\n";
        if(foundPath == null){
            resultString += "No path found!";
        }else{
            // Handle the result as needed, such as displaying it to the user
            resultString += "\nPath length: " + foundPath.size() + "\n";
            resultString += String.join(" -> ", foundPath);
            resultString += "\nVisited nodes: " + visitedWordsLength;
            resultString += "\nExecution time: " + elapsedTimeInMilliseconds + " ms";
            resultString += "\nUsed Memory: " + usedMemory / 8 + " bytes";
        }
        
        resultTextArea.setText(resultString);
    }
}
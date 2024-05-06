package com.henryofskalitz;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user to enter the filename
        System.out.print("Enter the filename: ");
        String fileName = scanner.nextLine();

        // Prompt the user to enter the length
        System.out.print("Enter the length of words you want to find: ");
        int length = scanner.nextInt();

        // Consume the newline character
        scanner.nextLine();

        System.out.print("Start Word: ");
        String startWord = scanner.nextLine();

        System.out.print("Goal Word: ");
        String goalWord = scanner.nextLine();
        
        // Find words with the specified length
        Set<String> wordsWithLength = WordLengthFinder.findWordsWithLength(fileName, length);

        // Print the words found
        if (wordsWithLength.isEmpty()) {
            System.out.println("No words found with length " + length);
        }
        
        long startTime = System.nanoTime();
        SearchResult result = UniformCostSearch.findWithUCS(startWord, goalWord, wordsWithLength);
        long endTime = System.nanoTime();
        long elapsedTimeInNanoseconds = endTime - startTime;
        double elapsedTimeInMilliseconds = (double) elapsedTimeInNanoseconds / 1_000_000;

        // Extract the found path and visited words length from the result
        List<String> foundPath = result.getFoundPath();
        int visitedWordsLength = result.getVisitedWordsLength();

        if (foundPath != null) {
            System.out.println("Found path:");
            System.out.println(String.join(" -> ", foundPath));
            System.out.printf("Visited nodes: %d\n", visitedWordsLength);
        } else {
            System.out.println("No ladder found.");
            System.out.printf("Visited nodes: %d\n", visitedWordsLength);
        }

        System.out.println("Execution time: " + elapsedTimeInMilliseconds + "ms");

        // boolean end = false;
        // while(!end){

        //     System.out.print("Continue?(Yes/No) ");
        //     String status = scanner.nextLine();
        //     if(status == "No"){
        //         end = true;
        //     }
        // }
        
        // Close the scanner
        scanner.close();
    }
}
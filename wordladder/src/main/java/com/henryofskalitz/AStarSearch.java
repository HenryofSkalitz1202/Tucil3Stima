package com.henryofskalitz;
import java.util.*;

public class AStarSearch {

    private static int calculateCost(String word, String goalWord) {
        // Here, we can use the countLetterDifference method as the cost function
        return countLetterDifference(word, goalWord);
    }

    public static SearchResult findWithAStar(String startWord, String goalWord, Set<String> dictionary) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        Map<String, Integer> costs = new HashMap<>();
        List<String> foundPath = new ArrayList<>();
        Set<String> visitedWords = new HashSet<>();

        // Initialize the start node with cost 0 and heuristic value
        priorityQueue.add(new Node(startWord, 0, calculateCost(startWord, goalWord), null));
        costs.put(startWord, 0);

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();
            String currentWord = currentNode.getWord();

            if (currentWord.equals(goalWord)) {
                // Construct the found path
                Node node = currentNode;
                while (node != null) {
                    foundPath.add(0, node.getWord()); // Add word at the beginning to maintain order
                    node = node.getParent();
                }
                return new SearchResult(foundPath, visitedWords.size());
            }

            // Add current word to visited words
            visitedWords.add(currentWord);

            // Generate neighboring words
            List<String> neighbors = generateWords(currentWord, dictionary);
            for (String word : neighbors) {
                int newCost = costs.get(currentWord) + 1;
                if (!costs.containsKey(word) || newCost < costs.get(word)) {
                    int heuristic = calculateCost(word, goalWord);
                    Node newNode = new Node(word, newCost, heuristic, currentNode);
                    priorityQueue.add(newNode);
                    costs.put(word, newCost);
                }
            }
        }

        // If no path is found, return an empty list
        return new SearchResult(null, visitedWords.size());
    }

    private static List<String> generateWords(String word, Set<String> wordSet) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        List<String> words = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            for (char letter : alphabet.toCharArray()) {
                String newWord = word.substring(0, i) + letter + word.substring(i + 1);
                if (!newWord.equals(word) && wordSet.contains(newWord)) {
                    words.add(newWord);
                }
            }
        }
        return words;
    }

    private static int countLetterDifference(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                count++;
            }
        }

        return count;
    }

    private static class Node implements Comparable<Node> {
        private String word;
        private int cost;
        private int heuristic;
        private Node parent;

        public Node(String word, int cost, int heuristic, Node parent) {
            this.word = word;
            this.cost = cost;
            this.heuristic = heuristic;
            this.parent = parent;
        }

        public String getWord() {
            return word;
        }

        // public int getCost() {
        //     return cost;
        // }

        // public int getHeuristic() {
        //     return heuristic;
        // }

        public Node getParent() {
            return parent;
        }

        @Override
        public int compareTo(Node other) {
            int f1 = cost + heuristic;
            int f2 = other.cost + other.heuristic;
            return Integer.compare(f1, f2);
        }
    }
}

package com.henryofskalitz;
import java.util.*;

public class UniformCostSearch {

    // Function to generate all possible words by changing one letter
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

    // Function to perform uniform cost search
    public static SearchResult findWithUCS(String startWord, String endWord, Set<String> wordSet) {
        Set<String> visitedWords = new HashSet<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        List<String> foundPath = new ArrayList<>();
        queue.offer(new Node(startWord, 0, null));

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            String currentWord = currentNode.word;

            if (currentWord.equals(endWord)) {
                // Construct the found path
                Node node = currentNode;
                while (node != null) {
                    foundPath.add(0, node.getWord()); // Add word at the beginning to maintain order
                    node = node.getParent();
                }
                
                return new SearchResult(foundPath, visitedWords.size());
            }

            if (!visitedWords.contains(currentWord)) {
                visitedWords.add(currentWord);
                List<String> neighbors = generateWords(currentWord, wordSet);
                for (String neighbor : neighbors) {
                    if (!visitedWords.contains(neighbor)) {
                        queue.offer(new Node(neighbor, currentNode.cost + 1, currentNode));
                    }
                }
            }
        }

        return new SearchResult(null, visitedWords.size());
    }

    // Node class to represent word with its cost and parent
    private static class Node {
        String word;
        int cost;
        Node parent;

        Node(String word, int cost, Node parent) {
            this.word = word;
            this.cost = cost;
            this.parent = parent;
        }

        // Getter and setter methods for word and cost
        public String getWord() {
            return word;
        }

        public int getCost() {
            return cost;
        }

        public Node getParent() {
            return parent;
        }
    }
}

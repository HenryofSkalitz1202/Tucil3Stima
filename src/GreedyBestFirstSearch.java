import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class GreedyBestFirstSearch {
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

    private static int countLetterDifference(String word1, String word2){
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

    public static List<String> findWithGBFS(String startWord, String goalWord, Set<String> dictionary) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        List<String> foundPath = new ArrayList<>();
        Set<String> visitedWords = new HashSet<>();

        int diff = countLetterDifference(startWord, goalWord);
        priorityQueue.add(new Node(startWord, diff, null));

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
                return foundPath;
            }

            // Add current word to visited words
            visitedWords.add(currentWord);

            // Generate neighboring words
            List<String> neighbors = generateWords(currentWord, dictionary);
            for (String word : neighbors) {
                diff = countLetterDifference(word, goalWord);
                if (!visitedWords.contains(word)) {
                    Node newNode = new Node(word, diff, currentNode);
                    priorityQueue.add(newNode);
                }
            }
        }

        // If no path is found, return an empty list
        return foundPath;
    }

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
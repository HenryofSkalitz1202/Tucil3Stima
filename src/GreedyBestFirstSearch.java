import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class GreedyBestFirstSearch {
    private static boolean hasOneLetterDifference(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        int count = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                count++;
            }
            if (count > 1) {
                return false;
            }
        }

        return count == 1;
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
            System.out.println(currentNode.getWord() + " " + currentNode.getCost());

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

            // Expand current node
            for (String word : dictionary) {
                if (hasOneLetterDifference(word, currentWord) && !visitedWords.contains(word)){
                    diff = countLetterDifference(word, goalWord);
                    Node newNode = new Node(word, diff, currentNode);
                    priorityQueue.add(newNode);
                }
            }
        }

        // If no path is found, return an empty list
        return foundPath;
    }
}
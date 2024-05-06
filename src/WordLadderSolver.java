import java.util.*;

public class WordLadderSolver {

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
    public static List<String> findLadder(String startWord, String endWord, Set<String> wordSet) {
        if (startWord.equals(endWord)) {
            List<String> ladder = new ArrayList<>();
            ladder.add(startWord);
            return ladder;
        }

        Set<String> visited = new HashSet<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        queue.offer(new Node(startWord, 0, null));

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            String currentWord = currentNode.word;

            if (currentWord.equals(endWord)) {
                List<String> ladder = new ArrayList<>();
                while (currentNode != null) {
                    ladder.add(0, currentNode.word);
                    currentNode = currentNode.parent;
                }
                return ladder;
            }

            if (!visited.contains(currentWord)) {
                visited.add(currentWord);
                List<String> neighbors = generateWords(currentWord, wordSet);
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        queue.offer(new Node(neighbor, currentNode.cost + 1, currentNode));
                    }
                }
            }
        }

        return null;
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
    }

    // Example usage
    public static void main(String[] args){
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
        } else {
            System.out.println("Words with length " + length + ":");
        }
        
        List<String> ladder = findLadder(startWord, goalWord, wordsWithLength);
        if (ladder != null) {
            System.out.println(String.join(" -> ", ladder));
        } else {
            System.out.println("No ladder found.");
        }

        scanner.close();
    }
}

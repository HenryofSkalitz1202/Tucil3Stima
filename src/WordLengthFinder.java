import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class WordLengthFinder {
    public static Set<String> findWordsWithLength(String fileName, int length) {
        Set<String> words = new HashSet<>();
        
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
}

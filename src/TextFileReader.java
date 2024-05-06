import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TextFileReader {
    public static String readFile(String fileName) {
        StringBuilder content = new StringBuilder();
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        
        return content.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user to enter the filename
        System.out.print("Enter the filename: ");
        String fileName = scanner.nextLine();
        
        // Read the content of the file
        String fileContent = readFile(fileName);
        
        // Print the content of the file
        System.out.println("File Content:");
        System.out.println(fileContent);
        
        // Close the scanner
        scanner.close();
    }
}

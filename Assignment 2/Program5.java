import java.io.*;
import java.util.*;

// ──────────────────────────────────────────────────────────────
// Q5: Count Word Occurrences from a File using File Handling APIs
// ──────────────────────────────────────────────────────────────

public class Q5_WordCount {

    // Creates a sample text file to work with
    static void createSampleFile(String path) throws IOException {
        String content = """
                Java is a programming language.
                Java is platform independent.
                Java supports multithreading and Java is object oriented.
                File handling in Java is done using Java IO API.
                """;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(content);
        }
        System.out.println("Sample file created: " + path);
    }

    // Reads file and counts word occurrences (case-insensitive)
    static Map<String, Integer> countWords(String path) throws IOException {
        Map<String, Integer> wordCount = new TreeMap<>();   // sorted output

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split on any non-letter character
                String[] words = line.toLowerCase().split("[^a-zA-Z]+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordCount.merge(word, 1, Integer::sum);
                    }
                }
            }
        }
        return wordCount;
    }

    public static void main(String[] args) {
        String filePath = "sample_words.txt";

        try {
            // Step 1 – create a demo file
            createSampleFile(filePath);

            // Step 2 – count words
            Map<String, Integer> wordCount = countWords(filePath);

            // Step 3 – display results
            System.out.println("\nWord Occurrence Count:");
            System.out.println("-".repeat(30));
            System.out.printf("%-20s %s%n", "Word", "Count");
            System.out.println("-".repeat(30));
            wordCount.forEach((word, count) ->
                    System.out.printf("%-20s %d%n", word, count));

            // Step 4 – find the most frequent word
            Optional<Map.Entry<String, Integer>> maxEntry =
                    wordCount.entrySet().stream()
                             .max(Map.Entry.comparingByValue());
            maxEntry.ifPresent(e ->
                    System.out.println("\nMost frequent word: \""
                            + e.getKey() + "\" (" + e.getValue() + " times)"));

        } catch (IOException e) {
            System.err.println("File error: " + e.getMessage());
        }
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WordCounter {

    private static final String[] COMMON_WORDS = {"the", "and", "of", "to", "in", "a", "is", "that", "it", "was"};

    public static void main(String[] args) {
        String text = readInputText();
        if (text == null) {
            System.out.println("No input provided. Exiting...");
            return;
        }

        String[] words = text.split("[\\s\\p{Punct}]+");
        int totalWords = words.length;
        int uniqueWords = countUniqueWords(words);
        int ignoredWords = countIgnoredWords(words);

        System.out.println("Total words: " + totalWords);
        System.out.println("Unique words: " + uniqueWords);
        System.out.println("Ignored common words: " + ignoredWords);
    }

    private static String readInputText() {
        try {
            // Prompt the user to enter text or provide a file
            System.out.println("Enter a text or provide the path to a file:");
            BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
            String input = reader.readLine().trim();

            if (input.isEmpty()) {
                return null; // Empty input
            } else if (input.endsWith(".txt")) {
                return readFromFile(input); // Read text from file
            } else {
                return input; // Read text from direct input
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String readFromFile(String filePath) {
        StringBuilder textBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                textBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textBuilder.toString();
    }

    private static int countUniqueWords(String[] words) {
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            wordFrequency.put(word.toLowerCase(), wordFrequency.getOrDefault(word.toLowerCase(), 0) + 1);
        }
        return wordFrequency.size();
    }

    private static int countIgnoredWords(String[] words) {
        int ignoredCount = 0;
        for (String word : words) {
            if (isCommonWord(word.toLowerCase())) {
                ignoredCount++;
            }
        }
        return ignoredCount;
    }

    private static boolean isCommonWord(String word) {
        for (String commonWord : COMMON_WORDS) {
            if (word.equals(commonWord)) {
                return true;
            }
        }
        return false;
    }
}

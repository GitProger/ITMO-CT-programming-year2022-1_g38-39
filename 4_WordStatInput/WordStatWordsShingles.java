import java.util.TreeMap; //
//import java.util.Set;
//import java.util.TreeSet;
import java.util.Scanner;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Comparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class WordStatWordsShingles {
    private static final int SHINGLE_LENGTH = 3;

    private static boolean isWordCharacter(char c) {
        return Character.isAlphabetic(c) || c == '\'' || Character.DASH_PUNCTUATION == Character.getType(c);
    }

    private static void particularUpdate(final Map<String, Integer> dictionary, String key) {
        dictionary.put(key, dictionary.getOrDefault(key, 0) + 1);
    }

    private static void updateShingles(final Map<String, Integer> dictionary, final String key) {
        if (key.length() <= SHINGLE_LENGTH) {
            particularUpdate(dictionary, key);
            return;
        }
        for (int i = SHINGLE_LENGTH; i <= key.length(); i++) {
            particularUpdate(dictionary, key.substring(i - SHINGLE_LENGTH, i));
        }
    }

    private static void consider(final Map<String, Integer> dictionary, final String arg) {
        int st = 0, cur = 0;
        for (int i = 0; i < arg.length(); i++) {
            boolean good = isWordCharacter(arg.charAt(i));
            if (good) {
                cur++;
            } else if (cur == 0) {
                st++;
            }
            if ((!good || i == arg.length() - 1) && cur != 0) {
                updateShingles(dictionary, arg.substring(st, st + cur));
                cur = 0;
                st = i + 1;
            }
        }
    }

    public static void main(String[] argv) {
        if (argv.length != 2) {
            System.err.println("Expected 2 arguments [input file, output file]");
            System.exit(1);
        }
        try {
            Map<String, Integer> dictionary = new TreeMap<>(); 

            var input = new File(argv[0]);
            PrintStream output = new PrintStream(argv[1]);

            Scanner scan = new Scanner(input);
            while (scan.hasNextLine()) {
                consider(dictionary, scan.nextLine().toLowerCase());
            }

            for (Map.Entry<String, Integer> entry : dictionary.entrySet()) {
                output.println(entry.getKey() + " " + entry.getValue());
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            System.exit(2);
        } catch (Exception e) {
            System.err.println("Unhandled error: " + e.getMessage());
            System.exit(3);
        }
    }
}

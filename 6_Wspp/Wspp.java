import java.util.Scanner;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Wspp {
    private static int occurrences = 0;

    private static boolean isWordCharacter(char c) {
        return Character.isAlphabetic(c) || c == '\'' || Character.DASH_PUNCTUATION == Character.getType(c);
    }

    private static void update(final Map<String, IntList> dictionary, String key) {
        var arr = dictionary.getOrDefault(key, new IntList());
        arr.add(occurrences);
        dictionary.put(key, arr);
    }

    private static void consider(final Map<String, IntList> dictionary, final String arg) {
        int st = 0, cur = 0;
        for (int i = 0; i < arg.length(); i++) {
            boolean good = isWordCharacter(arg.charAt(i));
            if (good) {
                cur++;
            } else if (cur == 0) {
                st++;
            }
            if ((!good || i == arg.length() - 1) && cur != 0) {
                occurrences++;
                update(dictionary, arg.substring(st, st + cur));
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
            occurrences = 0;
            Map<String, IntList> dictionary = new LinkedHashMap<>();

            var input = new File(argv[0]);
            PrintStream out = new PrintStream(argv[1]);

            Scanner scan = new Scanner(input);
            while (scan.hasNextLine()) {
                consider(dictionary, scan.nextLine().toLowerCase());
            }

            for (Map.Entry<String, IntList> entry : dictionary.entrySet()) {
                IntList arr = entry.getValue();
                out.print(entry.getKey() + " " + arr.size() + " ");
                for (int i = 0; i < arr.size(); i++) {
                    out.print(arr.get(i) + (i == arr.size() - 1 ? "" : " "));
                }
                out.println();
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

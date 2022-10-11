import java.util.Scanner;
import java.util.Map;
import java.util.Map.Entry;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class WsppCountLastL {
    private static int occurrences = 0;
    private static int localOccur = 0;

    private static boolean isWordCharacter(char c) {
        return Character.isAlphabetic(c) || c == '\'' || Character.DASH_PUNCTUATION == Character.getType(c);
    }

    private static void update(final Map<String, IntList> dictionary,
                               final Map<String, ArrayList<IntList>> lastOc, final String key, int toPut) {
        var arr = dictionary.getOrDefault(key, new IntList());
        arr.add(toPut);
        dictionary.put(key, arr);
    }

    private static void consider(final Map<String, IntList> dictionary, 
                                 final Map<String, IntList> lastOc, final String arg) {
        int st = 0, cur = 0;
        localOccur = 0;

        for (int i = 0; i < arg.length(); i++) {
            boolean good = isWordCharacter(arg.charAt(i));
            if (good) {
                cur++;
            } else if (cur == 0) {
                st++;
            }
            if ((!good || i == arg.length() - 1) && cur != 0) {
                occurrences++;
                localOccur++;
                update(dictionary, lastOc, arg.substring(st, st + cur), occurrences);
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
            Map<String, IntList> dictionary = new LinkedHashMap<>();
            Map<String, ArrayList<IntList>> lastOc = new LinkedHashMap<>();

            var input = new File(argv[0]);
            PrintStream out = new PrintStream(argv[1]);
//            var input = System.in;
//            var out = System.out;

            Scanner scan = new Scanner(input);
            while (scan.hasNextLine()) {
                consider(dictionary, lastOc, scan.nextLine().toLowerCase());
            }
            
            List<Map.Entry<String, IntList>> list = new ArrayList<>(dictionary.entrySet());
            list.sort(Comparator.comparing(e -> e.getValue().size()));

            for (Map.Entry<String, IntList> entry : list) {
                String key = entry.getKey();
                IntList arr = entry.getValue();
                out.print(key + " " + arr.size() + " ");

                for (int i = 0; i < lastOc.get(key).size(); i++) {
                    out.print(lastOc.get(key).get(i) + (i == lastOc.get(key).size() - 1 ? "" : " "));
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


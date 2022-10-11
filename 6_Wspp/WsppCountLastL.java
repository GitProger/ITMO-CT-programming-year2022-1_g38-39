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
    private static int curStr = 0;
    private static int curOccur = 0;

    private static boolean isWordCharacter(char c) {
        return Character.isAlphabetic(c) || c == '\'' || Character.DASH_PUNCTUATION == Character.getType(c);
    }

    private static void update(final Map<String, IntList> dictionary, 
                               final Map<String, IntList> lastOc, final String key) {
        occurrences++;
        curOccur++;

        var arr = dictionary.getOrDefault(key, new IntList());
        arr.add(occurrences);
        dictionary.put(key, arr);

        var oc = lastOc.getOrDefault(key, new IntList());
        oc.resize(curStr + 1);
        oc.set(curStr, curOccur);
        lastOc.put(key, oc);
    }

    private static void consider(final Map<String, IntList> dictionary, 
                                 final Map<String, IntList> lastOc, final String arg) {
        int st = 0, cur = 0;
        curOccur = 0;

        for (int i = 0; i < arg.length(); i++) {
            boolean good = isWordCharacter(arg.charAt(i));
            if (good) {
                cur++;
            } else if (cur == 0) {
                st++;
            }
            if ((!good || i == arg.length() - 1) && cur != 0) {
                update(dictionary, lastOc, arg.substring(st, st + cur));
                cur = 0;
                st = i + 1;
            }
        }
        curStr++;
    }

    public static void main(String[] argv) {
        if (argv.length != 2) {
            System.err.println("Expected 2 arguments [input file, output file]");
            System.exit(1);
        }
        try {
            Map<String, IntList> dictionary = new LinkedHashMap<>();
            Map<String, IntList> lastOc = new LinkedHashMap<>();

            var input = new File(argv[0]);
            PrintStream out = new PrintStream(argv[1]);
            //var input = System.in;
            //var out = System.out;

            Scanner scan = new Scanner(input);
            while (scan.hasNextLine()) {
                consider(dictionary, lastOc, scan.nextLine().toLowerCase());
            }
            
            List<Map.Entry<String, IntList>> list = new ArrayList<>(dictionary.entrySet());
            list.sort(Comparator.comparing(e -> e.getValue().size()));

            for (Map.Entry<String, IntList> entry : list) {
                String key = entry.getKey();
                out.print(key + " " + entry.getValue().size() + " ");
                IntList oc = lastOc.get(key);
                IntList ans = new IntList();

                for (int i = 0; i < oc.size(); i++) {
                    if (oc.get(i) != 0) {
                        ans.add(oc.get(i));
                    }
                }
                for (int i = 0; i < ans.size(); i++) {
                    if (ans.get(i) != 0) {
                        out.print((i == 0 ? "" : " ") + ans.get(i));
                    }
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

/*
To be, or not to be, that is the question:

Monday's child is fair of face.
Tuesday's child is full of grace.
*/


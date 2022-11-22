import java.util.Scanner;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

class SolutionCountLastL {
    private int occurrences = 0;
    private int curStr = 0;
    private int curOccur = 0;
    private Map<String, IntList> dictionary = new LinkedHashMap<>();
    private Map<String, IntList> lastOc = new HashMap<>(); // NOTE одной мапы достаточно

    private boolean isWordCharacter(char c) {
        return Character.isAlphabetic(c) || c == '\'' || Character.DASH_PUNCTUATION == Character.getType(c);
    }

    private void update(final String key) {
        occurrences++;
        curOccur++;

        var arr = dictionary.getOrDefault(key, new IntList());
        arr.add(occurrences);
        dictionary.put(key, arr); // номера вхождений слова

        var oc = lastOc.getOrDefault(key, new IntList());
        oc.resize(curStr + 1);
        oc.set(curStr, curOccur);
        lastOc.put(key, oc);
    }

    private void consider(final String arg) {
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
                update(arg.substring(st, st + cur));
                cur = 0;
                st = i + 1;
            }
        }
        curStr++;
    }

    public void main(File input, PrintStream out) throws FileNotFoundException {
        Scanner scan = new Scanner(input);
        while (scan.hasNextLine()) {
            consider(scan.nextLine().toLowerCase());
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
    }
}


public class WsppCountLastL {
    public static void main(String[] argv) {
        if (argv.length != 2) {
            System.err.println("Expected 2 arguments [input file, output file]");
            System.exit(1);
        }
        try {
            var input = new File(argv[0]);
            PrintStream out = new PrintStream(argv[1]);

            var solve = new SolutionCountLastL();
            solve.main(input, out);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            System.exit(2);
        } catch (Exception e) {
            System.err.println("Unhandled error: " + e.getMessage());
            System.exit(3);
        }
    }
}
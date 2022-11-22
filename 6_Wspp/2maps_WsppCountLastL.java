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
    private int curStr = 0;
    private int curOccur = 0;
    private Map<String, Integer> tokenCount = new LinkedHashMap<>(); // сколько всего таких слов
    private Map<String, IntList> lastTokenOccurence = new HashMap<>(); // последнее вхождение токена для каждой строки

    private boolean isWordCharacter(char c) {
        return Character.isAlphabetic(c) || c == '\'' || Character.DASH_PUNCTUATION == Character.getType(c);
    }

    private void processToken(final String key) {
        tokenCount.put(key, tokenCount.getOrDefault(key, 0) + 1);

        curOccur++; // последнее вхождение слова key в строке curStr
        var oc = lastTokenOccurence.getOrDefault(key, new IntList());
        oc.resize(curStr + 1);
        oc.set(curStr, curOccur);
        lastTokenOccurence.put(key, oc);
    }

    private void processLine(final String arg) {
        int curTokenSt = 0, curTokenLen = 0;
        curOccur = 0;

        for (int i = 0; i < arg.length(); i++) {
            boolean good = isWordCharacter(arg.charAt(i));
            if (good) {
                curTokenLen++;
            } else if (curTokenLen == 0) {
                curTokenSt++;
            }
            if ((!good || i == arg.length() - 1) && curTokenLen != 0) {
                processToken(arg.substring(curTokenSt, curTokenSt + curTokenLen));
                curTokenLen = 0;
                curTokenSt = i + 1;
            }
        }
        curStr++;
    }

    public void main(File input, PrintStream out) throws FileNotFoundException {
        Scanner scan = new Scanner(input);
        while (scan.hasNextLine()) {
            processLine(scan.nextLine().toLowerCase());
        }
        
        List<Map.Entry<String, Integer>> list = new ArrayList<>(tokenCount.entrySet());
        list.sort(Comparator.comparing(e -> e.getValue()));

        for (Map.Entry<String, Integer> entry : list) {
            String key = entry.getKey();

            out.print(key + " " + entry.getValue() + " ");
            IntList oc = lastTokenOccurence.get(key);
            IntList ans = new IntList();

            for (int i = 0; i < oc.size(); i++) {
                if (oc.get(i) != 0) { // если индекс вхождение токен в этой строке 0, то этого токена в ней не было
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
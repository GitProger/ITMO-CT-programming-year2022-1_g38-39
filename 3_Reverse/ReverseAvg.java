import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class ReverseAvg {
    private static void handle(ArrayList<long[]> table, 
                               ArrayList<Long> verSums, 
                               ArrayList<Long> verSize, 
                               ArrayList<Long> sums, 
                               String line) {
        long[] array = {};
        int arrSize = 0;
        Scanner scan = new Scanner(line);
        long curSum = 0L;
        for (int i = 0; scan.hasNextLong(); i++, arrSize++) {
            if (i >= array.length) {
                array = Arrays.copyOf(array, (int)((array.length + 1) * 1.5));
            }
            array[i] = scan.nextLong();
            curSum += array[i];
            if (i >= verSize.size()) {
                verSize.add(0L);
                verSums.add(0L);
            }
            verSize.set(i, verSize.get(i) + 1);
            verSums.set(i, verSums.get(i) + array[i]);;
        }
        table.add(Arrays.copyOf(array, arrSize));
        sums.add(curSum);
    }

    public static void main(String[] argv) {
        ArrayList<long[]> table = new ArrayList<>();
        ArrayList<Long> verSums = new ArrayList<>();
        ArrayList<Long> verSize = new ArrayList<>();
        ArrayList<Long> sums = new ArrayList<>();

        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            handle(table, verSums, verSize, sums, scan.nextLine());
        }

        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < table.get(i).length; j++) {
                long up = (long)(sums.get(i) + verSums.get(j) - table.get(i)[j]); // double
                var val = up / (table.get(i).length + verSize.get(j) - 1);
                System.out.print(val + (j != table.get(i).length - 1 ? " " : ""));
            }
            System.out.println();
        }
   }
}


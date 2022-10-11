//import java.util.Scanner;
import java.util.Arrays;

public class ReverseOctAbc {
    public static void main(String[] argv) {
        int tableSize = 0;
        int[][] table = new int[0][0];


        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            Scanner scan = new Scanner(line);
            int arrSize = 0;
            int[] array = {};
            for (;;) {
                if (!scan.hasNextInt()) {
                    break;
                }
                arrSize++;
                if (arrSize - 1 >= array.length) {
                    array = Arrays.copyOf(array, (int)((array.length + 1) * 1.5));
                }
                array[arrSize - 1] = scan.nextInt();
            }
            array = Arrays.copyOf(array, arrSize);

            tableSize++;
            if (tableSize - 1 >= table.length) {
                table = Arrays.copyOf(table, (int)((table.length + 1) * 1.5));
            }
            table[tableSize - 1] = array;
        }
        table = Arrays.copyOf(table, tableSize);

        for (int i = table.length - 1; i >= 0; i--) {
            for (int j = table[i].length - 1; j >= 0; j--) {
                System.out.print(table[i][j] + (j != 0 ? " " : ""));
            }
            System.out.println();
        }
   }
}

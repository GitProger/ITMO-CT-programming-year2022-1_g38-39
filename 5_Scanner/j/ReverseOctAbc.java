//import java.util.Scanner;
import java.util.Arrays;

public class ReverseOctAbc {
    public static void main(String[] argv) {
        int tableSize = 0;
        int[][] table = new int[0][0];


        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            Scanner scan = new Scanner(in.nextLine());
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

/*long:

    Running test 33: java ReverseOctAbc [4 input lines]
1    37777777776o    3

-4    -5
6
----------
6
-5 -4

3 4294967294 1
Exception in thread "main" java.lang.AssertionError: Line 4:
     expected `3 -2 1`,
       actual `3 4294967294 1`

int:error

jshell> Integer.toOctalString(-2)
$1 ==> "37777777776"

ParseUnsignedInt
*/
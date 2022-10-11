import java.util.Scanner;
import java.util.ArrayList;

public class ReverseArrayList {
    private static ArrayList<int[]> table = new ArrayList<>();

    private static int count(String line) {
        Scanner scan = new Scanner(line);
        int result = 0;
        while (scan.hasNextInt()) {
            scan.nextInt();
            result++;
        }
        return result;
    }

    private static void handle(String line) {
        int[] array = new int[count(line)];
        Scanner scan = new Scanner(line);
        for (int i = 0; i < array.length; i++) {
            array[i] = scan.nextInt();
        }
        table.add(array);
    }

    public static void main(String[] argv) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            handle(scan.nextLine());
        }

        for (int i = table.size() - 1; i >= 0; i--) {
            for (int j = table.get(i).length - 1; j >= 0; j--) {
                System.out.print(table.get(i)[j] + (j != 0 ? " " : ""));
            }
            System.out.println();
        }
        table.clear();
   }
}


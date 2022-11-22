//import java.util.Scanner;
import java.util.Arrays;

public class Test {
    private static void test1() {
        Scanner scan = new Scanner(" \t 1\n 2 \t\t 3   4 ");
        System.out.println(scan.nextInt() + scan.nextInt() + scan.nextInt() + scan.nextInt());
    }
    private static void test2() {
        Scanner scan = new Scanner("\n\rhi\r\nHello\r\n"); // System.in
        System.out.println(":"+scan.hasNextLine());
        System.out.println("Line1[" + scan.nextLine() + "]");
        System.out.println(":"+scan.hasNextLine());
        System.out.println("Line2[" + scan.nextLine() + "]");
        System.out.println(":"+scan.hasNextLine());
        System.out.println("Line3[" + scan.nextLine() + "]");
    }
    private static void test3() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            if (scan.hasNext()) {
                String a = scan.next();
                System.out.println(">"+a);
            }
        }
    }
 
    private static void test(String line) {
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
        System.out.print("--> [");
        array = Arrays.copyOf(array, arrSize);
        for (int i = 0; i < array.length; i++)
            System.out.print(array[i] + (i == array.length - 1 ? "" : " "));
        System.out.println("]");
    }

    public static void main(String[] argv) {
        test2();
  //      test("\n\n");
   //     Scanner scan = new Scanner(System.in);
    //    while (scan.hasNextLine()) {
     //       test(scan.nextLine());
      //  }
    }
}

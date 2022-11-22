import java.util.*;
import java.io.*;

public class A {
    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        int a, b, n;
        a = in.nextInt(); 
        b = in.nextInt();
        n = in.nextInt();
        System.out.println(1 + 2 * ((n - a - 1) / (b - a)));
    }
}
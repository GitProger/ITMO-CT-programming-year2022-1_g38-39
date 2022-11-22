import java.util.*;
import java.io.*;

public class B {
    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); 
        int start = -710 * 25000;
        for (int i = 0; i < n; i++) {
            System.out.println(start + i * 710);
        }   
    }
}

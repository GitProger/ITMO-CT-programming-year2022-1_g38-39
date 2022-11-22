import java.util.*;
import java.io.*;

public class M {
    public static void solve(Scanner in) {
        // Scanner in = new Scanner(System.in); // err
        int n = in.nextInt();
        int ans = 0;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        Map<Integer, Integer> cntKj = new HashMap<>();
        for (int j = n - 2; j > 0; j--) {
            cntKj.put(a[j + 1], cntKj.getOrDefault(a[j + 1], 0) + 1);
            for (int i = 0; i < j; i++) {
                ans += cntKj.getOrDefault(2 * a[j] - a[i], 0);
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            solve(in);
        }
    }
}

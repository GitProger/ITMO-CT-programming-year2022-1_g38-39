import java.util.*;
import java.io.*;

public class D {
    static class MyScanner {
        final BufferedReader reader;
        StringTokenizer tokenizer = new StringTokenizer("");

        MyScanner(InputStream is) { reader = new BufferedReader(new InputStreamReader(is)); }
        int nextInt() { 
            return Integer.parseInt(next());
        }
        String next() {
            while (!tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new AssertionError();
                }
            }
            return tokenizer.nextToken();
        }
    }
    static class IntList extends ArrayList<Integer> {
        public IntList() { super(); }
    }

    static class Solution {
        private MyScanner in;
        Solution(MyScanner in) { this.in = in; }

        private int n;
        private int k;
        private final long MOD = 998244353L;

        public void solve() {
            n = in.nextInt();
            k = in.nextInt();

            long[] pw = new long[n + 1];
            pw[0] = 1L;
            for (int i = 1; i <= n; i++) {
                pw[i] = (pw[i - 1] * k) % MOD;
            }

            long[] doublePal = new long[n + 1];
            long[] ans = new long[n + 1];
            long answer = 0;


            for (int i = 1; i <= n; i++) {
                if (i % 2 == 1) {
                    ans[i] += i * pw[(i + 1) / 2]; // число сдвигов * длина палиндрома
                } else {
                    ans[i] += (i / 2) * pw[i / 2] + (i / 2) * pw[i / 2 + 1]; // тут еще центральный символ
                }
                ans[i] %= MOD;
                
                doublePal[i] += ans[i];
                for (int j = 2; i * j <= n; j++) {
                    ans[i * j] -= (doublePal[i] * (j - 1)) % MOD;
                    doublePal[i * j] -= doublePal[i];
                }

                answer = (answer + ans[i]) % MOD;
            }
            System.out.println(answer);
        }
    }

    public static void main(String[] argv) {
        new Solution(new MyScanner(System.in)).solve();
    }
}

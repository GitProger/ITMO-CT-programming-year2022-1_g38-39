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
            long[] pal = new long[n + 1];
            long[] doublePal = new long[n + 1];
            long ans = 0;
            pw[0] = pal[0] = 1L;

            for (int curLen = 1; curLen <= n; curLen++) {
                pw[curLen] = (pw[curLen - 1] * k) % MOD;
                pal[curLen] = pw[(curLen + 1) / 2];

                long curCnt = 0;
                for (int i = 0; i < curLen; i++) {
                    curCnt += (pal[i] * pal[curLen - i]) % MOD;
                }
                for (int i = 1; i <= curLen; i++) {
                    if (curLen % i == 0) {
                        curCnt -= (doublePal[i] * (curLen / i - 1)) % MOD;
                    }
                }

                doublePal[curLen] = curCnt;
                for (int i = 1; i <= curLen / 2; i++) {
                    if (curLen % i == 0) {
                        doublePal[curLen] -= doublePal[i];
                    }
                }
                ans = (ans + curCnt) % MOD;
            }
            System.out.println(ans);
        }
    }

    public static void main(String[] argv) {
        new Solution(new MyScanner(System.in)).solve();
    }
}

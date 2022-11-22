import java.util.*;
import java.io.*;

public class H {
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

    public static void main(String[] argv) {
        MyScanner in = new MyScanner(System.in);

        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) { a[i] = in.nextInt(); }
        int q = in.nextInt();

        int[] sum = new int[n + 1];
        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, a[i - 1]);
            sum[i] = sum[i - 1] + a[i - 1];
        }

        int[] mapping = new int[sum[n]];
        for (int i = 0; i < n; i++) {
            for (int j = sum[i]; j < sum[i + 1]; j++) {
                mapping[j] = i;
            }
        }

        int[] cache = new int[sum[n] + 1];

        for (int qid = 0; qid < q; qid++) {
            int t = in.nextInt();
            t = Math.min(t, sum[n]);
            if (t < max) {
                System.out.println("Impossible");
                continue;
            }
            if (cache[t] != 0) {
                System.out.println(cache[t]);
                continue;
            }

            int last = 0, ans = 1;
            while (t + last < mapping.length) {
                last = sum[mapping[t + last]];
                ans++;
            }
            cache[t] = ans;
            System.out.println(ans);
        }
    }
}

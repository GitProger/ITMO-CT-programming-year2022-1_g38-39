import java.util.*;
import java.io.*;

public class E {
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

    static class Solution {
        class IntList extends ArrayList<Integer> {
            public IntList() { super(); }
        }


        private MyScanner in;
        Solution(MyScanner in) { this.in = in; }
        private int n;
        private int m;
        private IntList[] graph;
        private IntList teams;

        private void dfs(int v, int p, int[] depth, int[] prev) {
            prev[v] = p;
            for (Integer u : graph[v]) {
                if (u == p) { continue; }
                depth[u] = depth[v] + 1;
                dfs(u, v, depth, prev);
            }
        }

        public void solve() {
            n = in.nextInt();
            m = in.nextInt();
            graph = new IntList[n];
            teams = new IntList();
            for (int i = 0; i < n; i++) {
                graph[i] = new IntList();
            }
            for (int i = 1; i < n; i++) {
                int v = in.nextInt() - 1;
                int u = in.nextInt() - 1;
                graph[v].add(u);
                graph[u].add(v);
            }
            for (int i = 0; i < m; i++) {
                teams.add(in.nextInt() - 1);
            }


            int[] depth = new int[n];
            int[] prev = new int[n];
            dfs(teams.get(0), -1, depth, prev);

            int maxTeam = teams.get(0);
            for (Integer i : teams) {
                if (depth[maxTeam] < depth[i]) {
                    maxTeam = i;
                }
            }
            
            int v = maxTeam;
            for (int i = 0; i < depth[maxTeam] / 2; i++) {
                v = prev[v];
            }

            depth[v] = 0;
            dfs(v, -1, depth, prev);

            for (Integer team : teams) {
                if (depth[team] != depth[teams.get(0)]) {
                    System.out.println("NO");
                    return;
                }
            }

            System.out.println("YES");
            System.out.println(v + 1);
        }
    }

    public static void main(String[] argv) {
        new Solution(new MyScanner(System.in)).solve();
    }
}

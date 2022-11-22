import java.util.*;
import java.io.*;

public class J {
    public static int mod10(int a) {
        return (10 + a % 10) % 10;
    }
    public static void main(String[] argv) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ArrayList<ArrayList<Integer>> cnt = new ArrayList<>(n);
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            String s = in.next(); // nextLine
            cnt.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                cnt.get(i).add(s.charAt(j) - '0');
            }
            ans.add(new ArrayList<Integer>(Collections.nCopies(n, 0)));
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (cnt.get(i).get(j) == 1) {
                    ans.get(i).set(j, 1);
                    cnt.get(i).set(j, mod10(cnt.get(i).get(j) - 1));
                    for (int k = j + 1; k < n; k++) {
                        cnt.get(i).set(k, mod10(cnt.get(i).get(k) - cnt.get(j).get(k)));
                        // cnt[i][k] -= cnt[j][k]; % 10
                    }
                }
            }
        }


        for (ArrayList<Integer> line : ans) {
            for (Integer e : line) {
                System.out.print(e);
            }
            System.out.println();
        }

    }
}

/*
Начнем восстанавливать граф с вершины i. Если число путей i j равно нулю, то ребра нет, иначе
ребро есть, при этом, i j должно быть равно единице. Если мы удалим это ребро, то число путей
i k уменьшится на число путей j k. Переберем k от j+1 до n и вычтем число путей j k из числа
путей i k (все вычисления производятся по модулю 10)
*/
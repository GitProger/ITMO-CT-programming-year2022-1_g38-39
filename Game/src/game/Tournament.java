package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tournament {
    private final List<Player> players = new ArrayList<>();
    private final Map<Integer, Integer> table = new HashMap<>();
    private final MNKBoard map;
    private final boolean log;
    Tournament(boolean log, MNKBoard map, List<Player> players) {
        this.log = log;
        this.map = new MNKBoard(map);
        this.players.addAll(players);
        for (int i = 1; i <= players.size(); i++) {
            table.put(i, 0);
        }
    }
    private void add(int pId, int pts) {
        table.put(pId, table.get(pId) + pts);
    }

    private void log(String t) {
        if (log) {
            System.out.println(t);
        }
    }

    private void push(int i, int j) {
        if (i == j) return;
        log(TerminalColors.YELLOW + "!!! Player #" + i + " (X) vs Player #" + j + " (O) !!!" + TerminalColors.RESET);
        var game = new Game(false, List.of(players.get(i - 1), players.get(j - 1)));
        int res = game.play(new MNKBoard(map));

        if (res == 0) {
            add(i, 1);
            add(j, 1);
        } else if (res <= 2) {
            add(i, 3);
        } else {
            add(j, 3);
        }
    }

    public void contest() { // http://chess.sainfo.ru/table.html
        int n = players.size();
        var played = new boolean[n + 1][n + 1];
        int tourCnt = n % 2 == 1 ? n : n - 1;
        for (int t = 1; t <= 2 * tourCnt; t++) { // `2 *` because i, j  and   j, i
            int rest = 0;
            if (n % 2 == 1) {
                rest = n - t % tourCnt;
            }
            String line = new String(new char[15]).replace("\0", "=");
            System.out.println(TerminalColors.RED + " " + line + " ROUND #" + t + " " + line + " " + TerminalColors.RESET);
            System.out.println(TerminalColors.PURPLE + "Free player: " + rest + TerminalColors.RESET);
            var used = new boolean[n + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j || i == rest || j == rest) continue;
                    if (played[i][j]) continue;
                    if (used[i] || used[j]) continue;
                    played[i][j] = true;
                    used[i] = used[j] = true;
                    push(i, j);
                }
            }
            System.out.println(TerminalColors.BLUE + this + TerminalColors.RESET);
        }

        /*for (int i = 1; i <= players.size(); i++) {
            for (int j = 1; j <= players.size(); j++) {
                push(i, j);
            }
        }*/
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        var border = "+------+------+";
        sb.append(border);
        sb.append("\n|Player|Points|\n");
        sb.append(border);
        for (int i = 1; i <= players.size(); i++) {
            sb.append("\n").append(String.format("| %4d | %4d |", i, table.get(i)));
            sb.append("\n").append(border);
        }
        return sb.toString();
    }
}
/*
+---+---+---+
| X | O | - |
+---+---+---+
| 6 | 1 | 1 |
+---+---+---+
 */
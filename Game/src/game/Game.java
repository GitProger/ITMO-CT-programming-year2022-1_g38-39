package game;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final boolean log;
    private final int playerCnt;
    private final List<Player> players = new ArrayList<>();

    public Game(final boolean log, final List<Player> players) {
        this.log = log;
        playerCnt = players.size();
        this.players.addAll(players);
    }

    private void display(String s) {
        System.out.println(s);
    }

    public int play(Board board) {
        while (true) {
            for (int i = 0; i < players.size(); i++) {
                final int result = move(board, players.get(i), i + 1);
                if (result != -1) {
                    return result;
                }
            }
        }
    }

    private int move(final Board board, final Player player, final int no) {
        final Move move = player.move(board.getPosition(), board.getCell());
        final Result result = board.makeMove(move);
        log("Player " + no + " move: " + move);
        log("Position:\n" + board);
        if (result != Result.UNKNOWN) {
            display(TerminalColors.GREEN + board + TerminalColors.RESET);
        }
        if (result == Result.WIN) {
            display("Player " + no + " won");
            return no;
        } else if (result == Result.LOSE) {
            display("Player " + no + " lost");
            return playerCnt + no;
        } else if (result == Result.DRAW) {
            display("Draw");
            return 0;
        } else {
            return -1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}

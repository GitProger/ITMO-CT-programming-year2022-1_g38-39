package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class GameMenu {
    private Scanner in;
    private int getSetting(String message, int min, int max) {
        System.out.print(message);
        while (true) {
            System.out.print(" -> ");
            if (in.hasNext() && !in.hasNextInt()) {
                in.next();
                System.out.println("Bad input");
                continue;
            }
            try {
                int r = in.nextInt();
                if ((min != -1 && r < min) || (max != -1 && r > max)) {
                    System.out.println("Bad option, minimum value is " + min + (max != -1 ? " maximum value is " + max : ""));
                    continue;
                }
                return r;
            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public void main() {
        in = new Scanner(System.in);
        int mode = getSetting("""
                Choose mode:
                 * Basic game (1)
                 * Tournament (2)
                 * Exit game (3)
                """, 1, 3
        );
        if (mode == 3) return;

        int m = getSetting("Enter the number of rows", 3, -1);
        int n = getSetting("Enter the number of columns", 3, -1);
        int k = getSetting("Enter the winning length", 3, Math.min(m, n));
        int pCnt;
        if (mode == 1) {
            pCnt = getSetting("Enter the number of players (minimum 2, maximum 4)", 2, 4);
        } else {
            pCnt = getSetting("Enter the number of players (minimum 2)", 2, -1);
        }
        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= pCnt; i++) {
            int playerType = getSetting("Enter the type of player #" + i + "; Human(1), Sequential(2), Random(3) ", 1, 3);
            switch (playerType) {
                case 1 -> players.add(new HumanPlayer());
                case 2 -> players.add(new SequentialPlayer());
                case 3 -> players.add(new RandomPlayer());
                default -> {}
            }
        }

        if (mode == 1) {
            final Game game = new Game(false, players);
            game.play(new MNKBoard(m, n, k, pCnt));
        } else {
            System.out.println("If the number of players is odd then in each round one of them has a rest.");
            Tournament tour = new Tournament(true, new MNKBoard(m, n, k, 2), players);
            tour.contest();
        }
    }
}

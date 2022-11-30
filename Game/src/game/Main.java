package game;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static MNKBoard diagBoard() {
        List<ForbiddenMove> bad = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            bad.add(new ForbiddenMove(i, i));
            bad.add(new ForbiddenMove(i, 11 - 1 - i));
        }
        return new MNKBoard(11, 11, 5, bad);
    }
    private static void oldMain() {
        final Game game = new Game(true, List.of(new RandomPlayer(), new RandomPlayer()));
        int result;
        do {
            result = game.play(new MNKBoard(3, 3, 3));
            System.out.println("Game result: " + result);
        } while (result != 0);
    }
    private static void multiPlayer() {
        final Game game = new Game(false, List.of(new HumanPlayer(), new HumanPlayer(), new HumanPlayer()));
        game.play(new MNKBoard(3, 3, 3, 3));
    }

    private static void tourTest() {
        Tournament tour = new Tournament(true, new MNKBoard(3, 3, 3), List.of(
                new SequentialPlayer(),
                new SequentialPlayer()
        ));
        tour.contest();
        System.out.println(TerminalColors.BLUE + tour + TerminalColors.RESET);

      //  var players = List.of(new SequentialPlayer(), new SequentialPlayer());
      //  var game = new Game(false, List.of(players.get(1 - 1), players.get(2 - 1)));
      //  int res = game.play(new MNKBoard(new MNKBoard(3, 3, 3)));
       // System.out.println(res);
    }


    private static void game() {
        final Game game = new Game(false, List.of(new HumanPlayer(), new SequentialPlayer()));
        game.play(new MNKBoard(3, 3, 3));
    }

    public static void main(String[] args) {
        new GameMenu().main();
    }
}

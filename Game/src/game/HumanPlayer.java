package game;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    private int getCoordinate() {
       while (true) {
           try {
               if (!in.hasNext()) continue;
               if (in.hasNextInt()) {
                   return in.nextInt();
               } else {
                   in.next();
                   out.println("Bad number");
               }
           } catch (RuntimeException e) {
               out.println("Error: " + e.getMessage());
           }
       }
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position:");
            out.println(position);
            out.println(cell + "'s move");
            out.print("Enter row and column -> ");
            int r = getCoordinate();
            int c = getCoordinate();
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                return move;
            }

            final int row = move.getRow();
            final int column = move.getColumn();
            out.println("Move " + row + " " + column + " is invalid");
        }
    }
}

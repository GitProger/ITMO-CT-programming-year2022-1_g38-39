package game;

public class IntelligentPlayer implements Player {
    private Move ans;
    int analyze(MNKBoard board, final Cell cell, final Cell me) {
        System.out.println(TerminalColors.BLUE + board + TerminalColors.RESET + "\nCurrent: " + cell + " Empty: " + board.getEmpty() + "\n");
        for (int r = 0; r < board.getRowCnt(); r++) {
            for (int c = 0; c < board.getColCnt(); c++) {
                final Move move = new Move(r, c, cell);
                if (!board.isValid(move)) continue;
                var board2 = new MNKBoard(board);
                if (board2.getEmpty() == 1) {
                    if (board2.checkWin(move)) {
                        if (cell == me) {
                            ans = move;
                            return 1;
                        } else {
                            return -1;
                        }
                    } else {
                        return 0;
                    }
                }

                board2.makeMove(move);
                int res = -analyze(board2, board2.getCell(), me);
                if (res == 1) {
                    ans = move;
                    return res;
                }
            }
        }

        for (int r = 0; r < board.getRowCnt(); r++) {
            if (ans != null) break;
            for (int c = 0; c < board.getColCnt(); c++) {
                final Move move = new Move(r, c, cell);
                if (board.isValid(move)) {
                    ans = move;
                    break;
                }
            }
        }
        return 0;
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        ans = null;
        MNKBoard board = (MNKBoard)position;
        analyze(board, cell, cell);
        return ans;
    }
}

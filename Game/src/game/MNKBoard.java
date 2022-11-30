package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MNKBoard implements Board, Position {
    private final Cell[][] cells;
    private Cell turn;
    private final int rowCnt;
    private final int colCnt;
    private final int winCnt;
    private int empty;

    private final List<ForbiddenMove> forbidden = new ArrayList<>();

    private final List<Cell> possibleTurns = List.of(Cell.X, Cell.O, Cell.H, Cell.L);
    private int playerCnt = 2;

    public MNKBoard(int m, int n, int k) {
        rowCnt = m;
        colCnt = n;
        winCnt = k;
        empty = m * n;
        this.cells = new Cell[m][n];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }
    public MNKBoard(int n, int m, int k, List<ForbiddenMove> forbidden) {
        this(n, m, k);
        for (ForbiddenMove c : forbidden) {
            forbidden.add(c);
            empty--;
            cells[c.getRow()][c.getColumn()] = Cell.F;
        }
    }
    public MNKBoard(int n, int m, int k, int playerCnt) {
        this(n, m, k);
        this.playerCnt = playerCnt;
    }
    public MNKBoard(int n, int m, int k, int playerCnt, List<ForbiddenMove> forbidden) {
        this(n, m, k, forbidden);
        this.playerCnt = playerCnt;
    }

    public MNKBoard(MNKBoard b) {
        this(b.rowCnt, b.colCnt, b.winCnt, b.playerCnt, b.forbidden);
        for (int i = 0; i < b.rowCnt; i++) {
            for (int j = 0; j < b.colCnt; j++) {
                cells[i][j] = b.getCell(i, j);
            }
        }
        this.turn = b.turn;
        this.empty = b.empty;
    }

    private Cell next(Cell turn) {
        int i = possibleTurns.indexOf(turn);
        return possibleTurns.get((i + 1) % playerCnt);
    }

    @Override
    public Position getPosition() {
        //return this; // for AI
        return new Position() {
            @Override
            public boolean isValid(Move move) {
                return MNKBoard.this.isValid(move);
            }

            @Override
            public Cell getCell(int r, int c) {
                return MNKBoard.this.getCell(r, c);
            }

            @Override
            public int getRowCnt() {
                return MNKBoard.this.getRowCnt();
            }

            @Override
            public int getColCnt() {
                return MNKBoard.this.getColCnt();
            }

            @Override
            public String toString() {
                return MNKBoard.this.toString();
            }
        };//*/
    }

    @Override
    public Cell getCell() { return turn; }

    private int getCntInDirection(final Move move, int dx, int dy) {
        int x = move.getColumn(), y = move.getRow();
        Cell who = move.getValue();
        int res = 0;
        Cell cur = who;
        while (res < winCnt && cur == who) {
            res++;
            x += dx;
            y += dy;
            if (x < 0 || y < 0 || x >= colCnt || y >= rowCnt) return res;
            cur = cells[y][x];
        }
        return res;
    }

    public boolean checkWin(final Move move) {
        if (getCntInDirection(move, -1, -1) + getCntInDirection(move, 1, 1) - 1 >= winCnt) return true;
        if (getCntInDirection(move, -1, 1) + getCntInDirection(move, 1, -1) - 1 >= winCnt) return true;
        if (getCntInDirection(move, -1, 0) + getCntInDirection(move, 1, 0) - 1 >= winCnt) return true;
        return getCntInDirection(move, 0, -1) + getCntInDirection(move, 0, 1) - 1 >= winCnt;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();
        empty--;

        if (checkWin(move)) {
            return Result.WIN;
        }

        if (empty == 0) {
            return Result.DRAW;
        }

        turn = next(turn);
        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < rowCnt
                && 0 <= move.getColumn() && move.getColumn() < colCnt
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    public static String repeat(String s, int cnt) {
        return new String(new char[cnt]).replace("\0", s);
    }

    @Override
    public String toString() {
        final int indent = Integer.valueOf(rowCnt - 1).toString().length();
        final String indentStr = repeat(" ", indent);
        final StringBuilder sb = new StringBuilder();
        if (rowCnt > 10) {
            sb.append(indentStr).append(repeat(" ", 10));
            for (int decade = 1; decade <= rowCnt / 10; decade++) {
                int cnt = (decade < rowCnt / 10 ? 10 : rowCnt % 10);
                sb.append(repeat(String.valueOf(decade), cnt));
            }
            sb.append("\n");
        }
        sb.append(indentStr);
        for (int c = 0; c < colCnt; c++) {
            sb.append(c % 10);
        }
        for (int r = 0; r < rowCnt; r++) {
            sb.append("\n");
            sb.append(String.format("%" + indent + "d", r));
            for (int c = 0; c < colCnt; c++) {
                sb.append(cells[r][c].toString());
            }
        }
        return sb.toString();
    }


    public int getEmpty() { return empty; }
    @Override
    public int getRowCnt() { return rowCnt; }
    @Override
    public int getColCnt() { return colCnt; }
}

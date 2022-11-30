package game;

public enum Cell {
    /** X - X
     *  O - O
     *  L - |
     *  H - -
     *  E - Empty
     *  F - Forbidden
     */
    X("X"), O("O"),
    L("|"), H("-"),
    E("."), F("#");

    private final String draw;
    Cell(String draw) {
        this.draw = draw;
    }
    @Override
    public String toString() {
        return draw;
    }
}

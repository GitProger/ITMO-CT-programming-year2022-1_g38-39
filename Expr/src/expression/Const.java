package expression;

public class Const implements CommonExpression {
    private final int intValue;
    private final double doubleValue;
    private final boolean isInteger;

    Const(int c) {
        isInteger = true;
        intValue = c;
        doubleValue = c;
    }
    Const(double c) {
        isInteger = false;
        intValue = (int)c;
        doubleValue = c;
    }

    public boolean isInteger() { return isInteger; }
    @Override
    public int evaluate(int x) { return intValue; }
    @Override
    public double evaluate(double x) { return doubleValue; }
    @Override
    public int evaluate(int x, int y, int z) {  return intValue; }
    @Override
    public String toString() {
        return isInteger ? "" + intValue : "" + doubleValue;
    }
    @Override
    public String toMiniString() { return this.toString(); }
}

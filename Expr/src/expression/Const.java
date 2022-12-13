package expression;

public class Const implements CommonExpression {
    private final Number value;

    public Const(int c) {
        value = c;
    }

    public Const(double c) {
        value = c;
    }

    public boolean isInteger() {
        return value instanceof Integer;
    }

    @Override
    public int evaluate(int x) {
        return value.intValue();
    }

    @Override
    public double evaluate(double x) {
        return value.doubleValue();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value.intValue();
    }

    @Override
    public String toString() {
        return isInteger() ? "" + value.intValue() : "" + value.doubleValue();
    }

    @Override
    public String toMiniString() {
        return this.toString();
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Const that)) return false;
        return value.equals(that.value);
    }
}

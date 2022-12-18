package expression;

public class Subtract extends AbstractBinaryOperator {
    public Subtract(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    public String getOperatorName() {
        return "-";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public double evaluate(double x) {
        return left.evaluate(x) - right.evaluate(x);
    }

    @Override
    public int evaluate(int x) {
        return left.evaluate(x) - right.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return left.evaluate(x, y, z) - right.evaluate(x, y, z);
    }
}

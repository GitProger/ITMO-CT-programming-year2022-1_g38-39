package expression;

public class Negate extends AbstractUnaryOperator {
    public Negate(CommonExpression expr) {
        super(expr);
    }

    @Override
    public String getOperatorName() {
        return "-";
    }

    @Override
    public int priority() {
        return 3;
    }

    @Override
    public double evaluate(double x) {
        return -expr.evaluate(x);
    }

    @Override
    public int evaluate(int x) {
        return -expr.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -expr.evaluate(x, y, z);
    }
}

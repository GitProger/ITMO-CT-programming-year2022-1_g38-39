package expression;

public class Add extends AbstractBinaryOperator {
    public Add(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public String getOperatorName() {
        return "+";
    }

    @Override
    protected boolean needRightBracket() {
        if ((right instanceof Add || right instanceof Subtract)) return false;
        return super.needRightBracket();
    }

    @Override
    public double evaluate(double x) {
        return left.evaluate(x) + right.evaluate(x);
    }

    @Override
    public int evaluate(int x) {
        return left.evaluate(x) + right.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return left.evaluate(x, y, z) + right.evaluate(x, y, z);
    }
}

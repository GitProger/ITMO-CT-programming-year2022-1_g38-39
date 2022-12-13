package expression;

public class Multiply extends AbstractBinaryOperator {
    public Multiply(CommonExpression a, CommonExpression b) {
        super(a, b, (x, y) -> x * y, (x, y) -> x * y);
    }

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public String getOperatorName() {
        return "*";
    }

    @Override
    protected boolean needRightBracket() {
        if (right instanceof Multiply) return false;
        return super.needRightBracket();
    }
}

package expression;

public class Subtract extends AbstractBinaryOperator {
    public Subtract(CommonExpression a, CommonExpression b) {
        super(a, b, (x, y) -> x - y, (x, y) -> x - y);
    }

    @Override
    public String getOperatorName() {
        return "-";
    }

    @Override
    public int priority() {
        return 0;
    }
}

package expression;

public class Divide extends AbstractBinaryOperator {
    public Divide(CommonExpression a, CommonExpression b) {
        super(a, b, (x, y) -> x / y, (x, y) -> x / y);
    }

    @Override
    public String getOperatorName() {
        return "/";
    }

    @Override
    public int priority() {
        return 1;
    }

}

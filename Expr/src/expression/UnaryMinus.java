package expression;

public class UnaryMinus extends AbstractUnaryOperator {
    public UnaryMinus(CommonExpression expr) {
        super(expr, x -> -x, x -> -x);
    }

    @Override
    public String getOperatorName() {
        return "-";
    }

    @Override
    public int priority() {
        return 3;
    }
}

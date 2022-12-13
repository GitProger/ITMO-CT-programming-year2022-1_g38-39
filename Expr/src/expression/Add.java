package expression;

public class Add extends AbstractBinaryOperator {
    public Add(CommonExpression a, CommonExpression b) {
        super(a, b, Integer::sum, Double::sum);
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
}

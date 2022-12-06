package expression;

public class Add extends AbstractBinaryOperator {
    Add(CommonExpression a, CommonExpression b) {
        super("+", a, b, (x, y) -> x + y, (x, y) -> x + y);
    }
}

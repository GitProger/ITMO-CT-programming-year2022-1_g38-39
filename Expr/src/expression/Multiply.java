package expression;

public class Multiply extends AbstractBinaryOperator {
    Multiply(CommonExpression a, CommonExpression b) {
        super("*", a, b, (x, y) -> x * y, (x, y) -> x * y);
    }
}

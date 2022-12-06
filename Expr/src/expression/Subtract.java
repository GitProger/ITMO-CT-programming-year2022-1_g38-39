package expression;

public class Subtract extends AbstractBinaryOperator {
    Subtract(CommonExpression a, CommonExpression b) {
        super("-", a, b, (x, y) -> x - y, (x, y) -> x - y);
    }
}

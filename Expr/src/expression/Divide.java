package expression;

public class Divide extends AbstractBinaryOperator {
    Divide(CommonExpression a, CommonExpression b) {
        super("/", a, b, (x, y) -> x / y, (x, y) -> x / y);
    }
}

package expression.exceptions;
import expression.*;

public class CheckedDivide extends Divide {
    public CheckedDivide(CommonExpression a, CommonExpression b) {
        super(a, b);
    }
    @Override
    public int evaluate(int x, int y, int z) {
        return left.evaluate(x, y, z) / right.evaluate(x, y, z);
    }
}

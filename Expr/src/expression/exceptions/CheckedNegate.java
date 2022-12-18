package expression.exceptions;
import expression.*;

public class CheckedNegate extends Negate {
    public CheckedNegate(CommonExpression expr) {
        super(expr);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int tmp = expr.evaluate(x, y, z);
        if (tmp == Integer.MIN_VALUE) throw new ArithmeticException("overflow");
        return -tmp;
    }
}

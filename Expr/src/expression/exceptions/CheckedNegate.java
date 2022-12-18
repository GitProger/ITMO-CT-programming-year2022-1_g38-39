package expression.exceptions;
import expression.*;

public class CheckedNegate extends Negate {
    public CheckedNegate(CommonExpression expr) {
        super(expr);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -expr.evaluate(x, y, z);
    }
}

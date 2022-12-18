package expression.exceptions;
import expression.*;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int lVal = left.evaluate(x, y, z);
        int rVal = right.evaluate(x, y, z);
        int res = lVal * rVal;
        if ((lVal == -1 && rVal == Integer.MIN_VALUE) || (rVal == -1 && lVal == Integer.MIN_VALUE)) throw new ArithmeticException("overflow");
        if (lVal != 0 && res / lVal != rVal) throw new ArithmeticException("overflow");
        return res;
    }
}

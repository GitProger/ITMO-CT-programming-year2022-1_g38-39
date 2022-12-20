package expression.exceptions;
import expression.*;

public class CheckedLcm extends Lcm {
    public CheckedLcm(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    public int evaluate(int x, int y, int z) { // only TripleExpression required
        int lVal = left.evaluate(x, y, z);
        int rVal = right.evaluate(x, y, z);
        int res = lcm(lVal, rVal);
        if ((lVal != 0 && res % lVal != 0) || (rVal != 0 && res % rVal != 0)) throw new ArithmeticException("overflow");
        return res;
    }
}

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
        int g = Gcd.gcd(lVal, rVal);
        if (g == 0) return 0;
        int res = lVal * (rVal / g);

        if ((lVal == -1 && (rVal / g) == Integer.MIN_VALUE) || ((rVal / g) == -1 && lVal == Integer.MIN_VALUE)) throw new ArithmeticException("overflow");
        if (lVal != 0 && res / lVal != (rVal / g)) throw new ArithmeticException("overflow");
        if ((lVal != 0 && res % lVal != 0) || (rVal != 0 && res % rVal != 0)) throw new ArithmeticException("overflow");

        return res;
    }
}

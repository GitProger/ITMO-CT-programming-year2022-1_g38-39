package expression.exceptions;
import expression.*;

public class CheckedGcd extends Gcd {
    public CheckedGcd(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    public int evaluate(int x, int y, int z) { // only TripleExpression required
        int lVal = left.evaluate(x, y, z);
        int rVal = right.evaluate(x, y, z);
        int res = gcd(lVal, rVal);
        if (res != 0 && (lVal % res != 0 || rVal % res != 0)) {
            throw new ArithmeticException("overflow");
        }
        return res;
    }
}

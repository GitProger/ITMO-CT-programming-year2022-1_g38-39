package expression.exceptions;
import expression.*;

public class CheckedAdd extends Add {
    public CheckedAdd(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    public int evaluate(int x, int y, int z) { // only  TripleExpression required
        int lVal = left.evaluate(x, y, z);
        int rVal = right.evaluate(x, y, z);
        int res = lVal + rVal;
        if (((lVal & rVal & ~res) | (~lVal & ~rVal & res)) < 0) throw new ArithmeticException("overflow");
        return res;
    }
}

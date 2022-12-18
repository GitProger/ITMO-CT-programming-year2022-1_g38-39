package expression.exceptions;
import expression.*;

public class CheckedDivide extends Divide {
    public CheckedDivide(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int lVal = left.evaluate(x, y, z);
        int rVal = right.evaluate(x, y, z);
        if (rVal == 0) throw new ArithmeticException("division by zero");
        // modulo of lVal always decreases except cases when rVal is +-1
        // so, rVal = 1 -> ok, rVal = -1 -> we have to Check overflow like in `Negate` class
        if (rVal == -1 && lVal == Integer.MIN_VALUE) throw new ArithmeticException("overflow");
        return  lVal / rVal;
    }
}

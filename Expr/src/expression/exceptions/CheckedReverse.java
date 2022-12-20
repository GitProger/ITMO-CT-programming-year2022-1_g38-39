package expression.exceptions;

import expression.CommonExpression;
import expression.Reverse;

public class CheckedReverse extends Reverse {
    @Override
    protected int reverse(int x) {
        int c = x < 0 ? -1 : 1;
        try {
            String s = new StringBuilder(String.valueOf(c * x)).reverse().toString();
            return c * Integer.parseInt(s);
        } catch (RuntimeException e) {
            throw new ArithmeticException("overflow");
        }
    }

    public CheckedReverse(CommonExpression e) {
        super(e);
    }
}

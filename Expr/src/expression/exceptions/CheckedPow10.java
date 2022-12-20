package expression.exceptions;
import expression.*;

public class CheckedPow10 extends Pow10 {
    @Override
    protected int iPow10(int x) {
        int ans = 1;
        while (x > 0) {
            x--;
            if (((ans * 10) / 10) != ans) throw new ArithmeticException("overflow");
            ans *= 10;
        }
        return ans;
    }

    public CheckedPow10(CommonExpression e) {
        super(e);
    }
}

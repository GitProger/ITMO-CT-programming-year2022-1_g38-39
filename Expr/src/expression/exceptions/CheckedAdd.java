package expression.exceptions;
import expression.*;

public class CheckedAdd extends Add {
    public CheckedAdd(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    public int evaluate(int x, int y, int z) { // only  TripleExpression required
        return left.evaluate(x, y, z) + right.evaluate(x, y, z);
    }
}

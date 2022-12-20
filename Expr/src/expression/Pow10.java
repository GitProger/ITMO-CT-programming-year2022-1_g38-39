package expression;

public class Pow10 extends AbstractUnaryOperator {
    protected int iPow10(int x) {
        if (x < 0) return 0;
        int ans = 1;
        while (x > 0) {
            x--;
            ans *= 10;
        }
        return ans;
    }

    public Pow10(CommonExpression expr) {
        super(expr);
    }

    @Override
    public String getOperatorName() {
        return "pow10";
    }

    @Override
    public int priority() {
        return 4;
    }

    @Override
    public double evaluate(double x) {
        return Math.pow(10, expr.evaluate(x));
    }

    @Override
    public int evaluate(int x) {
        return iPow10(expr.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return iPow10(expr.evaluate(x, y, z));
    }

}

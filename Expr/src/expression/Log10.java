package expression;

public class Log10 extends AbstractUnaryOperator {
    protected int iLog10(int x) {
        if (x <= 0) {
            throw new ArithmeticException("logarithm of a negative number");
        }
        int ans = 0;
        while (x >= 10) {
            x /= 10;
            ans++;
        }
        return ans;
    }

    public Log10(CommonExpression expr) {
        super(expr);
    }

    @Override
    public String getOperatorName() {
        return "log10";
    }

    @Override
    public int priority() {
        return 4;
    }

    @Override
    public double evaluate(double x) {
        return Math.log10(expr.evaluate(x));
    }

    @Override
    public int evaluate(int x) {
        return iLog10(expr.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return iLog10(expr.evaluate(x, y, z));
    }

}

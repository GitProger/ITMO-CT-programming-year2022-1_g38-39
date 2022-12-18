package expression;


public class Gcd extends AbstractBinaryOperator {
    public static int gcd(int a, int b) {
        return b == 0 ? Math.abs(a) : gcd(b, a % b);
    }

    public Gcd(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    public int priority() {
        return -1;
    }

    @Override
    public String getOperatorName() {
        return "gcd";
    }

    @Override
    protected boolean needRightBracket() {
        if (right instanceof Gcd) return false;
        return super.needRightBracket();
    }


    @Override
    public double evaluate(double x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int evaluate(int x) {
        return gcd(left.evaluate(x), right.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return gcd(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }
}

package expression;


public class Lcm extends AbstractBinaryOperator {
    public static int lcm(int a, int b) {
        int g = Gcd.gcd(a, b);
        if (g == 0) {
            return 0;
        }
        return a * (b / g);
    }

    public Lcm(CommonExpression a, CommonExpression b) {
        super(a, b);
    }

    @Override
    public int priority() {
        return -1;
    }

    @Override
    public String getOperatorName() {
        return "lcm";
    }

    @Override
    protected boolean needRightBracket() {
        if (right instanceof Lcm) return false;
        return super.needRightBracket();
    }

    @Override
    public double evaluate(double x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int evaluate(int x) {
        return lcm(left.evaluate(x), right.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return lcm(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

}

package expression;


public class Gcd extends AbstractBinaryOperator {
    public static int gcd(int a, int b) {
        return b == 0 ? Math.abs(a) : gcd(b, a % b);
    }

    public Gcd(CommonExpression a, CommonExpression b) {
        super(a, b, Gcd::gcd, (x, y) -> (double) gcd((int) Math.round(x), (int) Math.round(y)));
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
//        if ((right instanceof Gcd || right instanceof Lcm)) return false;
        return super.needRightBracket();
    }
}

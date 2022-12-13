package expression;


public class Lcm extends AbstractBinaryOperator {
    public static int lcm(int a, int b) {
        int g = Gcd.gcd(a, b);
        if (g == 0) {
            System.err.println("lcm(0, 0) is incorrect, considering lcm(0, 0) = 0");
            return 0;
        }
        return (int)((long)a * (long)b / g);
    }

    public Lcm(CommonExpression a, CommonExpression b) {
        super(a, b, Lcm::lcm, (x, y) -> (double) lcm((int) Math.round(x), (int) Math.round(y)));
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
//        if ((right instanceof Gcd || right instanceof Lcm)) return false;
        return super.needRightBracket();
    }
}

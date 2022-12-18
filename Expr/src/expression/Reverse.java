package expression;

public class Reverse extends AbstractUnaryOperator {
    public static int reverse(int x) {
        long c = x < 0 ? -1L : 1L;
        String s = new StringBuilder(String.valueOf(c * x)).reverse().toString();
        try {
            return (int)(c * Long.parseLong(s));
        } catch (RuntimeException e) {
            System.err.println("Error: bad number `" + x + "` in reverse");
            return (int)c * x;
        }
    }

    public Reverse(CommonExpression expr) {
        super(expr);
    }

    @Override
    public String getOperatorName() {
        return "reverse";
    }

    @Override
    public int priority() {
        return 3;
    }

    @Override
    public double evaluate(double x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int evaluate(int x) {
        return reverse(expr.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return reverse(expr.evaluate(x, y, z));
    }

}

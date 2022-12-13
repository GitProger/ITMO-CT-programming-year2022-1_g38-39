package expression;

public class Reverse extends AbstractUnaryOperator {
    public static int reverse(int x) {
        int c = x < 0 ? -1 : 1;
        String s = new StringBuilder(String.valueOf(x * c)).reverse().toString();
        try {
            return Integer.parseInt(s);
        } catch (RuntimeException e) {
            System.err.println("Error: bad number in reverse; specific exception: " + e.getMessage());
            e.printStackTrace();
            return x * c;
        }
    }

    public Reverse(CommonExpression expr) {
        super(expr, Reverse::reverse, x -> (double) reverse((int) Math.round(x)));
    }

    @Override
    public String getOperatorName() {
        return "reverse";
    }

    @Override
    public int priority() {
        return 3;
    }
}

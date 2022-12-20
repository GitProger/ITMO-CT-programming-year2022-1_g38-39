package expression;
import expression.parser.*;

public class Main {
    public static int parabola(int x) {
        var expr = new Add(
                new Subtract(
                        new Multiply(
                                new Variable("x"),
                                new Variable("x")
                        ),
                        new Multiply(
                                new Const(2),
                                new Variable("x")
                        )
                ),
                new Const(1)
        );
        System.out.println(expr.toMiniString());
        return expr.evaluate(x);
    }

    public static void errors() {
        var expr = new ExpressionParser().parse("1000000*x*x*x*x*x/(x-1)");
        System.out.printf("%c%8c\n", 'x', 'f');
        for (int x = 0; x <= 10; x++) {
            String res = "";
            try {
                res = Integer.toString(expr.evaluate(x, 0, 0));
            } catch (ArithmeticException e) {
                res = e.getMessage();
            }
            System.out.printf("%-8d%s\n", x, res);
        }
    }

    public static void main(String[] args) {
        var e = new ExpressionParser().parse("(-1 lcm -2147483648)");
        System.out.println(e.evaluate(0, 0, 0));
//        errors();
//        if (args.length == 0) {
//            System.out.println("No input integer provided.");
//            return;
//        }
//        try {
//            int x = Integer.parseInt(args[0]);
//            System.out.println(parabola(x));
//        } catch (RuntimeException e) {
//            System.out.println("An error occurred: " + e.getMessage());
//            e.printStackTrace();
//        }
    }
}

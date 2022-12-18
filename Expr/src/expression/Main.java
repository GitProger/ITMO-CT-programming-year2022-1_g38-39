package expression;
import expression.parser.*;

public class Main {
    private static void unaryMinusTest() {
        var a = new Negate(new Add(
                new Negate(new Const(10)),
                new Const(20)
        ));
        System.out.println(a);
        System.out.println(a.toMiniString());

        var b = new Negate(new Const(1));
        System.out.println(b);
        System.out.println(b.toMiniString());
    }

    private static void parseTest() {
        TripleExpression e = new ExpressionParser().parse("reverse(-2147483648)");
        System.out.println(e);
        System.out.println(e.toMiniString());
        System.out.println(e.evaluate(1, 2, 3));
    }

    private static void newFuncTest() {
        var e = new Gcd(new Lcm(new Const(20), new Const(120)), new Lcm(new Const(2), new Const(3)));
        System.out.println(e);
        System.out.println(e.toMiniString());
        System.out.println(e.evaluate(0));

        var f = new Lcm(new Gcd(new Lcm(new Const(20), new Const(120)), new Const(2)), new Const(3));
        System.out.println(f);
        System.out.println(f.toMiniString());
        System.out.println(f.evaluate(0));

        var g = new Lcm(new Const(20), new Gcd(new Const(120), new Lcm(new Const(2), new Const(3))));
        System.out.println(g);
        System.out.println(g.toMiniString());
        System.out.println(g.evaluate(0));
    }

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

    public static void main(String[] args) {
       // parseTest();
       // newFuncTest();

        if (args.length == 0) {
            System.out.println("No input integer provided.");
            return;
        }
        try {
            int x = Integer.parseInt(args[0]);
            System.out.println(parabola(x));
        } catch (RuntimeException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

package expression;

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

    public static void main(String[] args) {
        // System.out.println(new Subtract(new Const(1), new Add(new Const(2), new Const(3))).toMiniString());
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

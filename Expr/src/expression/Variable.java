package expression;

public class Variable implements CommonExpression {
    private final String name;
    String getName() { return name; }
    Variable(String name) { this.name = name; }
    @Override
    public int evaluate(int x) { return x; }
    @Override
    public double evaluate(double x) { return x; }

    @Override
    public int evaluate(int x, int y, int z) {
        return switch (name) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> throw new RuntimeException("Unsupported variable name '" + name + "'in evaluate(?, ?, ?), acceptable are: x, y, z");
        };
    }

    @Override
    public String toString() { return  name; }
    @Override
    public String toMiniString() { return name; }
}

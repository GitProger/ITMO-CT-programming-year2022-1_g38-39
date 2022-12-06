package expression;

import java.util.Objects;

interface LambdaParameterInt {
    int run(int l, int r);
}

interface LambdaParameterDouble {
    double run(double l, double r);
}

public class AbstractBinaryOperator implements CommonExpression {
    protected final String operatorName;
    protected final CommonExpression left, right;
    protected final LambdaParameterInt operatorInt;
    protected final LambdaParameterDouble operatorDouble;

    protected static String embrace(String s) {
        return "(" + s + ")";
    }

    protected static String pad(String s) {
        return " " + s + " ";
    }

    protected static int priority(CommonExpression op) {
        if (op instanceof Const || op instanceof Variable) return 2;
        if (op instanceof Multiply || op instanceof Divide) return 1;
        return 0; // Add and Subtract
    }

    AbstractBinaryOperator(String operatorName, CommonExpression left, CommonExpression right, LambdaParameterInt opI, LambdaParameterDouble opD) {
        this.operatorName = operatorName;
        this.left = left;
        this.right = right;
        this.operatorInt = opI;
        this.operatorDouble = opD;
    }

    public String getOperatorName() {
        return operatorName;
    }

    @Override
    public double evaluate(double x) {
        return operatorDouble.run(left.evaluate(x), right.evaluate(x));
    }

    @Override
    public int evaluate(int x) {
        return operatorInt.run(left.evaluate(x), right.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return operatorInt.run(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof AbstractBinaryOperator that) &&
                Objects.equals(operatorName, that.operatorName) &&
                Objects.equals(left, that.left) && Objects.equals(right, that.right);
    }

    @Override
    public String toString() {
        return embrace(left.toString() + pad(operatorName) + right.toString());
    }

    @Override
    public String toMiniString() { // TODO: add analyzing of .right; bad example: 1 - (2 + 3)
        String l = priority(left) < priority(this) ? embrace(left.toMiniString()) : left.toMiniString();
        String r = right.toMiniString();
        if (priority(right) < priority(this)) {
            r = embrace(r);
        } else if (this instanceof Divide && priority(right) != 2) { // is not Const || Variable
            r = embrace(r);
        } else if (this instanceof Subtract && priority(right) == 0) { // is Add || Subtract
            r = embrace(r);
        }
        return l + pad(operatorName) + r;
    }
}

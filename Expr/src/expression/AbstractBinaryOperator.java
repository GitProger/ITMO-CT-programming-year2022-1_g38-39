package expression;

import java.util.Objects;
import java.util.function.BiFunction;

public abstract class AbstractBinaryOperator implements CommonExpression {
    protected final CommonExpression left, right;
    protected final BiFunction<Integer, Integer, Integer> operatorInt;
    protected final BiFunction<Double, Double, Double> operatorDouble;

    public AbstractBinaryOperator(CommonExpression left, CommonExpression right, BiFunction<Integer, Integer, Integer> opI, BiFunction<Double, Double, Double> opD) {
        this.left = left;
        this.right = right;
        this.operatorInt = opI;
        this.operatorDouble = opD;
    }

    public abstract int priority();

    public abstract String getOperatorName();

    @Override
    public double evaluate(double x) {
        return operatorDouble.apply(left.evaluate(x), right.evaluate(x));
    }

    @Override
    public int evaluate(int x) {
        return operatorInt.apply(left.evaluate(x), right.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return operatorInt.apply(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractBinaryOperator that)) return false;
        return Objects.equals(getOperatorName(), that.getOperatorName()) && Objects.equals(left, that.left) && Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, getOperatorName());
    }


    public void toString(StringBuilder sb) {
        sb.append("(");
        if (left instanceof AbstractBinaryOperator l) l.toString(sb);
        else sb.append(left.toString());
        sb.append(" ").append(getOperatorName()).append(" ");
        if (right instanceof AbstractBinaryOperator r) r.toString(sb);
        else sb.append(right.toString());
        sb.append(")");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(sb);
        return sb.toString();
    }


    public void toMiniString(StringBuilder sb) {
        if (needLeftBracket()) sb.append("(");
        if (left instanceof AbstractBinaryOperator l) {
            l.toMiniString(sb);
        } else {
            sb.append(left.toMiniString());
        }
        if (needLeftBracket()) sb.append(")");
        sb.append(" ").append(getOperatorName()).append(" ");
        if (needRightBracket()) sb.append("(");
        if (right instanceof AbstractBinaryOperator r) {
            r.toMiniString(sb);
        } else {
            sb.append(right.toMiniString());
        }
        if (needRightBracket()) sb.append(")");
    }


    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        toMiniString(sb);
        return sb.toString();
    }

    protected boolean needLeftBracket() {
        if (!(left instanceof AbstractBinaryOperator)) return false; // Const and Variable doesn't need
        return ((AbstractBinaryOperator) left).priority() < priority();
    }

    protected boolean needRightBracket() {
        if (!(right instanceof AbstractBinaryOperator)) return false; // Const and Variable doesn't need
        return ((AbstractBinaryOperator) right).priority() <= priority();
    }
}

package expression;

import java.util.Objects;
import java.util.function.BiFunction;

public abstract class AbstractBinaryOperator implements CommonExpression {
    protected final CommonExpression left, right;

    public AbstractBinaryOperator(CommonExpression left, CommonExpression right) {
        this.left = left;
        this.right = right;
    }

    public abstract int priority();

    public abstract String getOperatorName();

    @Override
    public abstract double evaluate(double x);

    @Override
    public abstract int evaluate(int x);

    @Override
    public abstract int evaluate(int x, int y, int z);

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

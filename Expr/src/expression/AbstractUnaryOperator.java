package expression;

import java.util.Objects;

public abstract class AbstractUnaryOperator implements CommonExpression { // prefix
    protected final CommonExpression expr;

    public AbstractUnaryOperator(CommonExpression expr) {
        this.expr = expr;
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
        if (!(o instanceof AbstractUnaryOperator that)) return false;
        return Objects.equals(getOperatorName(), that.getOperatorName()) && Objects.equals(expr, that.expr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expr, getOperatorName());
    }

    private void toString(StringBuilder sb) {
        sb.append(getOperatorName());
        sb.append("(");
        if (expr instanceof AbstractBinaryOperator r) r.toString(sb);
        else sb.append(expr.toString());
        sb.append(")");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(sb);
        return sb.toString();
    }


    private void toMiniString(StringBuilder sb) {
        sb.append(getOperatorName());
        if (needBracket()) sb.append("(");
        else sb.append(" ");
        if (expr instanceof AbstractBinaryOperator r) {
            r.toMiniString(sb);
        } else {
            sb.append(expr.toMiniString());
        }
        if (needBracket()) sb.append(")");
    }


    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        toMiniString(sb);
        return sb.toString();
    }

    protected boolean needBracket() {
        if (!(expr instanceof AbstractBinaryOperator)) return false; // Const and Variable doesn't need
        return ((AbstractBinaryOperator) expr).priority() <= priority();
    }
}

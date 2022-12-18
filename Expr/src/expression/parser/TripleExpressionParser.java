package expression.parser;
import expression.*;

public class TripleExpressionParser extends BaseParser {
    private String src;
    public TripleExpressionParser(String source) {
        super(new StringSource(source));
        src = source;
    }

    private void skipWhitespace() {
        while (between((char)1, (char)32) || between((char)128, (char)Character.MAX_CODE_POINT)) {
            take();
        }
    }

    private boolean take(String s) { //
        if (!take(s.charAt(0))) return false;
        expect(s.substring(1));
        return true;
    }

    private enum Type {
        NUM, VAR,
        LBR, RBR,
        MUL, DIV,
        ADD, SUB,
        GCD, LCM,
        REV,
        END
    }
    private Type curType = Type.END;
    private int num = 0;
    private String variable = "";

    private void parseNumber(String st) {
        StringBuilder sb = new StringBuilder(st);
        if (take('-')) {
            sb.append('-');
        }
        if (take('0')) {
            sb.append('0');
        } else if (between('1', '9')) {
            while (between('0', '9')) {
                sb.append(take());
            }
        } else {
            throw error("Invalid number");
        }
        try {
            num = Integer.parseInt(sb.toString());
        } catch (RuntimeException e) {
            System.err.println("Bad number `" + sb + "`");
        }
    }

    private void getToken() {
        skipWhitespace();
        if (take('\0') || eof()) { curType = Type.END; }
        else if (take('(')) { curType = Type.LBR; }
        else if (take(')')) { curType = Type.RBR; }
        else if (take('*')) { curType = Type.MUL; }
        else if (take('/')) { curType = Type.DIV; }
        else if (take('+')) { curType = Type.ADD; }
        else if (take('-')) {
            var prev = curType;
            curType = Type.SUB;
            if (prev != Type.NUM && prev != Type.VAR && prev != Type.RBR && between('0', '9')) {
                curType = Type.NUM;
                parseNumber("-");
            }
        }
        else if (between('x', 'z')) {
            curType = Type.VAR;
            variable = String.valueOf(take());
        } else if (between('0', '9')) {
            curType = Type.NUM;
            parseNumber("");
        } else if (take("reverse")) { //
            curType = Type.REV;
        } else if (take("gcd")) { //
            curType = Type.GCD;
        } else if (take("lcm")) { //
            curType = Type.LCM;
        }
    }


    CommonExpression prim(boolean get) {
        if (get) {
            getToken();
        }

        switch (curType) {
            case NUM: {
                CommonExpression v = new Const(num);
                getToken();
                return v;
            }
            case VAR: {
                CommonExpression v = new Variable(variable);
                getToken();
                return v;
            }
            case SUB:
                return new Negate(prim(true));
            case LBR: {
                CommonExpression e = numther(true); // expr
                if (curType != Type.RBR) {
                    throw error("')' expected");
                }
                getToken();
                return e;
            }
            case REV: //
                return new Reverse(prim(true));
            default:
                throw error("primary expression expected, got: " + curType + " in `" + src + "`");
        }
    }

    CommonExpression term(boolean get) {
        CommonExpression left = prim(get);
        while (true) {
            if (curType == Type.MUL) {
                left = new Multiply(left, prim(true));
            } else if (curType == Type.DIV) {
                left = new Divide(left, prim(true));
            } else
                return left;
        }
    }

    CommonExpression expr(boolean get) {
        CommonExpression left = term(get);
        while (true) {
            if (curType == Type.ADD) {
                left = new Add(left, term(true));
            } else if (curType == Type.SUB) {
                left = new Subtract(left, term(true));
            } else
                return left;
        }
    }

    CommonExpression numther(boolean get) {
        CommonExpression left = expr(get);
        while (true) {
            if (curType == Type.GCD) {
                left = new Gcd(left, expr(true));
            } else if (curType == Type.LCM) {
                left = new Lcm(left, expr(true));
            } else
                return left;
        }
    }

    public TripleExpression parse() {
        if (src.trim().isEmpty()) return new Const(0);
        getToken();
        return numther(false);
    }
}

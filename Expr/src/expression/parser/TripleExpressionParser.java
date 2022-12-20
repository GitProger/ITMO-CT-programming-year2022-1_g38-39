package expression.parser;

import expression.*;
import expression.exceptions.*;

public class TripleExpressionParser extends BaseParser {
    private final String src;

    public TripleExpressionParser(String source) {
        super(new StringSource(source));
        src = source;
    }

    private boolean isWhitespace() {
        return between((char) 1, (char) 32) || between((char) 128, (char) Character.MAX_CODE_POINT);
    }

    private void skipWhitespace() {
        while (isWhitespace()) {
            take();
        }
    }

    private boolean take(String s) {
        for (char c : s.toCharArray()) {
            if (!take(c)) {
                return false;
            }
        }
        return true;
    }

    private void verifyFunction() {
        if (!isWhitespace() && !test('(') && !test('-')) {
            throw error("Separator expected (space, '(' or '-')");
        }
    }

    private enum Type {
        NUM, VAR,
        LBR, RBR,
        POW10, LOG10,
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
        num = Integer.parseInt(sb.toString());
    }

    private void getToken() {
        skipWhitespace();
        if (take('\0') || eof()) {
            curType = Type.END;
        } else if (take('(')) {
            curType = Type.LBR;
        } else if (take(')')) {
            curType = Type.RBR;
        } else if (take('*')) {
            curType = Type.MUL;
        } else if (take('/')) {
            curType = Type.DIV;
        } else if (take('+')) {
            curType = Type.ADD;
        } else if (take('-')) {
            var prev = curType;
            curType = Type.SUB;
            if (prev != Type.NUM && prev != Type.VAR && prev != Type.RBR && between('0', '9')) {
                curType = Type.NUM;
                parseNumber("-");
            }
        } else if (between('x', 'z')) {
            curType = Type.VAR;
            variable = String.valueOf(take());
        } else if (between('0', '9')) {
            curType = Type.NUM;
            parseNumber("");
        } else if (take("reverse")) {
            curType = Type.REV;
            verifyFunction();
        } else if (take("gcd")) {
            curType = Type.GCD;
            verifyFunction();
        } else if (take("l")) {
            if (take("cm")) {
                curType = Type.LCM;
                verifyFunction();
            } else if (take("og10")) {
                curType = Type.LOG10;
                verifyFunction();
            }
        } else if (take("pow10")) {
            curType = Type.POW10;
            verifyFunction();
        }
    }


    CommonExpression prim(boolean checkedOperations) {
        getToken();
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
                return (checkedOperations
                        ? new CheckedNegate(prim(checkedOperations))
                        : new Negate(prim(checkedOperations)));

            case POW10:
                return (checkedOperations
                        ? new CheckedPow10(prim(checkedOperations))
                        : new Pow10(prim(checkedOperations)));
            case LOG10:
                return (checkedOperations
                        ? new CheckedLog10(prim(checkedOperations))
                        : new Log10(prim(checkedOperations)));

            case LBR: {
                CommonExpression e = numther(checkedOperations); // expr
                if (curType != Type.RBR) {
                    throw error("')' expected");
                }
                getToken();
                return e;
            }

            case REV: //
                return (checkedOperations
                        ? new CheckedReverse(prim(checkedOperations))
                        : new Reverse(prim(checkedOperations)));

            default:
                throw error("primary expression expected, got: " + curType + " in `" + src + "`");
        }
    }

    CommonExpression term(boolean checkedOperations) {
        CommonExpression left = prim(checkedOperations);
        while (true) {
            if (curType == Type.MUL) {
                left = (checkedOperations
                        ? new CheckedMultiply(left, prim(checkedOperations))
                        : new Multiply(left, prim(checkedOperations)));
            } else if (curType == Type.DIV) {
                left = (checkedOperations
                        ? new CheckedDivide(left, prim(checkedOperations))
                        : new Divide(left, prim(checkedOperations)));
            } else {
                return left;
            }
        }
    }

    CommonExpression expr(boolean checkedOperations) {
        CommonExpression left = term(checkedOperations);
        while (true) {
            if (curType == Type.ADD) {
                left = (checkedOperations
                        ? new CheckedAdd(left, term(checkedOperations))
                        : new Add(left, term(checkedOperations)));
            } else if (curType == Type.SUB) {
                left = (checkedOperations
                        ? new CheckedSubtract(left, term(checkedOperations))
                        : new Subtract(left, term(checkedOperations)));
            } else {
                return left;
            }
        }
    }

    CommonExpression numther(boolean checkedOperations) {
        CommonExpression left = expr(checkedOperations);
        while (true) {
            if (curType == Type.GCD) {
                left = (checkedOperations
                        ? new CheckedGcd(left, expr(checkedOperations))
                        : new Gcd(left, expr(checkedOperations)));
            } else if (curType == Type.LCM) {
                left = (checkedOperations
                        ? new CheckedLcm(left, expr(checkedOperations))
                        : new Lcm(left, expr(checkedOperations)));
            } else {
                return left;
            }
        }
    }

    public TripleExpression parse(boolean checkedOperations) {
        var r = numther(checkedOperations);
        if (curType != Type.END) {
            if (curType == Type.RBR) {
                throw error("Unopened ')'");
            }
            throw error("Unexpected character");
        }
        return r;
    }
}

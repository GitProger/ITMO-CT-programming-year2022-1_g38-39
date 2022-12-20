package expression.parser;

import expression.TripleExpression;

public class ExpressionParser implements TripleParser {
    @Override
    public TripleExpression parse(String expression) {
    //    System.err.println("<<" + "\u001B[32m" + expression + "\u001B[0m" + ">>");
        return new TripleExpressionParser(expression).parse(true);
    }
}

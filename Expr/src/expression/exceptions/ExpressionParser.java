package expression.exceptions;

import expression.TripleExpression;
import expression.parser.TripleExpressionParser;

public class ExpressionParser implements TripleParser {
    @Override
    public TripleExpression parse(String expression) {
        return new TripleExpressionParser(expression).parse(true);
    }
}

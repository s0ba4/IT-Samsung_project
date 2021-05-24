package itschool.xcalculator.domain.calculator;

import java.util.ArrayList;

import itschool.xcalculator.dto.Node;
import itschool.xcalculator.domain.Parser;
import itschool.xcalculator.domain.PostfixConverter;
import itschool.xcalculator.domain.Renderer;
import itschool.xcalculator.dto.Token;

import static itschool.xcalculator.dto.Token.TokenType.VARIABLE;

public class GeneralCalculator {

    private final Parser parser = new Parser();
    private final Renderer render = new Renderer();
    private final NumericCalculator numericCalculator = new NumericCalculator();
    private final PostfixConverter converter = new PostfixConverter();
    private final VariableCalculator variableCalculator = new VariableCalculator();

    public String calculate(String expression) {
        ArrayList<Token> tokens = converter.convert(parser.parse(expression));
        boolean isHasVariable = tokens.stream().anyMatch(token -> token.type == VARIABLE);
        if (isHasVariable) {
            Node node = variableCalculator.calculate(tokens);
            return render.render(node);
        } else {
            double result = numericCalculator.calculate(tokens);
            return String.valueOf(result);
        }
    }
}

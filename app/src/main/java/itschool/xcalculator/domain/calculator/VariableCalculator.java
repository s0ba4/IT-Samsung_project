package itschool.xcalculator.domain.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.BiFunction;

import itschool.xcalculator.domain.Node;
import itschool.xcalculator.dto.Polynomial;
import itschool.xcalculator.dto.Token;

import static itschool.xcalculator.dto.Token.TokenType.FUNCTION;
import static itschool.xcalculator.dto.Token.TokenType.NUMBER;
import static itschool.xcalculator.dto.Token.TokenType.OPERATOR;
import static itschool.xcalculator.dto.Token.TokenType.VARIABLE;

public class VariableCalculator {
    public List<Node> calculate(List<Token> tokens) {
        Stack<Node> stack = new Stack<>();
        for (Token token : tokens) {
            if (token.type == NUMBER) {
                stack.push(new Polynomial(token.getNumberValue()));
            } else if (token.type == VARIABLE) {
                stack.push(new Polynomial(0, 1));
            } else if (token.type == FUNCTION) {
                stack.push(token);
            } else if (token.type == OPERATOR) {
                if (token.content.equals("+")) {
                    withTwoNumbers(stack, Polynomial::plus);
                } else if (token.content.equals("-")) {
                    withTwoNumbers(stack, Polynomial::minus);
                } else if (token.content.equals("*")) {
                    withTwoNumbers(stack, Polynomial::multiply);
                } else if (token.content.equals("/")) {
                    throw new RuntimeException();
                } else if (token.content.equals("^"))  {
                    throw new RuntimeException();
                }
            }
        }
        return new ArrayList<>(stack);
    }

    private void withTwoNumbers(
            Stack<Node> stack,
            BiFunction<Polynomial, Polynomial, Polynomial> function
    ) {
        Polynomial number1 = (Polynomial) stack.pop();
        Polynomial number2 = (Polynomial) stack.pop();
        Polynomial result = function.apply(number1, number2);
        stack.push(result);
    }
}

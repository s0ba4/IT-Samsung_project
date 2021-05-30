package itschool.xcalculator.domain.calculator;

import java.util.ArrayList;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.Function;

import itschool.xcalculator.dto.Token;
import itschool.xcalculator.dto.TrigonometricMode;

import static itschool.xcalculator.dto.Token.TokenType.FUNCTION;
import static itschool.xcalculator.dto.Token.TokenType.NUMBER;
import static itschool.xcalculator.dto.Token.TokenType.OPERATOR;

public class NumericCalculator {

    public double calculate(ArrayList<Token> tokens, TrigonometricMode mode) {
        Stack<Token> stack = new Stack<>();
        for (Token token : tokens) {
            if (token.type == NUMBER) {
                stack.push(token);

            } else if (token.type == OPERATOR) {
                if (token.content.equals("+")) {
                    withTwoNumbers(stack, Double::sum);
                } else if (token.content.equals("-")) {
                    withTwoNumbers(stack, (n1, n2) -> n2 - n1);
                } else if (token.content.equals("*")) {
                    withTwoNumbers(stack, (n1, n2) -> n1 * n2);
                } else if (token.content.equals("/")) {
                    withTwoNumbers(stack, (n1, n2) -> n2 / n1);
                } else if (token.content.equals("^")) {
                    withTwoNumbers(stack, (n1, n2) -> Math.pow(n2, n1));
                }
            } else if (token.type == FUNCTION) {
                if (token.content.equals("sin")) {
                    withOneNumber(stack, mode, Math::sin);
                } else if (token.content.equals("cos")) {
                    withOneNumber(stack, mode, Math::cos);
                } else if (token.content.equals("tg")) {
                    withOneNumber(stack, mode, Math::tan);
                } else if (token.content.equals("ctg")) {
                    withOneNumber(stack, mode, (n) -> Math.pow(Math.tan(n), -1));
                }
            }
        }
        return stack.pop().getNumberValue();
    }

    private void withTwoNumbers(
            Stack<Token> stack,
            BiFunction<Double, Double, Double> function
    ) {
        Token number1 = stack.pop();
        Token number2 = stack.pop();
        double result = function.apply(number1.getNumberValue(), number2.getNumberValue());
        stack.push(new Token(NUMBER, String.valueOf(result)));
    }

    private void withOneNumber(
            Stack<Token> stack,
            TrigonometricMode mode,
            Function<Double, Double> function
    ) {
        Token number1 = stack.pop();
        double numericValue = (mode == TrigonometricMode.RADIANS)
                ? number1.getNumberValue()
                : Math.toRadians(number1.getNumberValue());
        double value = function.apply(numericValue);
        Token result = new Token(NUMBER, String.valueOf(value));
        stack.push(result);
    }

}

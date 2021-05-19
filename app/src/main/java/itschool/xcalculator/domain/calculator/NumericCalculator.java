package itschool.xcalculator.domain.calculator;

import java.util.ArrayList;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.Function;

import itschool.xcalculator.dto.Token;

import static itschool.xcalculator.dto.Token.TokenType.FUNCTION;
import static itschool.xcalculator.dto.Token.TokenType.NUMBER;
import static itschool.xcalculator.dto.Token.TokenType.OPERATOR;

public class NumericCalculator {

    public double calculate(ArrayList<Token> tokens) {
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
                    withTwoNumbers(stack, Math::pow);
                }
            } else if (token.type == FUNCTION){
                if (token.content.equals("sin")){
                    withOneNumber(stack, Math::sin);
                }else if (token.content.equals("cos")){
                    withOneNumber(stack, Math::cos);
                }else if (token.content.equals("tg")){
                    withOneNumber(stack, Math::tan);
                }else if (token.content.equals("ctg")){
                    withOneNumber(stack, (n) -> Math.pow(Math.tan(n), -1));
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
            Function<Double, Double> function
    ) {
        Token number1 = stack.pop();
        double value = function.apply(number1.getNumberValue());
        Token result = new Token(NUMBER, String.valueOf(value));
        stack.push(result);
    }

}

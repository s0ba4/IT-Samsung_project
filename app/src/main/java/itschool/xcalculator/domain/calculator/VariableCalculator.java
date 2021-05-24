package itschool.xcalculator.domain.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.Function;

import itschool.xcalculator.domain.Node;
import itschool.xcalculator.dto.Polynomial;
import itschool.xcalculator.dto.Token;

import static itschool.xcalculator.dto.Token.TokenType.FUNCTION;
import static itschool.xcalculator.dto.Token.TokenType.NUMBER;
import static itschool.xcalculator.dto.Token.TokenType.OPERATOR;
import static itschool.xcalculator.dto.Token.TokenType.VARIABLE;

public class VariableCalculator {
    public Node calculate(List<Token> tokens) {
        Stack<Node> stack = new Stack<>();
        for (Token token : tokens) {
            if (token.type == NUMBER) {
                stack.push(new Node(new Polynomial(token.getNumberValue())));
            } else if (token.type == VARIABLE) {
                stack.push(new Node(new Polynomial(0, 1)));
            } else if (token.type == FUNCTION) {
                Node node = stack.peek();
                // если полином состоит из одной цифры, мы можем посчитать функцию
                if (node.isPolynomial() && node.getPolynomial().getOrder() == 0) {
                    switch (token.content) {
                        case "sin":
                            withOneNumber(stack, Math::sin);
                            break;
                        case "cos":
                            withOneNumber(stack, Math::cos);
                            break;
                        case "tg":
                            withOneNumber(stack, Math::tan);
                            break;
                        case "ctg":
                            withOneNumber(stack, (n) -> Math.pow(Math.tan(n), -1));
                            break;
                    }
                    // если в полиноме есть x, то создаём ветку дерева
                } else {
                    stack.pop();
                    stack.push(new Node(token, node));
                }
            } else if (token.type == OPERATOR) {
                switch (token.content) {
                    case "+":
                        withTwoNumbers(stack, token, Polynomial::plus);
                        break;
                    case "-":
                        withTwoNumbers(stack, token, Polynomial::minus);
                        break;
                    case "*":
                        withTwoNumbers(stack, token, Polynomial::multiply);
                        break;
                    case "/":
                        Node left = stack.pop();
                        Node right = stack.pop();
                        stack.push(new Node(token, left, right));
                        break;
                    case "^":
                        withTwoNumbers(stack, token, (p1, p2) -> {
                            if (p1.getOrder() == 0) {
                                double coeff = p1.coefficientAt(0);
                                if (Math.floor(coeff) == coeff) {
                                    int n = (int) coeff - 1;
                                    Polynomial p3 = p2;
                                    for (int i = 0; i < n; i++) {
                                        p3 = p3.multiply(p2);
                                    }
                                    return p3;
                                } else {
                                    throw new RuntimeException("Unsupported power operation");
                                }
                            } else {
                                throw new RuntimeException("Unsupported power operation");
                            }
                        });
                        break;
                }
            }
        }
        return stack.pop();
    }

    private void withOneNumber(
            Stack<Node> stack,
            Function<Double, Double> function
    ) {
        Node number1 = stack.pop();
        Double value = function.apply(number1.getPolynomial().coefficientAt(0));
        Node result = new Node(new Polynomial(value));
        stack.push(result);
    }

    private void withTwoNumbers(
            Stack<Node> stack,
            Token operatorToken,
            BiFunction<Polynomial, Polynomial, Polynomial> function
    ) {
        Node number1 = stack.pop();
        Node number2 = stack.pop();
        if (number1.isPolynomial() && number2.isPolynomial()) {
            Polynomial result = function.apply(
                    number1.getPolynomial(),
                    number2.getPolynomial()
            );
            stack.push(new Node(result));
        } else {
            stack.push(new Node(operatorToken, number1, number2));
        }
    }
}

package itschool.xcalculator;

import java.util.ArrayList;
import java.util.Stack;

import static itschool.xcalculator.Token.TokenType.FUNCTION;
import static itschool.xcalculator.Token.TokenType.NUMBER;
import static itschool.xcalculator.Token.TokenType.OPERATOR;

public class NumericCalculator {

    public double calculate(ArrayList<Token> tokens) {
        Stack<Token> stack = new Stack<>();
        for (Token token : tokens) {
            if (token.type == NUMBER) {
                stack.push(token);

            } else if (token.type == OPERATOR) {
                if (token.content.equals("+")) {
                    Token number1 = stack.pop();
                    Token number2 = stack.pop();
                    double sum = number1.getNumberValue() + number2.getNumberValue();
                    Token result = new Token(NUMBER, String.valueOf(sum));
                    stack.push(result);
                } else if (token.content.equals("-")) {
                    Token number1 = stack.pop();
                    Token number2 = stack.pop();
                    double sub = number2.getNumberValue() - number1.getNumberValue();
                    Token result = new Token(NUMBER, String.valueOf(sub));
                    stack.push(result);
                } else if (token.content.equals("*")) {
                    Token number1 = stack.pop();
                    Token number2 = stack.pop();
                    double mult = number1.getNumberValue() * number2.getNumberValue();
                    Token result = new Token(NUMBER, String.valueOf(mult));
                    stack.push(result);
                } else if (token.content.equals("/")) {
                    Token number1 = stack.pop();
                    Token number2 = stack.pop();
                    double div = number2.getNumberValue() / number1.getNumberValue();
                    Token result = new Token(NUMBER, String.valueOf(div));
                    stack.push(result);
                } else if (token.content.equals("^")) {
                    Token number1 = stack.pop();
                    Token number2 = stack.pop();
                    double pow = Math.pow(number2.getNumberValue(), number1.getNumberValue());
                    Token result = new Token(NUMBER, String.valueOf(pow));
                    stack.push(result);
                }
            } else if (token.type == FUNCTION){
                if (token.content.equals("sin")){
                    Token number1 = stack.pop();
                    double sin = Math.sin(number1.getNumberValue());
                    Token result = new Token(NUMBER, String.valueOf(sin));
                    stack.push(result);
                }else if (token.content.equals("cos")){
                    Token number1 = stack.pop();
                    double cos = Math.cos(number1.getNumberValue());
                    Token result = new Token(NUMBER, String.valueOf(cos));
                    stack.push(result);
                }else if (token.content.equals("tg")){
                    Token number1 = stack.pop();
                    double tg = Math.tan(number1.getNumberValue());
                    Token result = new Token(NUMBER, String.valueOf(tg));
                    stack.push(result);
                }
            }
        }
        return stack.pop().getNumberValue();
    }

}

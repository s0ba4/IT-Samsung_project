package itschool.smartcalculator;

import android.icu.util.Output;
import android.media.VolumeShaper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import static itschool.smartcalculator.Token.TokenType.FUNCTION;
import static itschool.smartcalculator.Token.TokenType.NUMBER;
import static itschool.smartcalculator.Token.TokenType.OPERATOR;
import static itschool.smartcalculator.Token.TokenType.VARIABLE;

public class PostfixConverter {

    public ArrayList<Token> convert(ArrayList<Token> tokens) {
        Stack<Token> stack = new Stack<>();
        ArrayList<Token> output = new ArrayList<>();
        for (Token token : tokens) {
            if (token.type == NUMBER || token.type == VARIABLE) {
                output.add(token);
            } else if (token.type == FUNCTION) {
                stack.push(token);
            } else if (token.isLeftBracket()) {
                stack.push(token);
            } else if (token.isRightBracket()) {
                while (!stack.isEmpty() && !stack.peek().isLeftBracket()) {
                    output.add(stack.pop());
                }
                stack.pop();
            } else if (token.type == OPERATOR) {
                while (!stack.isEmpty() && (stack.peek().type == FUNCTION || stack.peek().getPriority() >= token.getPriority())) {
                    output.add(stack.pop());
                }
                stack.push(token);
            }
        }
        while (!stack.isEmpty()) {
            Token token = stack.pop();
            if (token.type != OPERATOR) {
                throw new IllegalArgumentException("В выражении не согласованы скобки");
            }
            output.add(token);
        }
        return output;
    }
}

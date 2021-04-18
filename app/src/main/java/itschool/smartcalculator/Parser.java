package itschool.smartcalculator;

import java.util.ArrayList;
import java.util.List;

import static itschool.smartcalculator.Token.TokenType.NUMBER;
import static itschool.smartcalculator.Token.TokenType.OPERATOR;

// text to token
public class Parser {
    public ArrayList<Token> parse(String text) {
        ArrayList<Token> tokens = new ArrayList<>();
        int i = 0;
        while (i < text.length()) {
            char currentChar = text.charAt(i);
            if (currentChar == '(') {
                tokens.add(new Token(OPERATOR, "("));
            } else if (currentChar == ')') {
                tokens.add(new Token(OPERATOR, ")"));
            } else if (currentChar == '+') {
                tokens.add(new Token(OPERATOR, "+"));
            } else if (currentChar == '-') {
                tokens.add(new Token(OPERATOR, "-"));
            } else if (currentChar == '/') {
                tokens.add(new Token(OPERATOR, "/"));
            } else if (currentChar == '*') {
                tokens.add(new Token(OPERATOR, "*"));
            }
            if (Character.isDigit(currentChar)) {
                StringBuilder number = new StringBuilder();
                while (i < text.length() && Character.isDigit(text.charAt(i))) {
                    number.append(text.charAt(i));
                    i++;
                }
                tokens.add(new Token(NUMBER, number.toString()));
                System.out.println(currentChar);
                i--;
            }
            i++;
        }
        return tokens;
    }
}


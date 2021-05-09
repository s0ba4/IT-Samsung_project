package itschool.xcalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static itschool.xcalculator.Token.TokenType.FUNCTION;
import static itschool.xcalculator.Token.TokenType.NUMBER;
import static itschool.xcalculator.Token.TokenType.OPERATOR;
import static itschool.xcalculator.Token.TokenType.VARIABLE;

// text to token
public class Parser {
    public ArrayList<Token> parse(String text) {
        ArrayList<Token> tokens = new ArrayList<>();
        HashSet operators = new HashSet<String>(Arrays.asList("+", "-", "*", "/", "(", ")", "^"));
        HashSet functions = new HashSet<String>(Arrays.asList("sin", "cos", "tg", "ctg"));
        int i = 0;
        while (i < text.length()) {
            char currentChar = text.charAt(i);
            if (operators.contains(String.valueOf(currentChar))) {
                tokens.add(new Token(OPERATOR, String.valueOf(currentChar)));
            }
            if (Character.isDigit(currentChar)) {
                StringBuilder number = new StringBuilder();
                while (i < text.length() && (Character.isDigit(text.charAt(i)) || text.charAt(i) == '.')) {
                    number.append(text.charAt(i));
                    i++;
                }
                tokens.add(new Token(NUMBER, number.toString()));
                i--;
            }
            if (currentChar == 'x') {
                tokens.add(new Token(VARIABLE, String.valueOf(currentChar)));
            } else if (Character.isAlphabetic(currentChar)) {
                StringBuilder name = new StringBuilder();
                while (i < text.length() && (Character.isAlphabetic(text.charAt(i)))) {
                    name.append(text.charAt(i));
                    i++;
                }
                if (functions.contains(name.toString())) {
                    tokens.add(new Token(FUNCTION, name.toString()));
                } else {
                    throw new IllegalArgumentException(String.format("Недопустимый символ: %s", name));
                }
                i--;
            }
            i++;
        }
        return tokens;
    }
}


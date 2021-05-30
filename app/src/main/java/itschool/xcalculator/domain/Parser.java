package itschool.xcalculator.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import itschool.xcalculator.dto.Token;

import static itschool.xcalculator.dto.Token.TokenType.FUNCTION;
import static itschool.xcalculator.dto.Token.TokenType.NUMBER;
import static itschool.xcalculator.dto.Token.TokenType.OPERATOR;
import static itschool.xcalculator.dto.Token.TokenType.VARIABLE;

// text to token
public class Parser {

    private final HashSet<Character> operators = new HashSet<>(Arrays.asList('+', '-', '*', '/', '(', ')', '^'));
    private final HashSet<String> functions = new HashSet<>(Arrays.asList("sin", "cos", "tg", "ctg"));

    public ArrayList<Token> parse(String text) {
        ArrayList<Token> tokens = new ArrayList<>();
        int i = 0;
        while (i < text.length()) {
            char currentChar = text.charAt(i);
            if (operators.contains(currentChar)) {
                tokens.add(new Token(OPERATOR, String.valueOf(currentChar)));
                // возможность опустить знак умножения между скобками
                if (i + 1 < text.length() && text.charAt(i + 1) == '(' && currentChar == ')') {
                    tokens.add(new Token(OPERATOR, "*"));
                    tokens.add(new Token(OPERATOR, "("));
                    i++;
                }
            } else if (Character.isDigit(currentChar)) {
                StringBuilder number = new StringBuilder();
                while (i < text.length() && (Character.isDigit(text.charAt(i)) || text.charAt(i) == '.')) {
                    number.append(text.charAt(i));
                    i++;
                }
                tokens.add(new Token(NUMBER, number.toString()));
                // возможность опустить знак умножения между числом и переменной
                if (i < text.length() && text.charAt(i) == 'x') {
                    tokens.add(new Token(OPERATOR, "*"));
                    tokens.add(new Token(VARIABLE, "x"));
                    i++;
                } else if (i < text.length() && text.charAt(i) == '(') {
                    tokens.add(new Token(OPERATOR, "*"));
                    tokens.add(new Token(OPERATOR, "("));
                    i++;
                }
                i--;
            } else if (currentChar == 'x') {
                tokens.add(new Token(VARIABLE, String.valueOf(currentChar)));
            } else if (currentChar == 'π') {
                tokens.add(new Token(NUMBER, String.valueOf(Math.PI)));
            } else if (currentChar == 'e') {
                tokens.add(new Token(NUMBER, String.valueOf(Math.E)));
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


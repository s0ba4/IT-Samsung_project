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
            } else if (Character.isDigit(currentChar)) {
                StringBuilder number = new StringBuilder();
                while (i < text.length() && (Character.isDigit(text.charAt(i)) || text.charAt(i) == '.')) {
                    number.append(text.charAt(i));
                    i++;
                }
                tokens.add(new Token(NUMBER, number.toString()));
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
        return desugar(tokens);
    }

    /**
     * <h1>Возвращает в выражение пропущенные знаки умножения</h1>
     * <ul>
     *     <li>Между двумя числами (например, 2pi)</li>
     *     <li>Между числом и переменной (например, 3x)</li>
     *     <li>Между числом и открывающей скобкой</li>
     *     <li>Между переменной и открывающей скобкой</li>
     *     <li>Между закрывающей и открывающей скобками</li>
     * </ul>
     *
     * <p>Алгоритм выдаст ошибку если найдет недопустимый пропуск оператора</p>
     *
     * @param tokens массив токенов из выражения с опущенными скобками
     * @return массив токенов с восстановленными знаками умножения
     */
    private ArrayList<Token> desugar(ArrayList<Token> tokens) {
        ArrayList<Token> output = new ArrayList<>();
        for (int i = 0; i < tokens.size() - 1; i++) {
            Token currentToken = tokens.get(i);
            Token nextToken = tokens.get(i + 1);
            output.add(currentToken);
            boolean isBetweenTwoNumbers =
                    currentToken.type == NUMBER && nextToken.type == NUMBER;
            boolean isBetweenNumberAndVariable =
                    currentToken.type == NUMBER && nextToken.type == VARIABLE;
            boolean isBetweenNumberAndLeftBracket =
                    currentToken.type == NUMBER && nextToken.isLeftBracket();
            boolean isBetweenVariableAndLeftBracket =
                    currentToken.type == VARIABLE && nextToken.isLeftBracket();
            boolean isBetweenRightAndLeftBracket =
                    currentToken.isRightBracket() && nextToken.isLeftBracket();
            if (isBetweenTwoNumbers || isBetweenNumberAndVariable || isBetweenNumberAndLeftBracket
                    || isBetweenVariableAndLeftBracket || isBetweenRightAndLeftBracket) {
                output.add(new Token(OPERATOR, "*"));
            } else {
                // обработаем недопустимые пропуски оператора умножения
                boolean isBetweenRightBracketAndNumber = // (1+2)3 - error, (1+2)*3 - correct
                        currentToken.isRightBracket() && nextToken.type == NUMBER;
                boolean isBetweenRightBracketAndVariable = // (1+2)x - error, (1+2)*x - correct
                        currentToken.isRightBracket() && nextToken.type == VARIABLE;
                boolean isBetweenVariableAndNumber = // x3 - error, 3x - correct
                        currentToken.type == VARIABLE && nextToken.type == NUMBER;
                if (isBetweenRightBracketAndNumber || isBetweenRightBracketAndVariable
                        || isBetweenVariableAndNumber) {
                    throw new RuntimeException("В выражении пропущен оператор умножения!");
                }
            }
        }
        output.add(tokens.get(tokens.size() - 1));
        return output;
    }
}


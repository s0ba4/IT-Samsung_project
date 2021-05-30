package itschool.xcalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import itschool.xcalculator.domain.Parser;
import itschool.xcalculator.dto.Token;

import static itschool.xcalculator.dto.Token.TokenType.FUNCTION;
import static itschool.xcalculator.dto.Token.TokenType.NUMBER;
import static itschool.xcalculator.dto.Token.TokenType.OPERATOR;
import static itschool.xcalculator.dto.Token.TokenType.VARIABLE;

public class ParserTest {
    @Test
    public void test1() {
        Parser parser = new Parser();
        ArrayList<Token> parserAnswer = parser.parse("(29.34 + sin(x) * 12 / 23)");
        List<Token> rightAnswer = Arrays.asList(
                new Token(OPERATOR, "("),
                new Token(NUMBER, "29.34"),
                new Token(OPERATOR, "+"),
                new Token(FUNCTION, "sin"),
                new Token(OPERATOR, "("),
                new Token(VARIABLE, "x"),
                new Token(OPERATOR, ")"),
                new Token(OPERATOR, "*"),
                new Token(NUMBER, "12"),
                new Token(OPERATOR, "/"),
                new Token(NUMBER, "23"),
                new Token(OPERATOR, ")")
        );
        Assertions.assertEquals(rightAnswer, parserAnswer);

    }
    @Test
    public void test2() {
        Parser parser = new Parser();
        ArrayList<Token> parserAnswer = parser.parse("2x + 3x^2");
        List<Token> rightAnswer = Arrays.asList(
                new Token(NUMBER, "2"),
                new Token(OPERATOR, "*"),
                new Token(VARIABLE, "x"),
                new Token(OPERATOR, "+"),
                new Token(NUMBER, "3"),
                new Token(OPERATOR, "*"),
                new Token(VARIABLE, "x"),
                new Token(OPERATOR, "^"),
                new Token(NUMBER, "2")
        );
        Assertions.assertEquals(rightAnswer, parserAnswer);
    }
    @Test
    public void test3 () {
        Parser parser = new Parser();
        ArrayList<Token> parserAnswer = parser.parse("3(x-5)(x+1)");
        List<Token> rightAnswer = Arrays.asList(
                new Token(NUMBER, "3"),
                new Token(OPERATOR, "*"),
                new Token(OPERATOR, "("),
                new Token(VARIABLE, "x"),
                new Token(OPERATOR, "-"),
                new Token(NUMBER, "5"),
                new Token(OPERATOR, ")"),
                new Token(OPERATOR, "*"),
                new Token(OPERATOR, "("),
                new Token(VARIABLE, "x"),
                new Token(OPERATOR, "+"),
                new Token(NUMBER, "1"),
                new Token(OPERATOR, ")")
        );
        Assertions.assertEquals(rightAnswer, parserAnswer);
    }
}

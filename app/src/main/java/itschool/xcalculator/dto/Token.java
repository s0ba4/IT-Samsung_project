package itschool.xcalculator.dto;

import java.util.Objects;

import itschool.xcalculator.domain.Node;

public class Token implements Node {
    public final TokenType type;
    public final String content;

    public Token(TokenType type, String content) {
        this.type = type;
        this.content = content;
    }

    public double getNumberValue (){
        return Double.parseDouble(content);
    }

    public boolean isLeftBracket() {
        return type == TokenType.OPERATOR && content.equals("(");
    }

    public boolean isRightBracket() {
        return type == TokenType.OPERATOR && content.equals(")");
    }

    public int getPriority() {
        if (type == TokenType.OPERATOR) {
            if (content.equals("+") || content.equals("-")) {
                return 1;
            }
            if (content.equals("*") || content.equals("/")) {
                return 2;
            }
            if (content.equals("^")) {
                return 3;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        switch (type) {
            case NUMBER:
                return String.format("NUMBER(%s)", content);
            case OPERATOR:
                return String.format("OPERATOR(%s)", content);
            case FUNCTION:
                return String.format("FUNCTION(%s)", content);
            case VARIABLE:
                return "x";
        }
        return null;
    }

    public enum TokenType {NUMBER, OPERATOR, FUNCTION, VARIABLE}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return type == token.type &&
                content.equals(token.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, content);
    }
}

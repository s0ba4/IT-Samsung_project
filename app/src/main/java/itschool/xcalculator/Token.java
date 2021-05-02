package itschool.xcalculator;

import java.util.Objects;

public class Token {
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
                return "NUMBER(" + content + ")";
            case OPERATOR:
                return "OPERATOR(" + content + ")";
            case FUNCTION:
                return "FUNCTION(" + content + ")";
            case VARIABLE:
                return "x";
        }
        return null;
    }

    enum TokenType {NUMBER, OPERATOR, FUNCTION, VARIABLE}

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
